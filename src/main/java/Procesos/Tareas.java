package Procesos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public abstract class Tareas {
	
	//Constructor
	public Tareas(){
		listaProcesos = new ArrayList<ResultadoProceso>();
	}
	
	//Atributos
	private int cantARepetir;
	private boolean enviarMailAdmin;
	private List<ResultadoProceso> listaProcesos;
	
	//Getters y Setters
	public List<ResultadoProceso> getListaProcesos(){
		return listaProcesos;
	}
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
	
	//Metodos
	
		
	public ResultadoProceso realizaTarea(){
		return new ResultadoProceso();
	}
	
	protected ResultadoProceso falle(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		resultadoProceso.setCantElementosAfectados(0);
		resultadoProceso.setResultadoDelProceso(false);
		while(cantARepetir == 0){
			resultadoProceso = this.realizaTarea();
			cantARepetir--;
		}
		return resultadoProceso;
	}
	
	public void guardarTarea(ResultadoProceso tarea){
		this.getListaProcesos().add(tarea);
	}
}
