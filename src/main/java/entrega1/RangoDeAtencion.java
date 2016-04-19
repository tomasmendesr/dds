package entrega1;

import java.util.List;

public class RangoDeAtencion {

	//CONSTRUCTOR
	
	public RangoDeAtencion(List<Double> unasHorasDeAtencion, List<Integer> unosDiasDeAtencion){
		this.setHorasDeAtencion(unasHorasDeAtencion);
		this.setDiasDeAtencion(unosDiasDeAtencion);
	}
	
	//ATRIBUTOS
	
	List<Double> 	horasDeAtencion;
	List<Integer> 	diasDeAtencion;
	
	//GETERS Y SETERS
	
	public List<Double> getHorasDeAtencion() {
		return horasDeAtencion;
	}
	public void setHorasDeAtencion(List<Double> horasDeAtencion) {
		this.horasDeAtencion = horasDeAtencion;
	}
	public List<Integer> getDiasDeAtencion() {
		return diasDeAtencion;
	}
	public void setDiasDeAtencion(List<Integer> diasDeAtencion) {
		this.diasDeAtencion = diasDeAtencion;
	}
	
}
