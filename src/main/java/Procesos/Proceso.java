package Procesos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Proceso {
	
	//Constructor
	public Proceso(){
		
		listaProcesos = new ArrayList<ResultadoProceso>();
	}
	
	//Atributos
	private int cantidadAIterar;
	private boolean enviarMailAdmin;
	private List<ResultadoProceso> listaProcesos;
	
	//Getters y Setters
	public List<ResultadoProceso> getListaProcesos(){
		return listaProcesos;
	}
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
	

	public ResultadoProceso realizarProceso(){
		return new ResultadoProceso();
	}
	
	protected ResultadoProceso falle(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		resultadoProceso.setCantElementosAfectados(0);
		resultadoProceso.setResultadoDelProceso(false);
		while(cantidadAIterar == 0){
			resultadoProceso = this.realizarProceso();
			cantidadAIterar--;
		}
		return resultadoProceso;
	}
	
	public void guardarTarea(ResultadoProceso tarea){
		this.getListaProcesos().add(tarea);
	}
	
}
