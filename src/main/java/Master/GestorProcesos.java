package Master;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import CommandProcesos.Proceso;
import Procesos.ResultadoProceso;

public class GestorProcesos {


	public void ejecutarTarea(Proceso tipoTarea, Date horario){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				tipoTarea.ejecutar();
			}
		};
		timer.schedule(tareaProgramada, horario); 
	}
	
	public void ejecutarTareaPeriodica(Proceso tipoTarea, Date horario, int frecuencia){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				tipoTarea.ejecutar();
			}
		};
		timer.schedule(tareaProgramada, horario, frecuencia); 
	}
}
