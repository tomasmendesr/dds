package POIs;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.uqbar.geodds.Point;

@Entity
@DiscriminatorValue("C")
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
