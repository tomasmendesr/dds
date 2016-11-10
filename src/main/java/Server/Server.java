package Server;

import org.uqbar.geodds.Point;

import Adapters.PolygonAdapter;
import Model.POI;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import Repos.RepositorioPOIs;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	    public static void main(String[] args) {
	    	Spark.port(8000);
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