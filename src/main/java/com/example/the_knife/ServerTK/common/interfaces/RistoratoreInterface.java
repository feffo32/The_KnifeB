package com.example.the_knife.ServerTK.common.interfaces;

import com.example.the_knife.Exceptions.DeleteException;
import com.example.the_knife.Exceptions.InsertException;
import com.example.the_knife.Exceptions.ModificaException;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;

import java.rmi.RemoteException;
import java.util.List;

public interface RistoratoreInterface extends UtenteInterface {
    public boolean aggiungiRistorante ( Ristorante ristorante ) throws RemoteException, InsertException;
    public boolean removeRistorante ( Ristorante ristorante ) throws RemoteException, DeleteException;
    public List<Ristorante> getRistoranteByIdRistoratore (int idRistoratore ) throws RemoteException;
    public boolean rispondiRecensione (Recensione recensione) throws RemoteException, InsertException;
    public boolean removeRispostaRec (Recensione recensione) throws RemoteException, DeleteException;
    public boolean modificaRispostaRec (Recensione recensione) throws RemoteException, ModificaException;
    public boolean modificaRistorante (Ristorante ristorante) throws RemoteException, ModificaException;
    public Ristorante riepilogoRistorante (int idRistorante) throws RemoteException;
}
