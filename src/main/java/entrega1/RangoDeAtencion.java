package entrega1;


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
	
	public boolean disponible(Tiempo unTiempo){
		return this.horarioDisponible(unTiempo) && this.diaDisponible(unTiempo);
	}
	
	public boolean horarioDisponible(Tiempo unTiempo){
		return	(this.getHorarioDeApertura() <= unTiempo.getHorasMinutos())
				&& (unTiempo.getHorasMinutos() <= this.getHorarioDeCierre());
	}
	public boolean diaDisponible(Tiempo unTiempo){
		return (this.getDiaDeIncioDeAtencion() <= unTiempo.getDiaDeSemana()) 
				&& (unTiempo.getDiaDeSemana() <= this.getDiaDeFinDeAtencion());
	}
	
}
