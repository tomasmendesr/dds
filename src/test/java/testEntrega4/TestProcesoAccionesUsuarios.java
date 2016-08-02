package testEntrega4;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Master.Identity;
import Master.RepositorioPOIs;
import Master.RepositorioTerminales;
import Master.Terminal;
import ObserversTerminal.ReportePorFecha;
import ObserversTerminal.ReporteTotalesPorUsuario;
import POIsExt.Comuna;
import Procesos.AccionesUsuarios;
import Procesos.ResultadoProceso;
import Procesos.CriterioSegunComuna;
import Procesos.CriterioAfectarSeleccionadas;
import Procesos.CriterioAfectarTodas;

import org.junit.After;
import org.junit.Assert;

public class TestProcesoAccionesUsuarios {
	
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	private Terminal terminalLugano;
	private Comuna comuna8;
	private Polygon	zonaComuna8;
	private ReporteTotalesPorUsuario observerReportesTotales;
	private AccionesUsuarios accionUsuario;
	private CriterioSegunComuna segunComuna;
	private ResultadoProceso resultadoProceso;
	private CriterioAfectarTodas todasLasTerminales;
	private CriterioAfectarSeleccionadas seleccionadas;
	private ReportePorFecha observerReportePorFecha;
	
	@Before
	public void init(){
	
	Identity.initializeIdentity();
		
	// Comuna 8
	comuna8 = new Comuna(8);
	zonaComuna8 = new Polygon();
	zonaComuna8.add(new Point(-34.6744,-58.5025));
	zonaComuna8.add(new Point(-34.6578,-58.4787));
	zonaComuna8.add(new Point(-34.6648,-58.4697));
	zonaComuna8.add(new Point(-34.6621,-58.4240));
	zonaComuna8.add(new Point(-34.7048,-58.4612));
	comuna8.setZona(zonaComuna8);
	
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
	
	//RepositorioTerminales
	RepositorioTerminales.getInstance().addTerminal(terminalAbasto);
	RepositorioTerminales.getInstance().addTerminal(terminalFlorida);
	RepositorioTerminales.getInstance().addTerminal(terminalLugano);
	
	// Criterio segun la comuna
	segunComuna = new CriterioSegunComuna();
	segunComuna.setComunaSeleccionada(comuna8);

	
	// Criterio para afectar a todas las terminales
	todasLasTerminales = new CriterioAfectarTodas();
	
	// Criterio para afectar las terminales seleccionadas
	seleccionadas = new CriterioAfectarSeleccionadas();
	}
	
	// Proceso1: Agregar accion ReportesTotales a las terminales segun la comuna
	@Test
	public void agregoObserverReportesTotalesALaTerminalAbastoyLugano(){ 
		accionUsuario = new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		accionUsuario.realizarProceso();
		Assert.assertEquals(2,terminalAbasto.getObservers().size());
		Assert.assertEquals(2,terminalLugano.getObservers().size());
	}
	
	@Test
	public void noSeAgregaElObserverATerminalFlorida(){ // porque no pertenece a esa comuna
		accionUsuario= new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		accionUsuario.realizarProceso();
		Assert.assertEquals(1, terminalFlorida.getObservers().size()); // tiene el ReportePorFecha
	}
	
	@Test
	public void laCantidadDeAfectadosEs2(){
		accionUsuario= new AccionesUsuarios(segunComuna, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(2, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso2: agregar accion ReportesTotales a todas las terminales
	@Test
	public void agregoElObserverALasTerminales(){
		accionUsuario = new AccionesUsuarios(todasLasTerminales, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		accionUsuario.realizarProceso();
		Assert.assertEquals(2,terminalAbasto.getObservers().size()); // reportePorFecha y reportestotales
		Assert.assertEquals(2, terminalFlorida.getObservers().size());	
	}
	
	@Test
	public void laCantidadDeAfectadosEs3(){
		accionUsuario= new AccionesUsuarios(todasLasTerminales, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(3, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso3: agregar accion ReportesTotales a las terminales seleccionadas
	@Test
	public void agregoElObserverALaTerminalSeleccionada(){
		accionUsuario= new AccionesUsuarios(seleccionadas, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalAbasto);
		accionUsuario.realizarProceso();
		Assert.assertEquals(2, terminalAbasto.getObservers().size()); // reportePorFecha y reportesTotales
	}
	
	@Test
	public void cantidadDeAfectadosEs1(){
		accionUsuario= new AccionesUsuarios(seleccionadas, "agregar", observerReportesTotales);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalAbasto);
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(1, resultadoProceso.getCantElementosAfectados());
	}
	
	
	//Proceso4: quito accion ReportePorFecha de las terminales de la comuna 8
	@Test
	public void quitoAccionDeLasTerminalesDeLacomuna8(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(0, terminalLugano.getObservers().size()); // no tienen ningun observer
		Assert.assertEquals(0, terminalAbasto.getObservers().size());
	}
	
	@Test
	public void quitoAccionDeLasTerminalesDeLaComuna8YTerminalFloridaLaSigueTeniendo(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(1, terminalFlorida.getObservers().size()); 
	}
	
	@Test 
	public void cantidadDeAfectadosEs2(){
		accionUsuario = new AccionesUsuarios(segunComuna, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		resultadoProceso = accionUsuario.realizarProceso();
		Assert.assertEquals(2, resultadoProceso.getCantElementosAfectados()) ;
	}
	
	
	// Proceso 6: quito accion ReportesPorFecha de todas las terminales (todas lo tienen)
	@Test
	public void quitoAccionDeLasTerminales(){
		accionUsuario = new AccionesUsuarios(todasLasTerminales, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		accionUsuario.realizarProceso();
		Assert.assertEquals(0,terminalAbasto.getObservers().size()); 
		Assert.assertEquals(0, terminalFlorida.getObservers().size());	
		Assert.assertEquals(0, terminalFlorida.getObservers().size());
	}
	
	
	// Proceso 7: quito accion ReportePorFecha de las terminales seleccionadas
	@Test
	public void quitoAccionDeLasTerminalesSeleccionadas(){
		accionUsuario = new AccionesUsuarios(seleccionadas, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalLugano);
		accionUsuario.realizarProceso();
		Assert.assertEquals(0, terminalLugano.getObservers().size());
	}
	
	@Test
	public void quitoAccionDeLaTerminalSeleccionadaYLasDemasLaSiguenTeniendo(){
		accionUsuario = new AccionesUsuarios(seleccionadas, "quitar", observerReportePorFecha);
		accionUsuario.setRepositorioTerminales(RepositorioTerminales.getInstance());
		seleccionadas.agregarTerminalSeleccionadaPorAdmin(terminalLugano);
		accionUsuario.realizarProceso();
		Assert.assertEquals(1, terminalAbasto.getObservers().size());
		Assert.assertEquals(1, terminalFlorida.getObservers().size());
	}
	
	@After
	public void tearDown(){
		RepositorioTerminales.resetTerminales();
	}
}
