package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class ParadaDeColectivo extends POI {

	public ParadaDeColectivo(Point miUbicacion, Polygon miComuna) {
		super(miUbicacion, miComuna);
	}

	public double cercaniaRequerida(){
		return 100.0;
	}
}
