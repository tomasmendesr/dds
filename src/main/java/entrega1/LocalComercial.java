package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class LocalComercial extends POI {

	//CONSTRUCTOR
	
	public LocalComercial(Point unaUbicacion, Polygon unaComuna, Rubro unRubro){
	
		super(unaUbicacion,unaComuna);
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
	
	public boolean estaDisponible(String unNombreDeServicio,Tiempo unTiempo){
		return this.getColeccionDeRangosDeAtencion().stream().
			   anyMatch(rangoDeAtencion -> this.rangoDeAtencionDisponible(rangoDeAtencion,unTiempo));
	}
	
	public boolean rangoDeAtencionDisponible(RangoDeAtencion unRangoDeAtencion, Tiempo unTiempo){
		return unRangoDeAtencion.disponible(unTiempo);
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getColeccionDeRangosDeAtencion().add(unRangoDeAtencion);
	}
	
	/*public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getRubro().getRadioDeCercania().isInside(unaMaquina.getUbicacion());
	}*/
	
	
	
}
