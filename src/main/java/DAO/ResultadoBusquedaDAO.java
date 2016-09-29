package DAO;

import Model.ResultadoBusqueda;
import com.mongodb.DB;

/**
 * Created by fede on 15/09/16.
 */
public interface ResultadoBusquedaDAO {

    void persistirResultadoBusqueda(ResultadoBusqueda unResultado, DB db);

}
