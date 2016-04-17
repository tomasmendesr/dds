package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PuntodeInteres {

	public CGP(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
		// TODO Auto-generated constructor stub
	}
	
	public boolean estaCerca(Point puntoPersona){
		return comuna.isInside(puntoPersona);		
	}

	public double cercaniaRequerida() {
		return 0;
	}

}
