package Procesos;

import java.time.LocalDateTime;

public class ResultadoProceso {
	//Atributos
	private int cantElementosAfectados;
	private boolean resultadoDelProceso;
	private LocalDateTime momentoDeEjecucion;

	//Constructor
	public ResultadoProceso(){
		super();
	}

	//Getters y Setters
	public void setCantElementosAfectados(int unaCant){
		cantElementosAfectados = unaCant;
	}
	
	public void setResultadoDelProceso(boolean value){
		resultadoDelProceso = value;
	}
	
	public void setMomentoDeEjecucion(LocalDateTime momento){
		momentoDeEjecucion = momento;
	}
	
	public int getCantElementosAfectados(){
		return cantElementosAfectados;
	}
	
	public boolean getResultadoDelProceso(){
		return resultadoDelProceso;
	}
	
	public LocalDateTime getMomentoDeEjecucion(){
		return momentoDeEjecucion;
	}
}
