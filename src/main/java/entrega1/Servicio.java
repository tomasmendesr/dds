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
	
	public boolean estaDentroDelRangoDeAtencion(Tiempo unTiempo){
		   return    !this.estaDentroDeFechaDeNoAtencion(unTiempo)
				   	 && this.diaDeSemanaValido(unTiempo)
				   	 && this.horaMinutosValidos(unTiempo);
	}
	
	public boolean horaMinutosValidos(Tiempo unTiempo){
		return this.getRangoDeAtencion().horarioDisponible(unTiempo);
	}
	
	public boolean estaDentroDeFechaDeNoAtencion(Tiempo unTiempo){
		return false; //HACERLO BIEN DESPUES
	}
	
	public boolean diaDeSemanaValido(Tiempo unTiempo){
		return (this.getRangoDeAtencion().getDiaDeIncioDeAtencion() <= unTiempo.getDiaDeSemana())
				&& (unTiempo.getDiaDeSemana() <= this.getRangoDeAtencion().getDiaDeFinDeAtencion());
	}
	
	
	
	
	
}
