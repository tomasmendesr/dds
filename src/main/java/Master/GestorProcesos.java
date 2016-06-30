package Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Procesos.Proceso;
import Procesos.ResultadoProceso;

public class GestorProcesos {
	
	//atributos
	private List<ResultadoProceso> resultadosProcesos;
	private List<Proceso> procesos;
	
	//constructor
	public GestorProcesos(){
		resultadosProcesos = new ArrayList<ResultadoProceso>();
		procesos = new ArrayList<Proceso>();
	}

	//Getters Y setters
	public List<ResultadoProceso> getResultadosProcesos(){
		return resultadosProcesos;
	}
	
	public List<Proceso> getProcesos(){
		return procesos;
	}
	
	public void agregarProcesoAEjecutar(Proceso proceso, Date horario){
		procesos.add(proceso);
		this.ejecutarTarea(proceso,horario);
	}
	
	public void ejecutarTarea(Proceso proceso, Date horario){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				ResultadoProceso resultado = proceso.realizarProceso();
				resultadosProcesos.add(resultado);
				procesos.remove(proceso);
			}
		};
		timer.schedule(tareaProgramada, horario); 
	}
	
	public void ejecutarTareaPeriodica(Proceso proceso, Date horario, int frecuencia){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				proceso.realizarProceso();
			}
		};
		timer.schedule(tareaProgramada, horario, frecuencia); 
	}
}
