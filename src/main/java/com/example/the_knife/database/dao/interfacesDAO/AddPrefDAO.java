package com.example.the_knife.database.dao.interfacesDAO;

import com.example.the_knife.common.model.Ristorante;
import java.util.List;

public interface AddPrefDAO {
    public boolean  addPrefe (Ristorante ristorante, int IdUte) ;
    public boolean  removePrefe (Ristorante ristorante, int IdUte) ;
    public List<Ristorante> prefUte (int IdUte) ;
}
