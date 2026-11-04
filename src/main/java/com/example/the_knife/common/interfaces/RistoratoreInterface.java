package com.example.the_knife.common.interfaces;

import com.example.the_knife.common.model.Recensione;
import com.example.the_knife.common.model.Ristorante;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RistoratoreInterface extends UtenteInterface{
    public boolean aggiungiRistorante ( Ristorante ristorante ) throws RemoteException;
    public boolean removeRistorante ( Ristorante ristorante ) throws RemoteException;
    public List<Ristorante> getRistoranteById (String idRistoratore ) throws RemoteException;
    public void rispondiRecensione (Recensione recensione) throws RemoteException;
    public void removeRispostaRec (Recensione recensione) throws RemoteException;
    public void modificaRispostaRec (Recensione recensione) throws RemoteException;
    public void modificaRistorante (Ristorante ristorante) throws RemoteException;
    public Ristorante riepilogoRistorante (int idRistorante) throws RemoteException;
}
