package Master;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import Procesos.GestorPOIsABajar;
import Procesos.Proceso;
import Procesos.ResultadoProceso;

public class GestorProcesos {
	
	//atributos
	private List<ResultadoProceso> resultadosProcesos;
	private List<Proceso> procesos;
	private GestorPOIsABajar gestorPOIsABajar;
	/*SI BIEN ESTE ULTIMO GESTOR PODRIA ESTAR CONTENDIO EN ESTA CLASE
	CONSIDERAMOS QUE ES MEJOR SEPARAR EL COMPORTAMTIENTO DE ESTA FORMA EN POS DE LA COHESION*/
	
	//Constructor
	public GestorProcesos(){
		resultadosProcesos = new ArrayList<ResultadoProceso>();
		procesos = new ArrayList<Proceso>();
		gestorPOIsABajar = new GestorPOIsABajar(this); 
	}

	//Getters Y setters
	public List<ResultadoProceso> getResultadosProcesos(){
		return resultadosProcesos;
	}
	
	public List<Proceso> getProcesos(){
		return procesos;
	}
		
	//Metodos

	public void agregarProcesoAEjecutar(Proceso proceso, Date horario, int cantARepetir){
		if(!procesos.contains(proceso)) { procesos.add(proceso);
		this.ejecutarTarea(proceso,horario,cantARepetir); }
	}
	
	public void ejecutarTarea(Proceso proceso, Date horario, int frecuencia){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				ResultadoProceso resultado = proceso.realizarProceso();
				proceso.setCantidadAIterar(frecuencia);
				resultadosProcesos.add(resultado);
				procesos.remove(proceso);
			}
		};
		timer.schedule(tareaProgramada, horario,frecuencia); 
	}

}
