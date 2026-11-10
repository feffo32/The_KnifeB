package com.example.the_knife.common.model;

import java.io.Serializable;

public class Ristorante implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idRistorante;
    private int idRistoratore;
    private String nome;
    private String citta;
    private String indirizzo;
    private String nCivico;
    private String cap;
    private double latitudine;
    private double longitudine;
    private String email;
    private String telefono;
    private String websiteUrl;
    private String descrizione;
    private String servizi;
    private Integer numeroStelle; // 1-5
    private String fasciaPrezzi;
    private String tipoCucina;
    private boolean delivery;
    private boolean prenotazioneOnline;
    private String stato;

    public Ristorante() {}

    public Ristorante(int idRistorante, int idRistoratore, String nome, String citta, String indirizzo,
                      String nCivico, String cap, double latitudine, double longitudine, String email,
                      String telefono, String websiteUrl, String descrizione, String servizi,
                      Integer numeroStelle, String fasciaPrezzi, String tipoCucina, boolean delivery,
                      boolean prenotazioneOnline, String stato) {
        this.idRistorante = idRistorante;
        this.idRistoratore = idRistoratore;
        this.nome = nome;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.nCivico = nCivico;
        this.cap = cap;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.email = email;
        this.telefono = telefono;
        this.websiteUrl = websiteUrl;
        this.descrizione = descrizione;
        this.servizi = servizi;
        this.numeroStelle = numeroStelle;
        this.fasciaPrezzi = fasciaPrezzi;
        this.tipoCucina = tipoCucina;
        this.delivery = delivery;
        this.prenotazioneOnline = prenotazioneOnline;
        this.stato = stato;
    }

    public int getIdRistorante() {
        return idRistorante;
    }

    public void setIdRistorante(int idRistorante) {
        this.idRistorante = idRistorante;
    }

    public int getIdRistoratore() {
        return idRistoratore;
    }

    public void setIdRistoratore(int idRistoratore) {
        this.idRistoratore = idRistoratore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getnCivico() {
        return nCivico;
    }

    public void setnCivico(String nCivico) {
        this.nCivico = nCivico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getServizi() {
        return servizi;
    }

    public void setServizi(String servizi) {
        this.servizi = servizi;
    }

    public Integer getNumeroStelle() {
        return numeroStelle;
    }

    public void setNumeroStelle(Integer numeroStelle) {
        this.numeroStelle = numeroStelle;
    }

    public String getFasciaPrezzi() {
        return fasciaPrezzi;
    }

    public void setFasciaPrezzi(String fasciaPrezzi) {
        this.fasciaPrezzi = fasciaPrezzi;
    }

    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPrenotazioneOnline() {
        return prenotazioneOnline;
    }

    public void setPrenotazioneOnline(boolean prenotazioneOnline) {
        this.prenotazioneOnline = prenotazioneOnline;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
