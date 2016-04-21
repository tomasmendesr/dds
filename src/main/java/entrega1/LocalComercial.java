package entrega1;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class LocalComercial extends POISinServicio {

	//CONSTRUCTOR
	
	public LocalComercial(Point unaUbicacion, Comuna comuna8, Rubro unRubro){
	
		super(unaUbicacion,comuna8);
		this.setRubro(unRubro);
		ArrayList<RangoDeAtencion> unaColeccionDeRangosDeAtencion = new ArrayList<RangoDeAtencion>();
		this.setColeccionDeRangosDeAtencion(unaColeccionDeRangosDeAtencion);
	}
	
	
	//ATRIBUTOS
	
	private Rubro 							rubro;
	private	ArrayList<RangoDeAtencion>		coleccionDeRangosDeAtencion;

	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	
	public ArrayList<RangoDeAtencion> getColeccionDeRangosDeAtencion(){
		return coleccionDeRangosDeAtencion;
	}
	
	public void setColeccionDeRangosDeAtencion(ArrayList<RangoDeAtencion> unaColeccionDeRangosDeAtencion){
		coleccionDeRangosDeAtencion = unaColeccionDeRangosDeAtencion;
	}
	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	
	public boolean estaDisponible(DateTime unTiempo){
		return this.getColeccionDeRangosDeAtencion().stream().
			   anyMatch(rangoDeAtencion -> this.rangoDeAtencionDisponible(rangoDeAtencion,unTiempo));
	}
	
	public boolean rangoDeAtencionDisponible(RangoDeAtencion unRangoDeAtencion, DateTime unTiempo){
		return unRangoDeAtencion.disponible(unTiempo);
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getColeccionDeRangosDeAtencion().add(unRangoDeAtencion);
	}

	
	/*public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getRubro().getRadioDeCercania().isInside(unaMaquina.getUbicacion());
	}*/
	
	
	
}
