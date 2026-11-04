package com.example.the_knife.common.interfaces;

import com.example.the_knife.common.model.Utente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthInterface extends Remote {

    public Utente login (String username, String password) throws RemoteException;
    public boolean register (Utente utente) throws RemoteException;
    public boolean deleteUtente (Utente utente) throws RemoteException;
}
