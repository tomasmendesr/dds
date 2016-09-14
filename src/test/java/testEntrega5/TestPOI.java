package testEntrega5;

import javax.persistence.*;
import Master.POI;
import Master.RepositorioPOIs;
import POIs.*;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import db.EntityManagerHelper;
import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static junit.framework.TestCase.assertNotNull;

public class TestPOI {
    ParadaDeColectivo paradaDel47;
    private Comuna comuna8;
    private Polygon zonaComuna8;
    private CGP cgp;
    private Banco banco;
    RepositorioPOIs repositorioPOIs;

    @Before
    public void init() {
        // Comuna 8
        comuna8 = new Comuna(8);
        zonaComuna8 = new Polygon();
        zonaComuna8.add(new Point(-34.6744, -58.5025));
        zonaComuna8.add(new Point(-34.6578, -58.4787));
        zonaComuna8.add(new Point(-34.6648, -58.4697));
        zonaComuna8.add(new Point(-34.6621, -58.4240));
        zonaComuna8.add(new Point(-34.7048, -58.4612));
        comuna8.setZona(zonaComuna8);
        // Parada del 47 -- Corvalan 3691
        paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
        paradaDel47.setDireccion("Corvalan 3691");
        paradaDel47.setNombre("Parada del 47");
        paradaDel47.setID(1);
        // CGP que provee Asesoramiento Legal -- Av Escalada 3100
        cgp = new CGP(new Point(-34.6672, -58.4669));
        cgp.setDireccion("Av Escalada 3100");
        cgp.setNombre("Asterisco");
        cgp.setComuna(comuna8);
        cgp.addTag("asesoramiento");
        cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
        // Banco -- Av Riestra 5002
        banco = new Banco(new Point(-34.6719, -58.4695));
        banco.addTag("deposito");
        banco.setNombre("Banco Nacion");
        banco.setComuna(comuna8);

        //Inicializo el repo de POIs

        repositorioPOIs = RepositorioPOIs.getInstance();

    }

    /*@Test
    public void TestGuardoPOI(){
        repositorioPOIs.agregar(paradaDel47);
    }
    
     @Test
	public void verificarPOIGuardado(){
		repositorioPOIs.agregar(paradaDel47); //Esta paradaDel47 tiene ID=1 => Busco parada con mismo Id y comparo
		POI unaParadaDel47 = repositorioPOIs.buscar(1);
		assertEquals(new Long(1), new Long(unaParadaDel47.getID()));
		assertEquals("Corvalan 3961", unaParadaDel47.getDireccion());
		assertEquals("Parada del 47", unaParadaDel47.getNombre());
		}
    */
    
}  

