package com.example.the_knife.common.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String username;
    private LocalDate dataNascita; // Il tipo LocalDate rappresenta solo giorno/mese/anno
    private String telefono;
    private String citta;
    private String stato;
    private String indirizzo;
    private String nCivico;
    private String cap;
    private String ruolo; // Cliente o Ristoratore

    public Utente (){}

    public Utente(String ruolo, String cap, String nCivico, String indirizzo, String stato, String citta, LocalDate dataNascita, String telefono,
                  String username, String password, String email, String cognome, String nome, int idUtente) {
        this.ruolo = ruolo;
        this.cap = cap;
        this.nCivico = nCivico;
        this.indirizzo = indirizzo;
        this.stato = stato;
        this.citta = citta;
        this.dataNascita = dataNascita;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.idUtente = idUtente;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCitta() {
        return citta;
    }

    public String getStato() {
        return stato;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getnCivico() {
        return nCivico;
    }

    public String getCap() {
        return cap;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setnCivico(String nCivico) {
        this.nCivico = nCivico;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
