package Server;

import org.uqbar.geodds.Point;

import Model.Terminal;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Rubro;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	    public static void main(String[] args) {
	    	Spark.port(8700);
	 
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
	    	Terminal terminal2 = new Terminal();
	    	terminal2.setNombre("campus");
	    	
	    	RepositorioTerminales.getInstance().agregar(terminal);
	    	RepositorioTerminales.getInstance().agregar(terminal2);
	    	
			DebugScreen.enableDebugScreen();
			Router.configure();
	    }
}