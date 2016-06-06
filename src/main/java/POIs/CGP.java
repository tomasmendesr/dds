package POIs;

import org.uqbar.geodds.Point;

public class CGP extends POIConServicio {

	//CONSTRUCTOR
	
	public CGP(Point ubicacion) {
		super(ubicacion);
	}
	
	//METODOS
	
	@Override
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getComuna().getZona().isInside(unaUbicacion);
	}


	
	
}
