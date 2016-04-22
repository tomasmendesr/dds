package entrega1;

import java.time.LocalDateTime;

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
	
	public boolean estaDentroDelRangoDeAtencion(LocalDateTime horaDelMomento){
		   return    !this.estaDentroDeFechaDeNoAtencion(horaDelMomento)
				   	 && this.diaDeSemanaValido(horaDelMomento)
				   	 && this.horaMinutosValidos(horaDelMomento);
	}
	
	public boolean horaMinutosValidos(LocalDateTime unTiempo){
		return this.getRangoDeAtencion().horarioDisponible(unTiempo);
	}
	
	public boolean estaDentroDeFechaDeNoAtencion(LocalDateTime unTiempo){
		return false; //HACERLO BIEN DESPUES
	}
	
	public boolean diaDeSemanaValido(LocalDateTime unTiempo){
		int diaDeLaSemana = unTiempo.getDayOfWeek().getValue();
		int diaDeInicioDeAtencion = this.getRangoDeAtencion().getDiaDeIncioDeAtencion(); 
		int diaDeFinDeAtencion = this.getRangoDeAtencion().getDiaDeFinDeAtencion();
		return (diaDeInicioDeAtencion <= diaDeLaSemana)
				&& (diaDeLaSemana <= diaDeFinDeAtencion);
	}
	
}
