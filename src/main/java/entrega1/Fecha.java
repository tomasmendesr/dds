package entrega1;

import org.joda.time.DateTime;

public class Fecha {

	//CONSTRUCTOR
	
	public Fecha(int unDia,int unMes, int unAnio){
		this.setDia(unDia);
		this.setMes(unMes);
		this.setAnio(unAnio);
	}
	
	//ATRIBUTOS
	
	int dia;
	int mes;
	int anio;
	
	//GETERS Y SETERS
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	//METODOS
	
	public static Fecha actual(){
		DateTime unDateTime = new DateTime();
		int unDia = unDateTime.getDayOfMonth();
		int unMes = unDateTime.getMonthOfYear();
		int unAnio = unDateTime.getYear();
		Fecha fecha = new Fecha(unDia,unMes,unAnio);
		return fecha;
	}
}
