package testEntrega5PersistenciaRelacional;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.Terminal;
import ObserversTerminal.AccionesTerminal;
import ObserversTerminal.ReportePorFecha;import POIsExt.Comuna;
import Repos.RepositorioBusquedas;
import Repos.RepositorioTerminales;

public class TestTerminal {

	private Terminal terminalAbasto;
	private Comuna comuna8;
	private PolygonAdapter	zonaComuna8;
	private ReportePorFecha observerReportePorFecha;
	
	@Before
	public void init(){
	// Comuna 8
	comuna8 = new Comuna(new Long(8));
	zonaComuna8 = new PolygonAdapter(new Long(1));
	zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
	zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
	zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
	zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
	zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
	comuna8.setZona(zonaComuna8);
	
	//Observer
	observerReportePorFecha = new ReportePorFecha();
	
	//Terminales
	terminalAbasto = new Terminal("Terminal Abasto");
	terminalAbasto.setComuna(comuna8);
	List<AccionesTerminal> acciones = new ArrayList<>();
	acciones.add(observerReportePorFecha);
	terminalAbasto.setObservers(acciones);
	RepositorioTerminales.getInstance().agregar(terminalAbasto);
	}
	
	@Test
	public void testVerificarTerminalGuardada(){
		Terminal terminalAbastoDevuelta = RepositorioTerminales.getInstance().buscarPorNombre("Terminal Abasto").get(0);
		Assert.assertEquals(new Long(1), new Long(terminalAbastoDevuelta.getId()));
		Assert.assertEquals("Terminal Abasto", terminalAbastoDevuelta.getNombre());
	}
    
	@After
	public void tearDown(){
		RepositorioTerminales.resetTerminales();
		RepositorioBusquedas.resetBusquedas();
	}
}
