package com.example.the_knife.common.interfaces;

import com.example.the_knife.common.model.Recensione;
import com.example.the_knife.common.model.Ristorante;
import com.example.the_knife.common.model.Utente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UtenteInterface extends Remote {

    public void modificaUte (Utente utente) throws RemoteException;
    public boolean verificaPassword (String username, String password) throws RemoteException;
    public void  addPrefe (Ristorante ristorante) throws RemoteException;
    public void  removePrefe (Ristorante ristorante) throws RemoteException;
    public List<Ristorante> cercaRistorante (String luogo, String name, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean onlineService, boolean bookOnline) throws RemoteException;
    public List<Ristorante> cercaTop10 () throws RemoteException;
    public List<Ristorante> prefUte () throws RemoteException;
    public List<Recensione> visualizzaRecensioneByRistorante (Ristorante ristorante) throws RemoteException;
    public Utente riepilogoUtente (int idUtente) throws RemoteException;

}

