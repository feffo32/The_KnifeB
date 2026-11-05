package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Ristorante;
import java.util.List;

public interface RistoranteDAO {

    public List<Ristorante> cercaRistorante (String luogo, String name, String tipoCucina, String fasciaPrezzo, boolean delivery, boolean onlineService, boolean bookOnline) ;
    public List<Ristorante> cercaTop10 () ;
    public boolean aggiungiRistorante ( Ristorante ristorante ) ;
    public boolean removeRistorante ( Ristorante ristorante ) ;
    public List<Ristorante> getRistoranteByIdRistoratore (String idRistoratore ) ;
    public void modificaRistorante (Ristorante ristorante) ;
    public Ristorante riepilogoRistorante (int idRistorante) ;
}
