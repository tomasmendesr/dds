package entrega1;

import org.joda.time.DateTime;

public class RangoDeAtencion {

	//CONSTRUCTOR
	
	public RangoDeAtencion(double unHorarioDeApertura, double unHorarioDeCierre, int unDiaDeInicioDeAtencion, int unDiaDeFinDeAtencion){
		this.setHorarioDeApertura(unHorarioDeApertura);
		this.setHorarioDeCierre(unHorarioDeCierre);
		this.setDiaDeIncioDeAtencion(unDiaDeInicioDeAtencion);
		this.setDiaDeFinDeAtencion(unDiaDeFinDeAtencion);
	}
	
	//ATRIBUTOS
	
	
	double			horarioDeApertura;	//HH.mm
	double			horarioDeCierre;	//HH.mm
	int				diaDeIncioDeAtencion;
	int				diaDeFinDeAtencion;
	
	//GETERS Y SETERS
	
	public double getHorarioDeApertura() {
		return horarioDeApertura;
	}

	public void setHorarioDeApertura(double horarioDeApertura) {
		this.horarioDeApertura = horarioDeApertura;
	}

	public double getHorarioDeCierre() {
		return horarioDeCierre;
	}

	public void setHorarioDeCierre(double horarioDeCierre) {
		this.horarioDeCierre = horarioDeCierre;
	}

	public int getDiaDeIncioDeAtencion() {
		return diaDeIncioDeAtencion;
	}

	public void setDiaDeIncioDeAtencion(int diaDeIncioDeAtencion) {
		this.diaDeIncioDeAtencion = diaDeIncioDeAtencion;
	}

	public int getDiaDeFinDeAtencion() {
		return diaDeFinDeAtencion;
	}

	public void setDiaDeFinDeAtencion(int diaDeFinDeAtencion) {
		this.diaDeFinDeAtencion = diaDeFinDeAtencion;
	}
		
	
	
	//METODOS
	
	public boolean disponible(DateTime unTiempo){
		return this.horarioDisponible(unTiempo) && this.diaDisponible(unTiempo);
	}
	
	public boolean horarioDisponible(DateTime unTiempo){
		return	(this.getHorarioDeApertura() <= this.pasarAHorasMinutos(unTiempo))
				&& (pasarAHorasMinutos(unTiempo) <= this.getHorarioDeCierre());
	}
	public boolean diaDisponible(DateTime unTiempo){
		return (this.getDiaDeIncioDeAtencion() <= unTiempo.getDayOfWeek()) 
				&& (unTiempo.getDayOfWeek() <= this.getDiaDeFinDeAtencion());
	}
	
	public double pasarAHorasMinutos(DateTime unTiempo){
		double horas = unTiempo.getHourOfDay();
		double minutos = (unTiempo.getMinuteOfHour())/100;
		return horas + minutos;
	}
	
}
