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

	//Constructor
	public GestorProcesos(){
		resultadosProcesos = new ArrayList<ResultadoProceso>();
		procesos = new ArrayList<Proceso>();
	}

	public static GestorProcesos nuevoGestorProcesosConBajaPOIs(){
		GestorProcesos gestorProcesos = new GestorProcesos();
		GestorPOIsABajar gestorPOIsABajar = GestorPOIsABajar.nuevoGestorConGestorDeProcesos(gestorProcesos);
		gestorProcesos.setGestorPOIsABajar(gestorPOIsABajar);
		gestorProcesos.iniciarProcesoDeBajaAutomaticaDePOIs();
		return gestorProcesos;
	}

	//atributos

	private List<ResultadoProceso> resultadosProcesos;
	private List<Proceso> procesos;
	private GestorPOIsABajar gestorPOIsABajar;
	/*SI BIEN ESTE ULTIMO GESTOR PODRIA ESTAR CONTENDIO EN ESTA CLASE
	CONSIDERAMOS QUE ES MEJOR SEPARAR EL COMPORTAMTIENTO DE ESTA FORMA EN POS DE LA COHESION*/
	

	//Getters Y setters
	public List<ResultadoProceso> getResultadosProcesos(){
		return resultadosProcesos;
	}
	
	public List<Proceso> getProcesos(){
		return procesos;
	}

	public void setGestorPOIsABajar(GestorPOIsABajar unGestor){gestorPOIsABajar = unGestor;}
		
	//Metodos

	public void agregarProcesoAEjecutar(Proceso proceso, Date horario, int cantAIterar){
		if(!procesos.contains(proceso)) { procesos.add(proceso);
		this.ejecutarTarea(proceso,horario,cantAIterar); }
	}
	
	public void ejecutarTarea(Proceso proceso, Date horario, int cantAIterar){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				ResultadoProceso resultado = proceso.realizarProceso();
				proceso.setCantidadAIterar(cantAIterar);
				resultadosProcesos.add(resultado);
				procesos.remove(proceso);
			}
		};
		timer.schedule(tareaProgramada, horario,cantAIterar); 
	}

	private void iniciarProcesoDeBajaAutomaticaDePOIs(){
		gestorPOIsABajar.iniciarBajaDePOIsAutomaticamente();
	}
}



