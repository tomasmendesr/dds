package POIs;


import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.uqbar.geodds.Point;

import Master.POI;
import POIsExt.Comuna;

@Entity
@DiscriminatorValue("P")
public class ParadaDeColectivo extends POI {

	//CONSTRUCTOR
	public ParadaDeColectivo(Point miUbicacion) {
		super(miUbicacion);
		//this.instanciarRangoDeAtencionDeColectivo();
	}
	
	public ParadaDeColectivo(){ }
	
	//METODOS
	@Override
	public double cercaniaRequerida(){
		return 100.0;
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return true;
	}

	public void setComuna(Comuna comuna) { }
	
		
}
