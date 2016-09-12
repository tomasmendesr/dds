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

	//ATRIBUTOS
	
	
	//CONSTRUCTOR
	
	public ParadaDeColectivo(Point miUbicacion) {
		super(miUbicacion);
		//this.instanciarRangoDeAtencionDeColectivo();
	}
	
	//METODOS
	
	@Override
	public double cercaniaRequerida(){
		return 100.0;
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return true;
	}
	
	/*private void instanciarRangoDeAtencionDeColectivo(){
		double horaDeApertura 		= 0.0;
		double horaDeCierre			= 23.0;
		int diaDeInicioDeAtencion 	= 1;
		int diaDeFinDeAtencion		= 7;
		RangoDeAtencion rangoDeAtencionDeColectivos = new RangoDeAtencion(horaDeApertura,horaDeCierre,diaDeInicioDeAtencion,diaDeFinDeAtencion);
		this.setRangoDeAtencion(rangoDeAtencionDeColectivos);
	}*/

	public void setComuna(Comuna comuna) {
	}
	
		
}
