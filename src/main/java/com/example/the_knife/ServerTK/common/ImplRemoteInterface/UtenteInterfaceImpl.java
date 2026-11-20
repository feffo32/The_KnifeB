package com.example.the_knife.ServerTK.common.ImplRemoteInterface;

import com.example.the_knife.Exceptions.*;
import com.example.the_knife.ServerTK.common.interfaces.UtenteInterface;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;
import com.example.the_knife.ServerTK.common.model.Utente;
import com.example.the_knife.ServerTK.database.dao.implDAO.AddPrefDAOImpl;
import com.example.the_knife.ServerTK.database.dao.implDAO.RecensioneDAOImpl;
import com.example.the_knife.ServerTK.database.dao.implDAO.RistoranteDAOImpl;
import com.example.the_knife.ServerTK.database.dao.implDAO.UtenteDAOImpl;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.AddPrefDAO;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.RecensioneDAO;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.RistoranteDAO;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.UtenteDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UtenteInterfaceImpl extends UnicastRemoteObject implements UtenteInterface {

    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO;
    private final AddPrefDAO addPrefDAO;
    private final RistoranteDAO ristoranteDAO;
    private final RecensioneDAO recensioneDAO;

    public UtenteInterfaceImpl() throws RemoteException {
        super();
        this.addPrefDAO = new AddPrefDAOImpl();
        this.utenteDAO = new UtenteDAOImpl();
        this.ristoranteDAO = new RistoranteDAOImpl();
        this.recensioneDAO = new RecensioneDAOImpl();
    }

    @Override
    public void modificaUte(Utente utente) throws RemoteException, ModificaUteException {
        try {
            if (!utenteDAO.modificaUte(utente)) {
                throw new ModificaUteException("Impossibile modificare i dati dell'utente.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace(); // Log completo su serverTK
            throw new RemoteException("Errore interno del server durante la modifica utente");
        }
    }

    @Override
    public boolean verificaPassword(String username, String password) throws RemoteException {
            try {
                if(!utenteDAO.verificaPassword(username, password)){
                    throw new VerificaPasswordException("Impossibile modificare la password dell'utente");
                }
                return true;
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void addPrefe(Ristorante ristorante, int IdUte) throws RemoteException {
        try{
            if(!addPrefDAO.addPrefe(ristorante,IdUte)){
                throw new AddPrefeException("Impossibile aggiunegere ai preferiti il ristorante");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removePrefe(Ristorante ristorante, int idUte) throws RemoteException {
        try{
            if(!addPrefDAO.removePrefe(ristorante, idUte)){
                throw new RemovePrefeException("Impossibile rimuovere dai preferiti il ristorante");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ristorante> cercaRistorante(String luogo, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean bookOnline) throws RemoteException {

        try{
            List<Ristorante> list = ristoranteDAO.cercaRistorante(luogo, tipoCucina, fasciaPrezzo, delivery, bookOnline);
            if(list.isEmpty()){
                throw new CercaRistorantiException("Impossibile cercare dei ristoranti");
            }
            return list;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ristorante> cercaTop10() throws RemoteException {
        try{
            List<Ristorante> list = ristoranteDAO.cercaTop10();
            if(list.isEmpty()){
                throw new CercaRistorantiException("Impossibile cercare dei ristoranti");
            }
            return list;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ristorante> prefUte(int IdUte) throws RemoteException {
        try{
            List<Ristorante> list = addPrefDAO.prefUte(IdUte);
            if(list.isEmpty()){
                throw new PrefeUteException("Impossibile recuperare i ristoranti preferiti dell'utente");
            }
            return list;
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Recensione> visualizzaRecensioneByRistorante(Ristorante ristorante) throws RemoteException {
        try{
            List<Recensione> list = recensioneDAO.visualizzaRecensioneByRistorante(ristorante);
            if(list.isEmpty()){
                throw new VisualizzaRecensioneByRistoranteException("Impossibile visualizzare la recensione");
            }
            return list;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente riepilogoUtente(int idUtente) throws RemoteException {
        try{
            Utente ute = utenteDAO.riepilogoUtente(idUtente);
            if(ute == null){
                throw new RiepilogoUtenteException("Impossibile visualizzare i dati dell'utente");
            }
            return ute;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
