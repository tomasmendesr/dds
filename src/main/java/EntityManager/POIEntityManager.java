package EntityManager;

import Master.POI;
import javax.persistence.*;
import java.util.*;

public class POIEntityManager {

    public void guardarPOI(POI poi) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(poi);
            tx.commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<POI> buscarPOIs() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        List<POI> pois = new ArrayList<POI>();

        try {
            tx = em.getTransaction();
            tx.begin();
            pois = em.createQuery("from POI").getResultList();
            tx.commit();
        } finally {
            em.close();
            emf.close();
        }

        return pois;
    }

    public POI buscarPOI(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        POI poi = em.find(POI.class, id);
        return poi;
    }

    public void eliminarPOI(Integer id) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "manager1" );
    EntityManager em = emf.createEntityManager( );
    EntityTransaction tx = null;
    POI poi=em.find( POI.class, id);
    em.remove(poi);
    em.getTransaction( ).commit( );
    em.close( );
    emf.close( );
    }

}
