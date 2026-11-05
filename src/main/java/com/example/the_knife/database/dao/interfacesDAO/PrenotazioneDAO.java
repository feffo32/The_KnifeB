package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Prenotazione;

public interface PrenotazioneDAO {
    public boolean prenota (Prenotazione prenotazione) ;
    public boolean annullaPrenotazione (Prenotazione prenotazione) ;
    public boolean modificaPrenotazione (Prenotazione prenotazione,Prenotazione newPrenotazione) ;
}
