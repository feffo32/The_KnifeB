package com.example.the_knife.ServerTK.common.ImplRemoteInterface;

import com.example.the_knife.Exceptions.ModificaUteException;
import com.example.the_knife.Exceptions.VerificaPasswordException;
import com.example.the_knife.ServerTK.common.interfaces.UtenteInterface;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;
import com.example.the_knife.ServerTK.common.model.Utente;
import com.example.the_knife.ServerTK.database.dao.implDAO.UtenteDAOImpl;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.UtenteDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UtenteInterfaceImpl extends UnicastRemoteObject implements UtenteInterface {

    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO;

    public UtenteInterfaceImpl() throws RemoteException {
        super();
        this.utenteDAO = new UtenteDAOImpl();
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
    public void addPrefe(Ristorante ristorante) throws RemoteException {

    }

    @Override
    public void removePrefe(Ristorante ristorante) throws RemoteException {

    }

    @Override
    public List<Ristorante> cercaRistorante(String luogo, String name, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean onlineService, boolean bookOnline) throws RemoteException {
        return List.of();
    }

    @Override
    public List<Ristorante> cercaTop10() throws RemoteException {
        return List.of();
    }

    @Override
    public List<Ristorante> prefUte() throws RemoteException {
        return List.of();
    }

    @Override
    public List<Recensione> visualizzaRecensioneByRistorante(Ristorante ristorante) throws RemoteException {
        return List.of();
    }

    @Override
    public Utente riepilogoUtente(int idUtente) throws RemoteException {
        return null;
    }
}
