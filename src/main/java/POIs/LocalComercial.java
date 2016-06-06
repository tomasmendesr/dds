package POIs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import Master.POI;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;

public class LocalComercial extends POI {

	//CONSTRUCTOR
	

	public LocalComercial(Point unaUbicacion, Rubro unRubro){
		super(unaUbicacion);
		this.setRubro(unRubro);
		ArrayList<RangoDeAtencion> unaColeccionDeRangosDeAtencion = new ArrayList<RangoDeAtencion>();
		this.setColeccionDeRangosDeAtencion(unaColeccionDeRangosDeAtencion);
	}
	
	
	//ATRIBUTOS
	
	private Rubro 							rubro;
	private	List<RangoDeAtencion>			coleccionDeRangosDeAtencion;

	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	
	public List<RangoDeAtencion> getColeccionDeRangosDeAtencion(){
		return coleccionDeRangosDeAtencion;
	}
	
	public void setColeccionDeRangosDeAtencion(List<RangoDeAtencion> unaColeccionDeRangosDeAtencion){
		coleccionDeRangosDeAtencion = unaColeccionDeRangosDeAtencion;
	}
	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return this.getColeccionDeRangosDeAtencion().stream().
			   anyMatch(rangoDeAtencion -> this.rangoDeAtencionDisponible(rangoDeAtencion,unTiempo));
	}
	
	public boolean rangoDeAtencionDisponible(RangoDeAtencion unRangoDeAtencion, LocalDateTime unTiempo){
		return unRangoDeAtencion.disponible(unTiempo);
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getColeccionDeRangosDeAtencion().add(unRangoDeAtencion);
	}

	
	
}
