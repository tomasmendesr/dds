package Procesos;

import java.time.LocalDateTime;

public abstract class Proceso {
	
	//Atributos
	private int cantidadAIterar;
	private boolean enviarMailAdmin;
			
	//Getters y Setters

	public void setCantidadAIterar(int unaCant){
		cantidadAIterar = unaCant;
	}
	
	public void setEnviarMailAdmin(boolean value){
		enviarMailAdmin = value;		
	}
	
	public int getCantidadAIterar(){
		return cantidadAIterar;
	}
	
	public boolean getEnviarMailAdmin(){
		return enviarMailAdmin;
	}	
	
	//Metodos

	public abstract ResultadoProceso realizarProceso();
	
	protected ResultadoProceso falle(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		resultadoProceso.setCantElementosAfectados(0);
		resultadoProceso.setResultadoDelProceso(false);
		while(cantidadAIterar > 0 && resultadoProceso.getResultadoDelProceso() == false){
			resultadoProceso = this.realizarProceso();
			cantidadAIterar--;
		}
		return resultadoProceso;
	}
		
}
