package Server;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.Terminal;
import ObserversTerminal.AccionesTerminal;
import ObserversTerminal.NotificarAdministrador;
import ObserversTerminal.ReportePorFecha;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	    public static void main(String[] args) {
	    	Spark.port(8700);
	    	
	    	Comuna comuna8 = new Comuna(new Long(8));
	    	PolygonAdapter zonaComuna8 = new PolygonAdapter();
	    	zonaComuna8.agregarPoint(new Point(-34.6744,-58.5025));
	    	zonaComuna8.agregarPoint(new Point(-34.6578,-58.4787));
	    	zonaComuna8.agregarPoint(new Point(-34.6648,-58.4697));
	    	zonaComuna8.agregarPoint(new Point(-34.6621,-58.4240));
	    	zonaComuna8.agregarPoint(new Point(-34.7048,-58.4612));
	    	comuna8.setZona(zonaComuna8);
	    	
	    	ParadaDeColectivo paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
	        paradaDel47.setDireccion("Corvalan 3691");
	        paradaDel47.setNombre("Parada del 47");
	        paradaDel47.addTag("47");
	        paradaDel47.addTag("parada");
	        paradaDel47.setTipoDePOI("Parada de Colectivo");
	    	RepositorioPOIs.getInstance().agregar(paradaDel47);
	    	
	    	Rubro rubroLibreriaEscolar = new Rubro(500.0);
			LocalComercial libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
			libreriaEscolar.addTag("libreria");
			libreriaEscolar.setComuna(comuna8);
			libreriaEscolar.setDireccion("Peru 201");
			libreriaEscolar.setNombre("Libreria");
			libreriaEscolar.setTipoDePOI("Local Comercial");
			
	    	RepositorioPOIs.getInstance().agregar(libreriaEscolar);
	    	
	    	List<AccionesTerminal> accionesTerminal = new ArrayList<>();
	    	List<AccionesTerminal> accionesTerminal2 = new ArrayList<>();
	    	ReportePorFecha observerReportePorFecha = new ReportePorFecha();
	    	NotificarAdministrador observerNotificarAdmin = new NotificarAdministrador();
	    	accionesTerminal.add(observerNotificarAdmin);
	    	accionesTerminal2.add(observerReportePorFecha);
	    	
	    	Terminal terminal = new Terminal("Terminal Abasto");
	    //	terminal.setComuna(comuna8);
	    	terminal.setObservers(accionesTerminal);
	    	RepositorioTerminales.getInstance().agregar(terminal);
	    	
	    	Terminal terminal2 = new Terminal("campus");
	    //	terminal2.setComuna(comuna8);
	    	terminal2.setObservers(accionesTerminal2);
	    	RepositorioTerminales.getInstance().agregar(terminal2);
	    	
			DebugScreen.enableDebugScreen();
			Router.configure();
	    }
}