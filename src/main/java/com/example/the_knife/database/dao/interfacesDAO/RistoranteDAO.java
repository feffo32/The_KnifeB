package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Ristorante;
import java.util.List;

public interface RistoranteDAO {

    public List<Ristorante> cercaRistorante (String luogoOrName, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean bookOnline) ;
    public List<Ristorante> cercaTop10 () ;
    public boolean aggiungiRistorante ( Ristorante ristorante ) ;
    public boolean removeRistorante ( Ristorante ristorante ) ;
    public List<Ristorante> getRistoranteByIdRistoratore (int idRistoratore ) ;
    public boolean modificaRistorante (Ristorante ristorante) ;
    public Ristorante riepilogoRistorante (int idRistorante) ;
}
