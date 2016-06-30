package Master;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Procesos.Proceso;
import Procesos.ResultadoProceso;

public class GestorProcesos {


	public void ejecutarTarea(Proceso proceso, Date horario){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				proceso.realizarProceso();
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
