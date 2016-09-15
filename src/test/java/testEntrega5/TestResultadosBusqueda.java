package testEntrega5;

import db.EntityManagerHelper;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

/**
 * Created by Fede on 9/14/2016.
 */
public class TestResultadosBusqueda {

    EntityManager entityManager;

    @Before
    public void init(){
        entityManager = PerThreadEntityManagers.getEntityManager();
    }

    @Test
    public void sePersisteUnResultadoBusqueda(){
        
    }

}
