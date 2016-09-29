package DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import db.EntityManagerHelper;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Model.ResultadoBusqueda;

import javax.persistence.EntityManager;

/**
 * Created by fede on 15/09/16.
 */
public class ResultadoBusquedaDAOMongo implements ResultadoBusquedaDAO {

    public ResultadoBusquedaDAOMongo(){
        entityManager = PerThreadEntityManagers.getEntityManager();
    }

    EntityManager entityManager;

    @Override
    public void persistirResultadoBusqueda(ResultadoBusqueda unResultado,DB db) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("terminal",unResultado.getTerminalId());
        dbObject.put("fecha",unResultado.getMomentoDeBusqueda());
        dbObject.put("fraseBuscada",unResultado.getFraseBuscada());
        dbObject.put("poisEncontrados",unResultado.getResultadoBusqueda());
        db.getCollection("resultadosBusqueda").save(dbObject);
    }
}
