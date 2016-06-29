package testEntrega4;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Master.RepositorioPOIs;
import Master.Terminal;
import ObserversTerminal.ReportePorFecha;
import ObserversTerminal.ReporteTotalesPorUsuario;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import Procesos.AccionesUsuarios;
import Procesos.ResultadoProceso;
import Procesos.SegunComuna;
import Procesos.Seleccionada;
import Procesos.Todas;

import org.junit.Assert;

public class TestProcesoAccionesUsuarios {
	
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	private Terminal terminalLugano;
	private Comuna comuna8;
	private ParadaDeColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Polygon	zonaComuna8;
	private ReporteTotalesPorUsuario observerReportesTotales;
	private AccionesUsuarios accionUsuario;
	private SegunComuna segunComuna;
	private ResultadoProceso resultadoProceso;
	private Todas todasLasTerminales;
	private Seleccionada seleccionadas;
	private ReportePorFecha observerReportePorFecha;
	
	@Before
	public void init(){
	// Comuna 8
	comuna8 = new Comuna(8);
	zonaComuna8 = new Polygon();
	zonaComuna8.add(new Point(-34.6744,-58.5025));
	zonaComuna8.add(new Point(-34.6578,-58.4787));
	zonaComuna8.add(new Point(-34.6648,-58.4697));
	zonaComuna8.add(new Point(-34.6621,-58.4240));
	zonaComuna8.add(new Point(-34.7048,-58.4612));
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
	observerReportePorFecha = new ReportePorFecha();
	
	//Terminales
	terminalAbasto = new Terminal("Terminal Abasto", RepositorioPOIs.getInstance());
	terminalAbasto.setComuna(comuna8);
	terminalAbasto.addObserver(observerReportePorFecha);
	
	terminalFlorida = new Terminal("Terminal Florida", RepositorioPOIs.getInstance());
	terminalFlorida.addObserver(observerReportePorFecha);
	
	terminalLugano = new Terminal("Terminal Lugano", RepositorioPOIs.getInstance());
	terminalLugano.setComuna(comuna8);
	terminalLugano.addObserver(observerReportePorFecha);

	
	// Criterio segun la comuna
	segunComuna = new SegunComuna();
	segunComuna.setComunaSeleccionada(comuna8);

	
	// Criterio para afectar a todas las terminales
	todasLasTerminales = new Todas();
	
	// Criterio para afectar las terminales seleccionadas
	seleccionadas = new Seleccionada();
	}
	
	// Proceso1: Agregar accion ReportesTotales a las terminales segun la comuna
	@Test
	public void agregoObserverReportesTotalesALaTerminalAbastoyLugano(){ 
		accionUsuario = new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(2,terminalAbasto.getObservers().size());
		Assert.assertEquals(2,terminalLugano.getObservers().size());
	}
	
	@Test
	public void noSeAgregaElObserverATerminalFlorida(){ // porque no pertenece a esa comuna
		accionUsuario= new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(1, terminalFlorida.getObservers().size()); // tiene el ReportePorFecha
	}
	
	@Test
	public void laCantidadDeAfectadosEs2(){
		accionUsuario= new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(2, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso2: agregar accion ReportesTotales a todas las terminales
	@Test
	public void agregoElObserverALasTerminales(){
		accionUsuario = new AccionesUsuarios(todasLasTerminales, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(2,terminalAbasto.getObservers().size()); // reportePorFecha y reportestotales
		Assert.assertEquals(2, terminalFlorida.getObservers().size());	
	}
	
	@Test
	public void laCantidadDeAfectadosEs3(){
		accionUsuario= new AccionesUsuarios(todasLasTerminales, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(3, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso3: agregar accion ReportesTotales a las terminales seleccionadas
	@Test
	public void agregoElObserverALaTerminalSeleccionada(){
		accionUsuario= new AccionesUsuarios(seleccionadas, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalAbasto);
		accionUsuario.realizarAccion();
		Assert.assertEquals(2, terminalAbasto.getObservers().size()); // reportePorFecha y reportesTotales
	}
	
	@Test
	public void cantidadDeAfectadosEs1(){
		accionUsuario= new AccionesUsuarios(seleccionadas, "agregar", observerReportesTotales);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalAbasto);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(1, resultadoProceso.getCantElementosAfectados());
	}
	
	
	//Proceso4: quito accion ReportePorFecha de las terminales de la comuna 8
	@Test
	public void quitoAccionDeLasTerminalesDeLacomuna8(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(0, terminalLugano.getObservers().size()); // no tienen ningun observer
		Assert.assertEquals(0, terminalAbasto.getObservers().size());
	}
	
	@Test
	public void quitoAccionDeLasTerminalesDeLaComuna8YTerminalFloridaLaSigueTeniendo(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(1, terminalFlorida.getObservers().size()); 
	}
	
	@Test 
	public void cantidadDeAfectadosEs2(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		resultadoProceso = accionUsuario.realizarAccion();
		Assert.assertEquals(2, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso 6: quito accion ReportesPorFecha de todas las terminales (todas lo tienen)
	@Test
	public void quitoAccionDeLasTerminales(){
		accionUsuario = new AccionesUsuarios(todasLasTerminales, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida);
		accionUsuario.agregarTerminal(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(0,terminalAbasto.getObservers().size()); 
		Assert.assertEquals(0, terminalFlorida.getObservers().size());	
		Assert.assertEquals(0, terminalFlorida.getObservers().size());
	}
	
	
	// Proceso 7: quito accion ReportePorFecha de las terminales seleccionadas
	@Test
	public void quitoAccionDeLasTerminalesSeleccionadas(){
		accionUsuario = new AccionesUsuarios(seleccionadas, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(0, terminalLugano.getObservers().size());
	}
	
	@Test
	public void quitoAccionDeLaTerminalSeleccionadaYLasDemasLaSiguenTeniendo(){
		accionUsuario = new AccionesUsuarios(seleccionadas, "quitar", observerReportePorFecha);
		accionUsuario.agregarTerminal(terminalAbasto);
		accionUsuario.agregarTerminal(terminalFlorida); // Posee todas las terminales
		accionUsuario.agregarTerminal(terminalLugano);
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalLugano);
		accionUsuario.realizarAccion();
		Assert.assertEquals(1, terminalAbasto.getObservers().size());
		Assert.assertEquals(1, terminalFlorida.getObservers().size());
	}
	
}
