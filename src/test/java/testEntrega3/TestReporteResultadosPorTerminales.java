package testEntrega3;



import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.*;
import ObserversTerminal.AlmacenarBusqueda;
import ObserversTerminal.ReporteTotalesPorUsuario;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import Repos.RepositorioBusquedas;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import de.flapdoodle.embedmongo.runtime.Network;

import org.junit.After;

public class TestReporteResultadosPorTerminales {
	
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private PolygonAdapter	zonaComuna8;
	private ReporteTotalesPorUsuario observerReportesTotales;
	private AlmacenarBusqueda observerAlmacenarBusqueda;
	private GestorConsultas gestorConsultas;
	private RepositorioBusquedas repositorioBusquedas;
	static int PORT;
	static MongodProcess mongod;
	
	@Before
	public void init() throws Exception{
	//Abro conexion con Mongodb
		PORT = 27017;
		MongodConfig config = new MongodConfig(Version.V2_0, PORT, Network.localhostIsIPv6());
		MongodExecutable prepared = MongoDBRuntime.getDefaultInstance().prepare(config);
		mongod = prepared.start();
	
	// Comuna 8
	comuna8 = new Comuna(new Long(8));
	zonaComuna8 = new PolygonAdapter(new Long(1));
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
			
	//Agrega POIs al repositorioPOIs
	RepositorioPOIs.getInstance().agregarPOI(paradaDel47);
	RepositorioPOIs.getInstance().agregarPOI(cgp);
	RepositorioPOIs.getInstance().agregarPOI(banco);
	RepositorioPOIs.getInstance().agregarPOI(libreriaEscolar);
	RepositorioPOIs.getInstance().agregarPOI(kioskoDeDiarios);
	
	//Observer
	observerReportesTotales = new ReporteTotalesPorUsuario();
	observerAlmacenarBusqueda = new AlmacenarBusqueda();
	
	//Terminales
	terminalAbasto = new Terminal("Terminal Abasto");
	terminalAbasto.addObserver(observerAlmacenarBusqueda);
	terminalAbasto.addObserver(observerReportesTotales);
	terminalFlorida = new Terminal("Terminal Florida");
	terminalFlorida.addObserver(observerReportesTotales);
	terminalFlorida.addObserver(observerAlmacenarBusqueda);
	
	gestorConsultas = new GestorConsultas();
	
	repositorioBusquedas = RepositorioBusquedas.getInstance();
	repositorioBusquedas.setContador(new Long(1));
	
	}
	
	@Test
	public void laTerminalAbastoContieneDosResultados(){
		gestorConsultas.consultarPOIsXTiempoEstimado("deposito", 0,terminalAbasto);
		gestorConsultas.consultarPOIsXTiempoEstimado("caramelos", 0,terminalAbasto);
		observerReportesTotales.generarTotalesPorUsuario(terminalAbasto);
		int resultados = observerReportesTotales.generarReporteTotalPorTerminal(terminalAbasto);
		Assert.assertEquals(2, resultados);
	}

	@After
	public void tearDown(){
		RepositorioPOIs.resetPOIs();
		RepositorioTerminales.resetTerminales();
		RepositorioBusquedas.resetBusquedas();
		RepositorioBusquedas.getInstance().borrarTodasLasBusquedas();
		if (mongod != null) mongod.stop();
	}
	
}
