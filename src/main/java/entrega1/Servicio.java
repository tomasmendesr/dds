package entrega1;

import org.joda.time.DateTime;

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
	
	public boolean estaDentroDelRangoDeAtencion(DateTime unTiempo){
		   return    !this.estaDentroDeFechaDeNoAtencion(unTiempo)
				   	 && this.diaDeSemanaValido(unTiempo)
				   	 && this.horaMinutosValidos(unTiempo);
	}
	
	public boolean horaMinutosValidos(DateTime unTiempo){
		return this.getRangoDeAtencion().horarioDisponible(unTiempo);
	}
	
	public boolean estaDentroDeFechaDeNoAtencion(DateTime unTiempo){
		return false; //HACERLO BIEN DESPUES
	}
	
	public boolean diaDeSemanaValido(DateTime unTiempo){
		return (this.getRangoDeAtencion().getDiaDeIncioDeAtencion() <= unTiempo.getDayOfWeek())
				&& (unTiempo.getDayOfWeek() <= this.getRangoDeAtencion().getDiaDeFinDeAtencion());
	}
	
}
