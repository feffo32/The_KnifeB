package com.example.the_knife.database.dao.implDAO;

import com.example.the_knife.common.model.Recensione;
import com.example.the_knife.common.model.Ristorante;
import com.example.the_knife.database.DBConnection;
import com.example.the_knife.database.dao.interfacesDAO.RecensioneDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAOImpl implements RecensioneDAO {
    @Override
    public List<Recensione> visualizzaRecensioneByRistorante(Ristorante ristorante) {
        String sqlVisualizzaRecensioneRistorante = "SELECT * FROM recensione WHERE idRistorante = ?";
        List<Recensione> recensione = new ArrayList<Recensione>();

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlVisualizzaRecensioneRistorante);
                ) {
            stmt.setInt(1, ristorante.getIdRistorante());

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()) {
                    recensione.add(mapResultSetToRecensione(rs));
                }
                return recensione;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean rispondiRecensione(Recensione recensione) {
        String sqlRispostaRecensione = "UPDATE recensione SET testoRecensione = ? WHERE idRecensione = ?";
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRispostaRecensione);
                ) {
            stmt.setString(1, recensione.getTestoRisposta());
            stmt.setInt(2, recensione.getIdRecensione());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeRispostaRec(Recensione recensione) {
        String sqlRemoveRispostaRec = "UPDATE recensione SET testoRisposta = NULL WHERE idRecensione = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRemoveRispostaRec);
        ) {
            stmt.setInt(1, recensione.getIdRecensione());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modificaRispostaRec(Recensione recensione) {

        String sqlModificaRispostaRec = "UPDATE recensione SET testoRisposta = ? WHERE idRecensione = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlModificaRispostaRec);
        ) {
            stmt.setString(1, recensione.getTestoRisposta());
            stmt.setInt(2, recensione.getIdRecensione());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean aggiungiRecensione(Recensione newRecensione) {

        String sqlAggiungiRecensione = "INSERT INTO recensione (IdCliente,IdRistorante,TestoRecensione,Valutazione,DataRecensione,TestoRisposta) VALUES(?,?,?,?,?,?)";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlAggiungiRecensione);
        ) {
            stmt.setInt(1, newRecensione.getIdCliente());
            stmt.setInt(2, newRecensione.getIdRistorante());
            stmt.setString(3, newRecensione.getTestoRecensione());
            stmt.setInt(4, newRecensione.getValutazione());
            stmt.setDate(5,Date.valueOf(newRecensione.getDataRecensione()));
            stmt.setString(6, newRecensione.getTestoRisposta());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean removeRecensione(Recensione recensione) {

        String sqlRemoveRecensione = "DELETE FROM recensione WHERE idRecensione = ?";
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRemoveRecensione);
        ) {
            stmt.setInt(1, recensione.getIdRecensione());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modificaRecensione(Recensione rec, String nuovoTesto, Integer nuovaValutazione) {

        if ((nuovoTesto == null || nuovoTesto.isBlank())) {
            return false;
        }

        StringBuilder sqlModificaRecensione = new StringBuilder("UPDATE Recensione SET ");
        List<Object> parametri = new ArrayList<>();
        boolean aggiungiVirgola = false;

        if (nuovoTesto != null && !nuovoTesto.isBlank()) {
            sqlModificaRecensione.append("TestoRecensione = ?");
            parametri.add(nuovoTesto);
            aggiungiVirgola = true;
        }

        if (nuovaValutazione != null) {
            if (aggiungiVirgola) {
                sqlModificaRecensione.append(", ");
            }
            sqlModificaRecensione.append("Valutazione = ?");
            parametri.add(nuovaValutazione);
        }

        sqlModificaRecensione.append(" WHERE IdRecensione = ?");
        parametri.add(rec.getIdRecensione());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlModificaRecensione.toString())) {
            // Mappatura dinamica dei parametri estratti dalla lista
            for (int i = 0; i < parametri.size(); i++) {
                stmt.setObject(i + 1, parametri.get(i));
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected> 0;

        } catch (SQLException e) {
            System.err.println("Errore durante la modifica della recensione: " + e.getMessage());
            throw new RuntimeException("Impossibile modificare la recensione", e);
        }
    }

    private Recensione mapResultSetToRecensione(ResultSet rs) throws SQLException {
        Recensione rec = new Recensione();

        rec.setIdCliente(rs.getInt("IdCliente"));
        rec.setIdRistorante(rs.getInt("IdRistorante"));
        rec.setTestoRecensione(rs.getString("TestoRecensione"));
        rec.setValutazione(rs.getInt("Valutazione"));
        Date dataRec = rs.getDate("DataRecensione");
        if(dataRec != null){
            rec.setDataRecensione(dataRec.toLocalDate());
        }
        rec.setTestoRisposta(rs.getString("TestoRisposta"));

        return rec;

    }
}
