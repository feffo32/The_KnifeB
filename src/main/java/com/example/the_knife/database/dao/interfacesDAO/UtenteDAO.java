package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Utente;



public interface UtenteDAO {

    public Utente login (String username, String password) ;
    public boolean register (Utente utente) ;
    public boolean deleteUtente (Utente utente) ;
    public boolean modificaUte (Utente utente) ;
    public boolean verificaPassword (String username, String password) ;
    public Utente riepilogoUtente (int idUtente) ;

}
