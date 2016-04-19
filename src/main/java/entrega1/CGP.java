package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI {

	//CONSTRUCTOR
	
	public CGP(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
	}
	
	//ATRIBUTOS
	
	
	
	//METODOS
	
	@Override
	public boolean estaCercaDeDispositivo(Dispositivo unDispositivo){
		return this.getComuna().isInside(unDispositivo.getUbicacion());		
	}
	
	
	
	
}
