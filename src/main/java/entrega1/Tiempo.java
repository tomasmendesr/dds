package entrega1;

public class Tiempo {
	
	//CONSTRUCTOR
	
	public Tiempo(int unDiaDeSemana,double unaHora){
		this.setDiaDeSemana(unDiaDeSemana);
		this.setHora(unaHora);
	}
	
	//ATRIBUTOS
	
	private int 		diaDeSemana;
	private double 		hora;
//	private double minutos; ver como maneja el tema de los minutos joda-time

	//GETERS Y SETERS
	

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

	
}


