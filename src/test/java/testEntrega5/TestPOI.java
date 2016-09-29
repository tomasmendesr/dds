package testEntrega5;

import javax.persistence.*;

import POIs.*;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;
import POIsExt.Servicio;
import Repos.RepositorioPOIs;
import db.EntityManagerHelper;
import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Adapters.PolygonAdapter;
import Converter.PointConverter;
import Model.POI;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestPOI {
    ParadaDeColectivo paradaDel47;
    private Comuna comuna8;
    private PolygonAdapter zonaComuna8;
    private CGP cgp;
    private Banco banco;
    RepositorioPOIs repositorioPOIs;
    private Servicio servicio;
    private RangoDeAtencion rangoUno;
    private RangoDeAtencion rangoDos;
    private List<RangoDeAtencion> listaRangos;

    @Before
    public void init() {
        // Comuna 8
		comuna8 = new Comuna(8);
		zonaComuna8 = new PolygonAdapter();
		zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
		zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
		zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
		zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
		zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
        // Parada del 47 -- Corvalan 3691
        paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
        paradaDel47.setDireccion("Corvalan 3691");
        paradaDel47.setNombre("Parada del 47");
        //paradaDel47.setID(1);
        paradaDel47.setUbicacion(new Point(-34.6715, -58.4676));
        paradaDel47.addTag("47");
        paradaDel47.setComuna(comuna8);
        
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
        
        rangoUno = new RangoDeAtencion(1,10,0,15,0);
        rangoDos = new RangoDeAtencion(2,10,0,15,0);
        listaRangos = new ArrayList<RangoDeAtencion>();
        listaRangos.add(rangoUno);
        listaRangos.add(rangoDos);
        servicio = new Servicio("Rentas" , listaRangos);

        //Inicializo el repo de POIs

        repositorioPOIs = RepositorioPOIs.getInstance();

    }
    
    @Test
    public void testPersistoUnServicioYAdemasSeGuardaSusRangos(){
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "db" );
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	entityManager.getTransaction().begin();
    	entityManager.persist(servicio);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    	
    	entityManager = entityManagerFactory.createEntityManager();
    	entityManager.getTransaction().begin();
    	List<Servicio> result = entityManager.createQuery( "from Servicio", Servicio.class ).getResultList();
    	Assert.assertTrue(result.stream().anyMatch( servicio -> servicio.getNombre() == "Rentas"));
    	Assert.assertTrue(result.stream().anyMatch(servicio -> servicio.estaDisponible(LocalDateTime.of(2016, 9, 27, 12, 30))));
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }


    @Test
    public void testFuncionaBienElPointConverter(){
    	PointConverter convertidor = new PointConverter();
        Point punto = new Point(-34.6715,-58.4676);
        punto.equals(convertidor.convertToEntityAttribute("-34.6715,-58.4676"));
    }

     @Test
	public void testVerificarPOIGuardado(){
		repositorioPOIs.agregar(paradaDel47); //Esta paradaDel47 tiene ID=1 => Busco parada con mismo Id y comparo
		POI unaParadaDel47 = repositorioPOIs.buscarPorNombre("Parada del 47").get(0);
		Assert.assertEquals(new Long(1), new Long(unaParadaDel47.getID()));
		Assert.assertEquals("Corvalan 3691", unaParadaDel47.getDireccion());
		Assert.assertEquals("Parada del 47", unaParadaDel47.getNombre());
		}
    
    
}  

