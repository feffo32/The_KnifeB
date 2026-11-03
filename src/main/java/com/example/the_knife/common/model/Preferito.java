package com.example.the_knife.common.model;

import java.io.Serializable;

// Rappresenta la relazione N:N tra `Utente` (cliente) e `Ristorante` per la lista dei preferiti

public class Preferito implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCliente;
    private int idRistorante;

    public Preferito() {}

    public Preferito(int idCliente, int idRistorante) {
        this.idCliente = idCliente;
        this.idRistorante = idRistorante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdRistorante() {
        return idRistorante;
    }

    public void setIdRistorante(int idRistorante) {
        this.idRistorante = idRistorante;
    }
}
