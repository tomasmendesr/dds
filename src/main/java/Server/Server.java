package Server;

import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.Terminal;
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
	    	Comuna comuna8 = new Comuna(8);
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
			libreriaEscolar.setDireccion("Peru 201");
			libreriaEscolar.setNombre("Libreria");
			libreriaEscolar.setTipoDePOI("Local Comercial");
	    	RepositorioPOIs.getInstance().agregar(libreriaEscolar);
	    	
	    	Terminal terminal = new Terminal();
	    	terminal.setNombre("Terminal Abasto");
	    	terminal.setComuna(comuna8);
	    	Terminal terminal2 = new Terminal();
	    	terminal2.setNombre("campus");
	    	terminal2.setComuna(comuna8);
	    	
	    	RepositorioTerminales.getInstance().agregar(terminal);
	    	RepositorioTerminales.getInstance().agregar(terminal2);
	    	
			DebugScreen.enableDebugScreen();
			Router.configure();
	    }
}