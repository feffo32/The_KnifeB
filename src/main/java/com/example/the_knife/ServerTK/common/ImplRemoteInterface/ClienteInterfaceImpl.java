package com.example.the_knife.ServerTK.common.ImplRemoteInterface;

import com.example.the_knife.Exceptions.*;
import com.example.the_knife.ServerTK.common.interfaces.ClienteInterface;
import com.example.the_knife.ServerTK.common.model.Prenotazione;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.database.dao.implDAO.PrenotazioneDAOImpl;
import com.example.the_knife.ServerTK.database.dao.implDAO.RecensioneDAOImpl;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.PrenotazioneDAO;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.RecensioneDAO;

import java.rmi.RemoteException;

public class ClienteInterfaceImpl extends UtenteInterfaceImpl  implements ClienteInterface {
    private static final long serialVersionUID = 1L;
    private final RecensioneDAO recensioneDAO;
    private final PrenotazioneDAO prenotazioneDAO;

    public ClienteInterfaceImpl() throws RemoteException {
        super();
        this.recensioneDAO = new RecensioneDAOImpl();
        this.prenotazioneDAO = new PrenotazioneDAOImpl();
    }

    @Override
    public void aggiungiRecensione(Recensione newRecensione) throws RemoteException {
            if(!recensioneDAO.aggiungiRecensione(newRecensione)){
                throw new AggiungiRecensioneException("Impossibile aggiungere la recensione");
            }
    }

    @Override
    public void removeRecensione(Recensione recensione) throws RemoteException {
            if(!recensioneDAO.removeRecensione(recensione)) {
                throw new RimuoviRecensioneException("Impossibile rimuovere la recensione");
            }
    }

    @Override
    public void modificaRecensione(Recensione rec, String testoRecensione, int valutazione) throws RemoteException {
            if(!recensioneDAO.modificaRecensione(rec,testoRecensione,valutazione)){
                throw new ModificaRecensioneException("Impossibile modificare la recensione");
            }
    }

    @Override
    public boolean prenota(Prenotazione prenotazione) throws RemoteException {
        if(!prenotazioneDAO.prenota(prenotazione)){
            throw new PrenotaException("Impossibile prenotare il ristorante");
        }
        return true;
    }

    @Override
    public boolean annullaPrenotazione(Prenotazione prenotazione) throws RemoteException {
        if(!prenotazioneDAO.prenota(prenotazione)){
            throw new AnnullaPrenotazioneException("Impossibile annullare la prenotazione");
        }
        return true;
    }

    @Override
    public boolean modificaPrenotazione(Prenotazione prenotazione, Prenotazione newPrenotazione) throws RemoteException {
        if(!prenotazioneDAO.modificaPrenotazione(prenotazione,newPrenotazione)){
            throw new ModificaPrenotazioneException("Impossibile modificare la prenotazione");
        }
        return true;
    }

}
