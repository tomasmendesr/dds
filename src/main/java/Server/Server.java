package Server;

import org.uqbar.geodds.Point;

import POIs.ParadaDeColectivo;
import Repos.RepositorioPOIs;
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
	    	RepositorioPOIs.getInstance().agregar(paradaDel47);
			DebugScreen.enableDebugScreen();
			Router.configure();
	    }
}