package com.example.the_knife.database.dao.implDAO;

import com.example.the_knife.common.model.Prenotazione;
import com.example.the_knife.common.model.Ristorante;
import com.example.the_knife.database.DBConnection;
import com.example.the_knife.database.dao.interfacesDAO.RistoranteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RistoranteDAOImpl implements RistoranteDAO {


    @Override
    public List<Ristorante> cercaRistorante(String luogoOrName, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean bookOnline) {

        List<Ristorante> risultati = new ArrayList<>();
        boolean haLuogo = luogoOrName != null && !luogoOrName.isBlank();

        try (Connection conn = DBConnection.getConnection()) {

            // MATCH PER NOME ESATTO (Priorità massima)
            if (haLuogo) {
                String sqlName = "SELECT * FROM Ristorante WHERE Nome ILIKE ?"; // ILIKE è case-insensitive in PostgreSQL
                try (PreparedStatement stmtName = conn.prepareStatement(sqlName)) {
                    stmtName.setString(1, luogoOrName);
                    try (ResultSet rs = stmtName.executeQuery()) {
                        // Usiamo il while per ciclare su tutti i ristoranti omonimi
                        while (rs.next()) {
                            risultati.add(mapResultSetToRistorante(rs));
                        }

                        // Se abbiamo trovato almeno un ristorante con quel nome,
                        // ritorniamo la lista intera e fermiamo la ricerca
                        if (!risultati.isEmpty()) {
                            return risultati;
                        }
                    }
                }
            }

            // CALCOLO COORDINATE (Se non è un nome esatto, lo trattiamo come luogo)
            double[] coordinateLuogo = haLuogo ? coordinate(luogoOrName) : null;

            // Se abbiamo un luogo ma non troviamo le coordinate, falliamo come nel file JSON
            if (haLuogo && (coordinateLuogo == null || coordinateLuogo[0] == 0)) {
                return risultati;
            }

            // COSTRUZIONE DELLA QUERY DINAMICA PER I FILTRI BASE
            StringBuilder sql = new StringBuilder("SELECT * FROM Ristorante WHERE 1=1");
            // 1=1 permette di creare la clausola WHERE
            // per poi fare la concatenazione tramite gli AND però quella 1=1
            // restituisce sempre true e permette di andare avanti con la query dinamica
            List<Object> parametri = new ArrayList<>();

            if (tipoCucina != null && !tipoCucina.isBlank()) {
                sql.append(" AND TipoCucina = ?");
                parametri.add(tipoCucina);
            }

            if (fasciaPrezzo != null && !fasciaPrezzo.isBlank()) {
                sql.append(" AND FasciaPrezzi = ?");
                parametri.add(fasciaPrezzo);
            }

            if(delivery) {
                sql.append(" AND Delivery = true");
                parametri.add(delivery);
            }

            if (bookOnline) {
                sql.append(" AND PrenotazioneOnline = true");
                parametri.add(bookOnline);
            }

            // FILTRO PER DISTANZA (Formula di Haversine SQL)
            if (coordinateLuogo != null) {
                // Formula per calcolare i km di distanza tra due punti tramite Latitudine e Longitudine
                sql.append(" AND (6371 * acos(cos(radians(?)) * cos(radians(Latitudine)) * " +
                        "cos(radians(Longitudine) - radians(?)) + sin(radians(?)) * " +
                        "sin(radians(Latitudine)))) <= 40");

                parametri.add(coordinateLuogo[0]); // Latitudine target
                parametri.add(coordinateLuogo[1]); // Longitudine target
                parametri.add(coordinateLuogo[0]); // Latitudine target
            }

            // ESECUZIONE DELLA QUERY PRINCIPALE
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                // Imposta tutti i parametri raccolti dinamicamente
                for (int i = 0; i < parametri.size(); i++) {
                    stmt.setObject(i + 1, parametri.get(i));
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        risultati.add(mapResultSetToRistorante(rs));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la ricerca dei ristoranti: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return risultati;
    }

    @Override
    public List<Ristorante> cercaTop10() {
        String sqlCercaTop10 = "SELECT Ristorante.IdRistorante, Ristorante.Nome, COALESCE(AVG(Recensione.Stelle), 0) AS ValutazioneMedia " +
                "FROM Ristorante " +
                "LEFT JOIN " +
                "Recensione ON Ristorante.IdRistorante = Recensione.IdRistorante " +
                "GROUP BY Ristorante.IdRistorante, Ristorante.Nome " +
                "ORDER BY ValutazioneMedia DESC " +
                "LIMIT 10 " ;

        List<Ristorante> risultati = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlCercaTop10);
        ) {
            try(ResultSet ris =  stmt.executeQuery();) {
                while (ris.next()) {
                    risultati.add(mapResultSetToRistorante(ris));
                }
            }
            return risultati;
        }catch (SQLException e) {
            System.err.println("Errore durante la ricerca dei ristoranti: " + e.getMessage());
            throw new RuntimeException("Impossibile recuperare la classifica Top 10 dal database", e);
        }
    }

    @Override
    public boolean aggiungiRistorante(Ristorante ristorante) {

        String sqlAddRist = "INSERT INTO Ristorante " +
                "(idristoratore,nome,citta,indirizzo,ncivico,cap,latitudine,longitudine,email,telfono,websiteurl,descrizione,servizi,numerostelle,fasciaprezzi,tipocucina,delivery,prenotazioneOnline) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlAddRist);
                ){
                stmt.setInt(1, ristorante.getIdRistoratore());
                stmt.setString(2, ristorante.getNome());
                stmt.setString(3, ristorante.getCitta());
                stmt.setString(4, ristorante.getIndirizzo());
                stmt.setString(5,ristorante.getnCivico());
                stmt.setString(6,ristorante.getCap());
                stmt.setDouble(7, ristorante.getLatitudine());
                stmt.setDouble(8, ristorante.getLongitudine());
                stmt.setString(9, ristorante.getEmail());
                stmt.setString(10, ristorante.getTelefono());
                stmt.setString(11,ristorante.getWebsiteUrl());
                stmt.setString(12,ristorante.getDescrizione());
                stmt.setString(13,ristorante.getServizi());
                stmt.setInt(14,ristorante.getNumeroStelle());
                stmt.setString(15,ristorante.getFasciaPrezzi());
                stmt.setString(16,ristorante.getTipoCucina());
                stmt.setBoolean(17,ristorante.isDelivery());
                stmt.setBoolean(18,ristorante.isPrenotazioneOnline());

                int rowsAffected = stmt.executeUpdate();
                return (rowsAffected > 0);

        }catch (SQLException e) {
            System.err.println("Errore durante la ricerca dei ristoranti: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeRistorante(Ristorante ristorante) {

        String sqlRemoveRist =  "DELETE FROM Ristorante WHERE IdRistorante = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRemoveRist);
        ){
            stmt.setInt(1, ristorante.getIdRistorante());
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        }catch (SQLException e) {
            System.err.println("Errore durante la ricerca dei ristoranti: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Ristorante> getRistoranteByIdRistoratore(int  idRistoratore) {
        String sqlGetRistById = "SELECT * FROM Ristorante WHERE IdRistoratore = ?";
        List<Ristorante> risultati = new ArrayList<>();

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlGetRistById);
        ) {
            stmt.setInt(1, idRistoratore);

            try (ResultSet rs = stmt.executeQuery();) { // try fatto apposta per andare a chiudere il cursore nel DB postgres
                while (rs.next()) {
                    risultati.add(mapResultSetToRistorante(rs));
                }
            }
            if(!risultati.isEmpty()){
                return risultati;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean modificaRistorante(Ristorante ristorante) {

        String sqlUpdateRist = "UPDATE Ristorante SET idristoratore = ?,nome = ?,citta = ?,indirizzo = ?,ncivico = ?,cap = ?,latitudine = ?,longitudine = ?,email = ?,telfono = ?,websiteurl = ?,descrizione = ?,servizi = ?,numerostelle = ?,fasciaprezzi = ?,tipocucina = ?,delivery = ?,prenotazioneOnline = ? WHERE idristorante = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlUpdateRist);
        ) {
            stmt.setInt(1, ristorante.getIdRistoratore());
            stmt.setString(2, ristorante.getNome());
            stmt.setString(3, ristorante.getCitta());
            stmt.setString(4, ristorante.getIndirizzo());
            stmt.setString(5,ristorante.getnCivico());
            stmt.setString(6,ristorante.getCap());
            stmt.setDouble(7, ristorante.getLatitudine());
            stmt.setDouble(8, ristorante.getLongitudine());
            stmt.setString(9, ristorante.getEmail());
            stmt.setString(10, ristorante.getTelefono());
            stmt.setString(11,ristorante.getWebsiteUrl());
            stmt.setString(12,ristorante.getDescrizione());
            stmt.setString(13,ristorante.getServizi());
            stmt.setInt(14,ristorante.getNumeroStelle());
            stmt.setString(15,ristorante.getFasciaPrezzi());
            stmt.setString(16,ristorante.getTipoCucina());
            stmt.setBoolean(17,ristorante.isDelivery());
            stmt.setBoolean(18,ristorante.isPrenotazioneOnline());
            stmt.setInt(19,ristorante.getIdRistoratore());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ristorante riepilogoRistorante(int idRistorante) {
        String sqlRiepilogoRist = "SELECT * FROM Ristorante WHERE IdRistorante = ?";
        Ristorante ristorante = null;

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRiepilogoRist);
        ) {
            stmt.setInt(1,idRistorante);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ristorante = mapResultSetToRistorante(rs);
                }
            }
            return ristorante;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Metodo helper per mappare il ResultSet
    private Ristorante mapResultSetToRistorante(ResultSet rs) throws SQLException {
        Ristorante r = new Ristorante();
        r.setIdRistorante(rs.getInt("IdRistorante"));
        r.setIdRistoratore(rs.getInt("IdRistoratore"));
        r.setNome(rs.getString("Nome"));
        r.setCitta(rs.getString("Citta"));
        r.setIndirizzo(rs.getString("Indirizzo"));
        r.setnCivico(rs.getString("Ncivico"));
        r.setCap(rs.getString("Cap"));
        r.setLatitudine(rs.getDouble("Latitudine"));
        r.setLongitudine(rs.getDouble("Longitudine"));
        r.setEmail(rs.getString("Email"));
        r.setTelefono(rs.getString("Telefono"));
        r.setWebsiteUrl(rs.getString("WebsiteUrl"));
        r.setDescrizione(rs.getString("Descrizione"));
        r.setServizi(rs.getString("Servizi"));
        r.setNumeroStelle(rs.getInt("NumeroStelle"));
        r.setFasciaPrezzi(rs.getString("FasciaPrezzi"));
        r.setTipoCucina(rs.getString("TipoCucina"));
        r.setDelivery(rs.getBoolean("Delivery"));
        r.setPrenotazioneOnline(rs.getBoolean("PrenotazioneOnline"));
        return r;
    }

    // Stub del tuo metodo coordinate (da implementare o importare)
    private double[] coordinate(String luogo) {
        // La tua logica di Geocoding API qui
        return new double[]{0.0, 0.0};
    }
}