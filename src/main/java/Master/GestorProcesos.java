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
	
<<<<<<< HEAD
	public List<Proceso> getProcesos(){
		return procesos;
	}
	
	public void agregarProcesoAEjecutar(Proceso proceso, Date horario){
=======
	public void agregarProcesoAEjecutar(Proceso proceso, Date horario, int cantARepetir){
>>>>>>> 4e3009d21434c637aedaa3d5ce84b101c94d12e5
		procesos.add(proceso);
		this.ejecutarTarea(proceso,horario,cantARepetir);
	}
	
	public void ejecutarTarea(Proceso proceso, Date horario, int cantArepetir){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				ResultadoProceso resultado = proceso.realizarProceso();
				proceso.setCantARepetir(cantArepetir);
				resultadosProcesos.add(resultado);
				procesos.remove(proceso);
			}
		};
		timer.schedule(tareaProgramada, horario,cantArepetir); 
	}
	
}
