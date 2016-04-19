package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class LocalComercial extends POI {

	//CONSTRUCTOR
	
	public LocalComercial(Point unaUbicacion, Polygon unaComuna, Rubro unRubro){
	
		super(unaUbicacion,unaComuna);
		this.setRubro(unRubro);
		
	}
	
	
	//ATRIBUTOS
	
	private Rubro 				rubro;
	private	RangoDeAtencion		rangoDeAtencion;

	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	
	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	
	public boolean estaDisponible(String unNombreDeServicio,Tiempo unTiempo){
		return this.rangoDeAtencionValido(unTiempo);
	}
	
	public boolean rangoDeAtencionValido(Tiempo unTiempo){
		return this.diaDeSemanaValido(unTiempo.getDiaDeSemana()) && this.horaValida(unTiempo.getHora());
	}
	
	public boolean diaDeSemanaValido(int unDiaDeSemana){
		return rangoDeAtencion.getDiasDeAtencion().contains(unDiaDeSemana);
	}
	
	public boolean horaValida(double unaHora){
		return rangoDeAtencion.getHorasDeAtencion().contains(unaHora);
	}
	
	/*public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getRubro().getRadioDeCercania().isInside(unaMaquina.getUbicacion());
	}*/
	
	
	
}
