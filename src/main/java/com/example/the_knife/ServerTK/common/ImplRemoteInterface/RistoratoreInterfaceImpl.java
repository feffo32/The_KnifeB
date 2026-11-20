package com.example.the_knife.ServerTK.common.ImplRemoteInterface;

import com.example.the_knife.Exceptions.DeleteException;
import com.example.the_knife.Exceptions.InsertException;
import com.example.the_knife.Exceptions.ModificaException;
import com.example.the_knife.ServerTK.common.interfaces.RistoratoreInterface;
import com.example.the_knife.ServerTK.common.model.Recensione;
import com.example.the_knife.ServerTK.common.model.Ristorante;
import com.example.the_knife.ServerTK.database.dao.implDAO.RecensioneDAOImpl;
import com.example.the_knife.ServerTK.database.dao.implDAO.RistoranteDAOImpl;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.RecensioneDAO;
import com.example.the_knife.ServerTK.database.dao.interfacesDAO.RistoranteDAO;

import java.rmi.RemoteException;
import java.util.List;

public class RistoratoreInterfaceImpl extends UtenteInterfaceImpl implements RistoratoreInterface {

    private static final long serialVersionUID = 1L;
    private final RistoranteDAO ristoDAO;
    private final RecensioneDAO receDAO;


    public RistoratoreInterfaceImpl() throws RemoteException {
        super();
        this.ristoDAO = new RistoranteDAOImpl();
        this.receDAO = new RecensioneDAOImpl();
    }

    @Override
    public boolean aggiungiRistorante(Ristorante ristorante) throws RemoteException, InsertException {
        try{
            if(!ristoDAO.aggiungiRistorante(ristorante)){
                throw new InsertException("Impossibile aggiungere il ristorante");
            }
            return true;
        }catch (RuntimeException e){
            e.printStackTrace(); // Log completo su serverTK
            throw new RemoteException("Errore interno del server durante la aggiunta di un ristorante");
        }
    }

    @Override
    public boolean removeRistorante(Ristorante ristorante) throws RemoteException, DeleteException{
        try{
            if(!ristoDAO.removeRistorante(ristorante)){
                throw new DeleteException("Impossibile rimuovere il ristorante");
            }
            return true;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante la rimozione del ristorante");
        }
    }

    @Override
    public List<Ristorante> getRistoranteByIdRistoratore(int idRistoratore) throws RemoteException {
        try{
            return ristoDAO.getRistoranteByIdRistoratore(idRistoratore);
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante la ricerca del ristorante");
        }
    }

    @Override
    public boolean rispondiRecensione(Recensione recensione) throws RemoteException, InsertException {
        try{
            if(!receDAO.rispondiRecensione(recensione)){
                throw new InsertException("Impossibile rispondere alla recensione");
            }
            return true;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante l'inserimento della risposta alla recensione");
        }
    }

    @Override
    public boolean removeRispostaRec(Recensione recensione) throws RemoteException, DeleteException {
        try{
            if(!receDAO.removeRispostaRec(recensione)){
                throw new DeleteException("Impossibile rimuovere il la risposta alla recensione");
            }
            return true;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante la rimozione della risposta alla recensione");
        }
    }

    @Override
    public boolean modificaRispostaRec(Recensione recensione) throws RemoteException, ModificaException {
        try{
            if(!receDAO.modificaRispostaRec(recensione)){
                throw new ModificaException("Impossibile modificare la recensione");
            }
            return true;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante la modifica della risposta alla recensione");
        }
    }

    @Override
    public boolean modificaRistorante(Ristorante ristorante) throws RemoteException, ModificaException {
        try{
            if(!ristoDAO.modificaRistorante(ristorante)){
                throw new ModificaException("Impossibile modificare il ristorante");
            }
            return true;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante la rimozione dela risposta alla recensione");
        }
    }

    @Override
    public Ristorante riepilogoRistorante(int idRistorante) throws RemoteException {
        try{
            return ristoDAO.riepilogoRistorante(idRistorante);
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RemoteException("Errore interno del server durante il riepilogo del ristorante");
        }
    }
}
