package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI {

	public CGP(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getComuna().isInside(unaMaquina.getUbicacion());		
	}

	public double cercaniaRequerida() {
		return 0;
	}

}
