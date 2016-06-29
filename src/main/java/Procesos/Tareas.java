package Procesos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Command.Proceso;

public abstract class Tareas {
	
	//Constructor
	public Tareas(){
		super();
	}
	
	//Atributos
	private int cantARepetir;
	private boolean enviarMailAdmin;
	
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
	
	//Metodos
	
	public void ejecutarTarea(Proceso tipoTarea, Date horario){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				tipoTarea.ejecutar();
			}
		};
		timer.schedule(tareaProgramada, horario); 
		//Esto se ejecuta una vez al horario, para repetir: agregar 3er parametro indicando cada cuanto se repite
	}
	
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
}
