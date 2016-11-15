package testEntrega6;

import Model.GestorConsultas;
import Model.ResultadoBusqueda;
import Model.Terminal;
import ObserversTerminal.AlmacenarBusqueda;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import Repos.RepositorioBusquedas;
import Repos.RepositorioPOIs;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import de.flapdoodle.embedmongo.runtime.Network;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;

public class TestPersistirBusquedasMongo {

	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private PolygonAdapter	zonaComuna8;
	private GestorConsultas gestorConsultas;
	private Terminal terminal;
	private AlmacenarBusqueda observerAlmacenarBusqueda;
	private RepositorioBusquedas repositorioBusquedas;
	private RepositorioPOIs repositorioPOIs;
	static int PORT;
	static MongodProcess mongod;
	
	
	@Before
	public void init()throws Exception{
		
		//Abro conexion con Mongodb
		PORT = 27017;
		MongodConfig config = new MongodConfig(Version.V2_0, PORT, Network.localhostIsIPv6());
		MongodExecutable prepared = MongoDBRuntime.getDefaultInstance().prepare(config);
		mongod = prepared.start();
		
		// Comuna 8
		comuna8 = new Comuna(new Long(8));
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
			
		// CGP que provee Asesoramiento Legal -- Av Escalada 3100
		cgp = new CGP(new Point(-34.6672, -58.4669));
		cgp.setDireccion("Av Escalada 3100");
		cgp.setNombre("Asesoria");
		cgp.setComuna(comuna8);
		cgp.addTag("asesoramiento");
		cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean
		
		// Banco -- Av Riestra 5002
		banco = new Banco(new Point(-34.6719, -58.4695));
		banco.addTag("deposito");
		banco.setComuna(comuna8);
		
		// Libreria Escolar -- Av Argentina 4802
		Rubro rubroLibreriaEscolar = new Rubro(500.0);
		libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
		libreriaEscolar.setComuna(comuna8);
		libreriaEscolar.addTag("libreria");
		
		// Kiosko de Diarios -- Albariño 3702
		Rubro rubroKioskoDeDiarios = new Rubro(200.0);
		kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), rubroKioskoDeDiarios);
		kioskoDeDiarios.setComuna(comuna8);
		kioskoDeDiarios.addTag("caramelos");
		kioskoDeDiarios.setNombre("Kiosko de Carlitos");
		
		gestorConsultas = new GestorConsultas();
		terminal = new Terminal("Terminal Principal");
		terminal.setComuna(comuna8);
		terminal.setId(new Long(1));
		observerAlmacenarBusqueda = new AlmacenarBusqueda();
		terminal.addObserver(observerAlmacenarBusqueda);
		repositorioBusquedas = RepositorioBusquedas.getInstance();
		repositorioBusquedas.setContador(new Long(1));
		repositorioPOIs = RepositorioPOIs.getInstance();
		repositorioPOIs.agregarPOI(libreriaEscolar);
		repositorioPOIs.agregarPOI(banco);
		repositorioPOIs.agregarPOI(cgp);
		repositorioPOIs.agregarPOI(kioskoDeDiarios);
		repositorioPOIs.agregarPOI(paradaDel47);
	}
	
	/*
	@Test
	public void testSeAgregaLaBusquedaDeCaramelosAlRepositorio(){
		gestorConsultas.consultarPOIsXTiempoEstimado("caramelos", 2000, terminal);
		ResultadoBusqueda resultado = repositorioBusquedas.getAllBusquedas().get(0);
		Assert.assertEquals("caramelos", resultado.getFraseBuscada());
	}
	
	@Test
	public void testSePersistenLosLocalesComercialesBuscados(){
		gestorConsultas.consultarPOIsXTiempoEstimado("caramelos", 2000, terminal);
		ResultadoBusqueda resultado = repositorioBusquedas.getAllBusquedas().get(0);
		Assert.assertEquals(1, resultado.cantidadDeResultados());
	}
	
	
	@Test
	public void testSePersistenLosBancosBuscados(){
		gestorConsultas.consultarPOIsXTiempoEstimado("deposito", 2000, terminal);
		ResultadoBusqueda resultado = repositorioBusquedas.getAllBusquedas().get(0);
		Assert.assertEquals("deposito", resultado.getResultadoBusqueda().get(0).getTags().get(0));
	}
	
	@Test
	public void testSePersistenLosCGPsBuscados(){
		gestorConsultas.consultarPOIsXTiempoEstimado("asesoramiento", 2000, terminal);
		ResultadoBusqueda resultado = repositorioBusquedas.getAllBusquedas().get(0);
		Assert.assertEquals("Asesoria", resultado.getResultadoBusqueda().get(0).getNombre());
	}*/
	
    @After
    public void tearDown(){
        RepositorioBusquedas.resetBusquedas();
        RepositorioPOIs.resetPOIs();
        RepositorioBusquedas.getInstance().borrarTodasLasBusquedas();
        if (mongod != null) mongod.stop();
    }

}
