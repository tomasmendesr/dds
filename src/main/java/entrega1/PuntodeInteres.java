package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public abstract class PuntodeInteres {
	
	protected Point ubicacion;
	protected Polygon comuna;
//	private Coordenada coordenadaDelP;
	private String nombre;
//	private Direccion direccion;

	
	public PuntodeInteres(Point ubicacion, Polygon comuna) {
		this.ubicacion = ubicacion;
		this.comuna = comuna;
	}
	
	public boolean estaCerca(Point puntoPersona){
		return (ubicacion.distance(puntoPersona) * 1000 ) < this.cercaniaRequerida();
	}
	
	public abstract double cercaniaRequerida();

/*
esto no se usa
	public boolean estaAMenosMetrosDe(Double metros, Point otroPunto){
		return (ubicacion.distance(otroPunto) / 1000) < metros;
	}
	
	public boolean creoOtraFuncion(Polygon comuna, Point unPunto){
		return comuna.isInside(unPunto);
	}
	
	public abstract boolean estaCerca();
*/
}
