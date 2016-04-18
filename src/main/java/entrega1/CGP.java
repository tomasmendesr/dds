package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI {

	public CGP(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
	}
	
	public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getComuna().isInside(unaMaquina.getUbicacion());		
	}
	
}
