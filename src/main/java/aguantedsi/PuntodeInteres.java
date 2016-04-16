package aguantedsi;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class PuntodeInteres {
	
	Point ubicacion;
	Polygon comuna;
	
	public PuntodeInteres(Point _ubicacion, Polygon _miComuna) {
		ubicacion = _ubicacion; // Así le pasas la ubicacion cuando instancias el objeto
		comuna = _miComuna;
	}
	public boolean creoUnaFuncion(Point otroPunto){
		// Osea, vos le asignas un objeto al atributo ubicacion, y le mandas el mensaje distance, 
		//que le calcula la distancia desde su ubicacion hasta el otro punto que le pasaste.
		return (ubicacion.distance(otroPunto)) > 2;
		
	}
	
	public boolean creoOtraFuncion(Polygon comuna, Point unPunto){
		comuna.isInside(unPunto); // Vos tenes que cargar un Polygon que es una lista de puntos. Y esa funcion se fija si esta adentro de esos puntos
		return false;
	}
	
	
}
