package com.example.the_knife.database.dao.implDAO;

import com.example.the_knife.common.model.Utente;
import com.example.the_knife.database.DBConnection;
import com.example.the_knife.database.dao.interfacesDAO.UtenteDAO;

import java.sql.*;

public class UtenteDAOImpl implements UtenteDAO {


    @Override
    public Utente login(String username, String password) {
        String sql = "SELECT * FROM Utente WHERE username = ? AND password = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()) {
                    return mapResultSetToUtente(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean register(Utente utente) {

        String sqlInsert = "INSERT INTO Utente ( Nome, Cognome, Email, Username, Password, " +
                "DataNascita, Telefono, Citta, Stato, Indirizzo, Ncivico, Cap, Ruolo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();){
            // Disabilitiamo l'autocommit per gestire manualmente la transazione
            conn.setAutoCommit(false);

            try(PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                stmt.setString(1, utente.getNome());
                stmt.setString(2, utente.getCognome());
                stmt.setString(3, utente.getEmail());
                stmt.setString(4, utente.getUsername());
                stmt.setString(5, utente.getPassword());
                stmt.setDate(6, Date.valueOf(utente.getDataNascita()));
                stmt.setString(7, utente.getTelefono());
                stmt.setString(8, utente.getCitta());
                stmt.setString(9, utente.getStato());
                stmt.setString(10, utente.getIndirizzo());
                stmt.setString(11, utente.getnCivico());
                stmt.setString(12, utente.getCap());
                stmt.setString(13, utente.getRuolo());

                int rowsAffected = stmt.executeUpdate();

                // Se l'inserimento ha avuto successo, confermiamo la transazione
                conn.commit();
                return rowsAffected > 0;
            } catch (SQLException e){
                // In caso di errore (es. Username/Email duplicati), annulliamo la transazione
                conn.rollback();
                System.err.println("Errore/Conflitto durante la registrazione: " + e.getMessage());
                return false;
            }

        }
        catch (SQLException e) {
            System.err.println("Errore di connessione durante la registrazione: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUtente(Utente utente) {
        return false;
    }

    @Override
    public void modificaUte(Utente utente) {

    }

    @Override
    public boolean verificaPassword(String username, String password) {
        return false;
    }

    @Override
    public Utente riepilogoUtente(int idUtente) {
        return null;
    }


    private Utente mapResultSetToUtente(ResultSet rs) throws SQLException {
        Utente utente = new Utente();
        utente.setIdUtente(rs.getInt("idutente"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setEmail(rs.getString("email"));
        utente.setUsername(rs.getString("username"));
        utente.setPassword(rs.getString("password"));
        Date dataNascitaSql = rs.getDate("datanascita");
        if (dataNascitaSql != null) {
            utente.setDataNascita(dataNascitaSql.toLocalDate());
        }
        utente.setTelefono(rs.getString("telefono"));
        utente.setCitta(rs.getString("citta"));
        utente.setStato(rs.getString("stato"));
        utente.setIndirizzo(rs.getString("indirizzo"));
        utente.setnCivico(rs.getString("ncivico"));
        utente.setCap(rs.getString("cap"));
        utente.setRuolo(rs.getString("ruolo"));


        return utente;
    }
}

