package com.example.the_knife.database.dao.implDAO;

import com.example.the_knife.common.model.Prenotazione;
import com.example.the_knife.database.DBConnection;
import com.example.the_knife.database.dao.interfacesDAO.PrenotazioneDAO;

import java.sql.*;

public class PrenotazioneDAOImpl implements PrenotazioneDAO {
    @Override
    public boolean prenota(Prenotazione p) {

        // La query restituirebbe NULL se non ci fossero prenotazioni, con COALESCE invece di restituire NULL restituisce 0
        String sqlPostiOccupati = "SELECT COALESCE(SUM(NumeroPersone), 0) AS Occupati FROM Prenotazione " +
                "WHERE IdRistorante = ? AND Data = ? AND FasciaOraria = ?";

        // FOR UPDATE blocca la riga Ristorante quando si entra nella transazione e la sblocca quando si esce
        String sqlNumPosti = "SELECT NumeroPosti FROM Ristorante WHERE IdRistorante = ? FOR UPDATE";

        String sqlPrenotazione = "INSERT INTO Prenotazione (IdCliente, IdRistorante, Data, FasciaOraria, Nominativo, Telefono, InfoAggiuntive, NumeroPersone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Inizio transazione

            int numeroPostiTotali = 0;

            // Leggiamo i posti totali del ristorante
            try (PreparedStatement stmtNumPosti = conn.prepareStatement(sqlNumPosti)) {
                stmtNumPosti.setInt(1, p.getIdRistorante());

                try (ResultSet rs = stmtNumPosti.executeQuery()) {
                    if (rs.next()) {
                        numeroPostiTotali = rs.getInt("NumeroPosti");
                    } else {
                        conn.rollback();
                        return false; // Ristorante non trovato
                    }
                }
            }

            // Calcoliamo i posti occupati per quella data/fascia
            int postiOccupati = 0;
            try (PreparedStatement stmtOccupati = conn.prepareStatement(sqlPostiOccupati)) {
                stmtOccupati.setInt(1, p.getIdRistorante());
                stmtOccupati.setDate(2, Date.valueOf(p.getData()));
                stmtOccupati.setString(3, p.getFasciaOraria());

                try (ResultSet rs = stmtOccupati.executeQuery()) {
                    if (rs.next()) {
                        postiOccupati = rs.getInt("Occupati");
                    }
                }
            }

            // Controllo disponibilità
            if ((postiOccupati + p.getNumeroPersone()) > numeroPostiTotali) {
                conn.rollback(); // Non c'è posto, annulliamo tutto
                return false;
            }

            // Inserimento della prenotazione
            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlPrenotazione)) {
                stmtInsert.setInt(1, p.getIdCliente());
                stmtInsert.setInt(2, p.getIdRistorante());
                stmtInsert.setDate(3, Date.valueOf(p.getData()));
                stmtInsert.setString(4, p.getFasciaOraria());
                stmtInsert.setString(5, p.getNominativo());
                stmtInsert.setString(6, p.getTelefono());
                stmtInsert.setString(7, p.getInfoAggiuntive());
                stmtInsert.setInt(8, p.getNumeroPersone());

                stmtInsert.executeUpdate();
            }

            conn.commit(); // Rilascio mutua esclusione
            return true;

        } catch (SQLException e) {
            System.err.println("Errore di concorrenza o DB durante la prenotazione: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean annullaPrenotazione(Prenotazione prenotazione) {
        String sqlAnnullaPrenotazione = " DELETE FROM Prenotazione WHERE IdPrenotazione = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlAnnullaPrenotazione);
                ){

            stmt.setInt(1, prenotazione.getIdPrenotazione());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean modificaPrenotazione(Prenotazione prenotazione, Prenotazione newPrenotazione) {
        // Escludiamo la vecchia prenotazione dal conteggio per non auto-bloccarci i posti (con l' IdPrenotazione != ?)
        String sqlPostiOccupati = "SELECT COALESCE(SUM(NumeroPersone), 0) AS Occupati FROM Prenotazione " +
                "WHERE IdRistorante = ? AND Data = ? AND FasciaOraria = ? AND IdPrenotazione != ?";

        // FOR UPDATE blocca la riga Ristorante quando si entra nella transazione e la sblocca quando si esce
        String sqlNumPosti = "SELECT NumeroPosti FROM Ristorante WHERE IdRistorante = ? FOR UPDATE";

        String sqlUpdate = "UPDATE Prenotazione SET Data = ?, Ora = ?, FasciaOraria = ?, Nominativo = ?, " +
                "Telefono = ?, InfoAggiuntive = ?, NumeroPersone = ? WHERE IdPrenotazione = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Inizio transazione unica

            int numeroPostiTotali = 0;

            // Lock sul ristorante
            // Prendo dal DB il numero totale dei posti
            try (PreparedStatement stmtNumPosti = conn.prepareStatement(sqlNumPosti)) {
                stmtNumPosti.setInt(1, newPrenotazione.getIdRistorante());
                try (ResultSet rs = stmtNumPosti.executeQuery()) {
                    if (rs.next()) {
                        numeroPostiTotali = rs.getInt("NumeroPosti");
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            // Calcolo dei posti occupati (escludendo la prenotazione attuale)
            int postiOccupati = 0;
            try (PreparedStatement stmtOccupati = conn.prepareStatement(sqlPostiOccupati)) {
                stmtOccupati.setInt(1, newPrenotazione.getIdRistorante());
                stmtOccupati.setDate(2, Date.valueOf(newPrenotazione.getData()));
                stmtOccupati.setString(3, newPrenotazione.getFasciaOraria());
                stmtOccupati.setInt(4, prenotazione.getIdPrenotazione()); // Esclusione prenotazione già presente (da cambiare)

                try (ResultSet rs = stmtOccupati.executeQuery()) {
                    if (rs.next()) {
                        postiOccupati = rs.getInt("Occupati");
                    }
                }
            }

            // Controllo disponibilità per la nuova prenotazione
            if ((postiOccupati + newPrenotazione.getNumeroPersone()) > numeroPostiTotali) {
                conn.rollback();
                return false; // Non c'è posto
            }

            // Aggiornamento del record esistente
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setDate(1, Date.valueOf(newPrenotazione.getData()));
                stmtUpdate.setTime(2,newPrenotazione.getOra());
                stmtUpdate.setString(3, newPrenotazione.getFasciaOraria());
                stmtUpdate.setString(4, newPrenotazione.getNominativo());
                stmtUpdate.setString(5, newPrenotazione.getTelefono());
                stmtUpdate.setString(6, newPrenotazione.getInfoAggiuntive());
                stmtUpdate.setInt(7, newPrenotazione.getNumeroPersone());
                stmtUpdate.setInt(8, prenotazione.getIdPrenotazione());

                stmtUpdate.executeUpdate();
            }

            conn.commit(); // Conferma definitiva
            return true;

        } catch (SQLException e) {
            System.err.println("Errore durante la modifica della prenotazione: " + e.getMessage());
            return false;
        }
    }


}
