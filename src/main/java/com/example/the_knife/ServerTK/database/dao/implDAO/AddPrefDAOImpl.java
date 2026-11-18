package com.example.the_knife.ServerTK.database.dao.implDAO;

import com.example.the_knife.ClientTK.common.model.Ristorante;
import com.example.the_knife.ServerTK.database.DBConnection;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.AddPrefDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddPrefDAOImpl implements AddPrefDAO {
    @Override
    public boolean addPrefe(Ristorante ristorante, int IdUte) {

        String sqlAddPrefe = "INSERT INTO AddPref (idcliente,idristorante) VALUES  (?,?) ";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlAddPrefe);
        ){
            stmt.setInt(1, IdUte);
            stmt.setInt(2,ristorante.getIdRistorante());

            int rowsAffected = stmt.executeUpdate();
            return  (rowsAffected > 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removePrefe(Ristorante ristorante, int  IdUte) {

        String sqlRemovePrefe = "DELETE FROM AddPref WHERE idcliente = ? AND idristorante = ?";
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlRemovePrefe);
        ){
            stmt.setInt(1, IdUte);
            stmt.setInt(2,ristorante.getIdRistorante());

            int rowsAffected = stmt.executeUpdate();
            return  (rowsAffected > 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ristorante> prefUte(int IdUte) {
        String sqlPrefeUte = "SELECT * FROM Ristorante  " +
                "JOIN AddPref ON Ristorante.idRistorante = AddPref.idRistorante" +
                " WHERE AddPref.idcliente = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlPrefeUte);
        ){
            try (ResultSet rs = stmt.executeQuery()){
                List<Ristorante> listaRistoranti = new ArrayList<>();
                while(rs.next()){
                    listaRistoranti.add(mapResultSetToRistorante(rs));
                }
                return listaRistoranti;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo helper per mappare il ResultSet
    public Ristorante mapResultSetToRistorante(ResultSet rs) throws SQLException {
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
}
