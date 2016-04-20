package entrega1;

import org.joda.time.DateTime;

public class Tiempo {
	
	//CONSTRUCTOR
	
	public Tiempo(Fecha unaFecha,int unDiaDeSemana,double unaHora,double unosMinutos){
		this.setFecha(unaFecha);
		this.setDiaDeSemana(unDiaDeSemana);
		this.setHora(unaHora);
		this.setMinutos(unosMinutos);
	}
	
	//ATRIBUTOS
	
	private Fecha		fecha;
	private int 		diaDeSemana;
	private double 		hora;
	private double		minutos;

	//GETERS Y SETERS
	

	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	public int getDiaDeSemana() {
		return diaDeSemana;
	}
	public void setDiaDeSemana(int diaDeSemana) {
		this.diaDeSemana = diaDeSemana;
	}

	public double getHora() {
		return hora;
	}

	public void setHora(double hora) {
		this.hora = hora;
	}
	public double getMinutos() {
		return minutos;
	}
	public void setMinutos(double minutos) {
		this.minutos = minutos;
	}
	
	public double getHorasMinutos(){
		return this.getHora()+(this.getMinutos()/100);	//Obtengo la hora en formato HHmm
	}
	
	public void setTiempoConDateTime(DateTime unDateTime){	//Sirve para insanciar un Tiempo con un DateTime
		this.setDiaDeSemana(unDateTime.getDayOfWeek());		
		this.setHora(unDateTime.getHourOfDay());
		this.setMinutos(unDateTime.getMinuteOfHour());
	}

	
}


