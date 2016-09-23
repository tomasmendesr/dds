package DAO;

import db.EntityManagerHelper;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Model.ResultadoBusqueda;

import javax.persistence.EntityManager;

/**
 * Created by fede on 15/09/16.
 */
public class ResultadoBusquedaDAOImpl implements ResultadoBusquedaDAO {

    public ResultadoBusquedaDAOImpl(){
        entityManager = PerThreadEntityManagers.getEntityManager();
    }

    EntityManager entityManager;

    @Override
    public void persistirResultadoBusqueda(ResultadoBusqueda unResultado) {
        EntityManagerHelper.beginTransaction();
        entityManager.persist(unResultado);
        EntityManagerHelper.commit();
    }
}
