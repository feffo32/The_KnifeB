package com.example.the_knife.ClientTK.common.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

public class Prenotazione implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idPrenotazione;
    private int idCliente;
    private int idRistorante;
    private LocalDate data;
    private Time ora;
    private String nominativo;
    private String telefono;
    private String infoAggiuntive;
    private String fasciaOraria;
    private int numeroPersone;

    public Prenotazione() {}

    public Prenotazione(int idPrenotazione, int idCliente, int idRistorante, LocalDate data, Time ora,
                        String nominativo, String telefono, String infoAggiuntive, String fasciaOraria, int numeroPersone ) {
        this.idPrenotazione = idPrenotazione;
        this.idCliente = idCliente;
        this.idRistorante = idRistorante;
        this.data = data;
        this.ora = ora;
        this.nominativo = nominativo;
        this.telefono = telefono;
        this.infoAggiuntive = infoAggiuntive;
        this.fasciaOraria = fasciaOraria;
        this.numeroPersone = numeroPersone;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdRistorante() {
        return idRistorante;
    }

    public void setIdRistorante(int idRistorante) {
        this.idRistorante = idRistorante;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInfoAggiuntive() {
        return infoAggiuntive;
    }

    public void setInfoAggiuntive(String infoAggiuntive) {
        this.infoAggiuntive = infoAggiuntive;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(String fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }
}
