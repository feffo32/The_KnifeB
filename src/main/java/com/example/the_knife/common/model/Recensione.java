package com.example.the_knife.common.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Recensione implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idRecensione;
    private int idCliente;
    private int idRistorante;
    private String testoRecensione;
    private int valutazione; // 1-5 stelle
    private LocalDate dataRecensione;
    private String testoRisposta; // Può essere null se il ristoratore non ha ancora risposto

    public Recensione(){}

    public Recensione(int idRecensione, int idCliente, int idRistorante, String testoRecensione, int valutazione,
                      LocalDate dataRecensione, String testoRisposta) {
        this.idRecensione = idRecensione;
        this.idCliente = idCliente;
        this.idRistorante = idRistorante;
        this.testoRecensione = testoRecensione;
        this.valutazione = valutazione;
        this.dataRecensione = dataRecensione;
        this.testoRisposta = testoRisposta;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
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

    public String getTestoRecensione() {
        return testoRecensione;
    }

    public void setTestoRecensione(String testoRecensione) {
        this.testoRecensione = testoRecensione;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public LocalDate getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(LocalDate dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public String getTestoRisposta() {
        return testoRisposta;
    }

    public void setTestoRisposta(String testoRisposta) {
        this.testoRisposta = testoRisposta;
    }
}
