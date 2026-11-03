package com.example.the_knife.common.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prenotazione implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idPrenotazione;
    private int idCliente;
    private int idRistorante;
    private LocalDate data;
    private LocalTime ora;
    private String nominativo;
    private String telefono;
    private String infoAggiuntive;

    public Prenotazione() {}

    public Prenotazione(int idPrenotazione, int idCliente, int idRistorante, LocalDate data, LocalTime ora,
                        String nominativo, String telefono, String infoAggiuntive) {
        this.idPrenotazione = idPrenotazione;
        this.idCliente = idCliente;
        this.idRistorante = idRistorante;
        this.data = data;
        this.ora = ora;
        this.nominativo = nominativo;
        this.telefono = telefono;
        this.infoAggiuntive = infoAggiuntive;
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

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
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
}
