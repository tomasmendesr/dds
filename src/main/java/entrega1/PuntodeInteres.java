package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class PuntodeInteres {
	
	Point ubicacion;
	Polygon comuna;
	
	public PuntodeInteres(Point miUbicacion, Polygon miComuna) {
		this.ubicacion = miUbicacion;
		this.comuna = miComuna;
	}
	public boolean estaAMenosMetrosDe(Double metros, Point otroPunto){
		return (ubicacion.distance(otroPunto)) < metros;
	}
	
	public boolean creoOtraFuncion(Polygon comuna, Point unPunto){
		return comuna.isInside(unPunto);
	}
	
	
}
