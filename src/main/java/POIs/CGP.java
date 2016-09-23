package POIs;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.uqbar.geodds.Point;

@Entity
@DiscriminatorValue("2")
public class CGP extends POIConServicio {

	//CONSTRUCTOR
	public CGP(Point ubicacion) {
		super(ubicacion);
	}
	
	public CGP(){ }
	
	//METODOS
	@Override
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getComuna().getZona().isInside(unaUbicacion);
	}


	
	
}
