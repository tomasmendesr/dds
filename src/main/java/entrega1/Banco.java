package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Banco extends POI {

	public Banco(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
		// TODO Auto-generated constructor stub
	}

	public double cercaniaRequerida(){
		return 500;
	}
}
