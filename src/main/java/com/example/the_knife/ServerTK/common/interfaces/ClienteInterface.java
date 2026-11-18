package com.example.the_knife.ServerTK.common.interfaces;

import com.example.the_knife.ServerTK.common.model.Prenotazione;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;

import java.rmi.RemoteException;

public interface ClienteInterface extends UtenteInterface {

    public void aggiungiRecensione (Recensione newRecensione, Ristorante ristorante) throws RemoteException;
    public void removeRecensione (Recensione recensione, Ristorante ristorante) throws RemoteException;
    public void modificaRecensione (String testoRecensione, int valutazione) throws  RemoteException;
    public boolean prenota (Prenotazione prenotazione) throws RemoteException;
    public boolean annullaPrenotazione (Prenotazione prenotazione) throws RemoteException;
    public boolean modificaPrenotazione (Prenotazione prenotazione, Prenotazione newPrenotazione) throws RemoteException;
}
