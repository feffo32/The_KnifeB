package com.example.the_knife.ServerTK.database.dao.interfacesDAO;

import com.example.the_knife.ClientTK.common.model.Recensione;
import com.example.the_knife.ClientTK.common.model.Ristorante;

import java.util.List;

public interface RecensioneDAO {

    public List<Recensione> visualizzaRecensioneByRistorante (Ristorante ristorante);
    public boolean rispondiRecensione (Recensione recensione) ;
    public boolean removeRispostaRec (Recensione recensione) ;
    public boolean modificaRispostaRec (Recensione recensione) ;
    public boolean aggiungiRecensione (Recensione newRecensione) ;
    public boolean removeRecensione (Recensione recensione) ;
    public boolean modificaRecensione (Recensione recensione, String testoRecensione, Integer valutazione);
}
