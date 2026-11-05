package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Ristorante;
import java.util.List;

public interface AddPrefDAO {
    public void  addPrefe (Ristorante ristorante) ;
    public void  removePrefe (Ristorante ristorante);
    public List<Ristorante> prefUte () ;
}
