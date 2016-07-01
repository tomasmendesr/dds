package Procesos;

import java.time.LocalDateTime;
import java.util.List;

import Master.Terminal;

public abstract class Proceso {
	
	//Atributos
	private int cantARepetir;
	private boolean enviarMailAdmin;
	private List<Terminal> terminales;
			
	//Getters y Setters
	public void setCantARepetir(int unaCant){
		cantARepetir = unaCant;
	}
	
	public void setEnviarMailAdmin(boolean value){
		enviarMailAdmin = value;		
	}
	
	public int getCantARepetir(){
		return cantARepetir;
	}
	
	public boolean getEnviarMailAdmin(){
		return enviarMailAdmin;
	}	
	
	public void setTerminales(List<Terminal> terminales){
		this.terminales = terminales;
	}
	
	public List<Terminal> getTerminales(){
		return terminales;
	}
	//Metodos
	
	public abstract ResultadoProceso realizarProceso();
	
	protected ResultadoProceso falle(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		resultadoProceso.setCantElementosAfectados(0);
		resultadoProceso.setResultadoDelProceso(false);
		while(cantARepetir == 0){
			resultadoProceso = this.realizarProceso();
			cantARepetir--;
		}
		return resultadoProceso;
	}
		
}
