package com.example.the_knife.ServerTK.common.interfaces;

import com.example.the_knife.Exceptions.ModificaUteException;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;
import com.example.the_knife.ServerTK.common.model.Utente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UtenteInterface extends Remote {

    public void modificaUte (Utente utente) throws RemoteException, ModificaUteException;
    public boolean verificaPassword (String username, String password) throws RemoteException;
    public void  addPrefe (Ristorante ristorante, int IdUte) throws RemoteException;
    public void  removePrefe (Ristorante ristorante, int IdUte) throws RemoteException;
    public List<Ristorante> cercaRistorante (String luogo, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean bookOnline) throws RemoteException;
    public List<Ristorante> cercaTop10 () throws RemoteException;
    public List<Ristorante> prefUte (int IdUte) throws RemoteException;
    public List<Recensione> visualizzaRecensioneByRistorante (Ristorante ristorante) throws RemoteException;
    public Utente riepilogoUtente (int idUtente) throws RemoteException;

}

