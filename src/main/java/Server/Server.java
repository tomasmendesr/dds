package Server;

import org.uqbar.geodds.Point;

import Model.Terminal;
import POIs.ParadaDeColectivo;
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
	        paradaDel47.setUbicacion(new Point(-34.6715, -58.4676));
	        paradaDel47.addTag("47");
	        paradaDel47.addTag("parada");
	    	RepositorioPOIs.getInstance().agregar(paradaDel47);
	    	
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