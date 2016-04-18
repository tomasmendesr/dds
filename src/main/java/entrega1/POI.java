package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public abstract class POI {

	//CONSTRUCTOR 
	
	public POI(Point unaUbicacion, Polygon unaComuna) {
		this.setUbicacion(unaUbicacion);
		this.setComuna(unaComuna);
	}	
	
	//ATRIBUTOS
	
	private Point ubicacion;
	private String nombre;
	private String calle;
	private Integer altura;
	private Polygon comuna;

	
	//GETERS Y SETERS
	
	public Point getUbicacion(){
		return ubicacion;
	}
	
	public void setUbicacion(Point unaUbicacion){
		ubicacion = unaUbicacion;
	}
	
	public Polygon getComuna(){
		return comuna;
	}
	
	public void setComuna(Polygon unaComuna){
		comuna = unaComuna;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	//METODOS
	
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return this.getUbicacion().distance(otroPOI.getUbicacion())*1000 < metros;	//para pasarlo a metros
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getUbicacion() != null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null;
	}
	
	//CALCULO DE CERCANIA
	
	public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getUbicacion().distance(unaMaquina.getUbicacion())*1000 < this.cercaniaRequerida(); //para pasar a metros
	}
	
	public double cercaniaRequerida(){
		return 500.0;
	}
	
	/*
	public boolean estaCerca(POI otroPOI){
		return this.estaAMenosDeXMetrosDeOtroPOI(otroPOI, 500.0);
	}
	
	public abstract double cercaniaRequerida();
	*/

	
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
