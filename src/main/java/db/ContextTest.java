package db;

import static org.junit.Assert.*;

import POIs.ParadaDeColectivo;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.Persistence;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

   @Test
    public void contextUp() {

        ParadaDeColectivo paradaDel47;
        paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
        paradaDel47.setDireccion("Corvalan 3691");
        paradaDel47.setNombre("Parada del 47");
        paradaDel47.setID(1);
        persist(paradaDel47);
    }

}