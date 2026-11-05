package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Recensione;
import com.example.the_knife.common.model.Ristorante;

import java.util.List;

public interface RecensioneDAO {

    public List<Recensione> visualizzaRecensioneByRistorante (Ristorante ristorante);
    public void rispondiRecensione (Recensione recensione) ;
    public void removeRispostaRec (Recensione recensione) ;
    public void modificaRispostaRec (Recensione recensione) ;
    public void aggiungiRecensione (Recensione newRecensione, Ristorante ristorante) ;
    public void removeRecensione (Recensione recensione,  Ristorante ristorante) ;
    public void modificaRecensione (String testoRecensione, int valutazione);
}
