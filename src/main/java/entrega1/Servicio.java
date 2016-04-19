package entrega1;

public class Servicio {

	//CONSTRUCTOR 
	
	public Servicio(String unNombre,RangoDeAtencion unRangoDeAtencion){
		this.setNombre(unNombre);
		this.setRangoDeAtencion(unRangoDeAtencion);
	}
	
	//ATRIBUTOS
	
	String 				nombre;
	RangoDeAtencion		rangoDeAtencion;
	
	//GETERS Y SETERS
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public RangoDeAtencion getRangoDeAtencion() {
		return rangoDeAtencion;
	}

	public void setRangoDeAtencion(RangoDeAtencion rangoDeAtencion) {
		this.rangoDeAtencion = rangoDeAtencion;
	}

	//METODOS
	
	public boolean rangoDeAtencionValido(Tiempo unTiempo){
		return this.diaDeSemanaValido(unTiempo.getDiaDeSemana()) && this.horaValida(unTiempo.getHora());
	}
	
	public boolean diaDeSemanaValido(int unDiaDeSemana){
		return rangoDeAtencion.getDiasDeAtencion().contains(unDiaDeSemana);
	}
	
	public boolean horaValida(double unaHora){
		return rangoDeAtencion.getHorasDeAtencion().contains(unaHora);
	}
	
	
	
}
