package Master;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import Command.Command;

public class Admin {


	public void ejecutarTarea(Command tipoTarea, Date horario){
		Timer timer = new Timer();
		TimerTask tareaProgramada = new TimerTask(){
			@Override
			public void run(){
				tipoTarea.ejecutar();
			}
		};
		timer.schedule(tareaProgramada, horario); 
	}
	
	public void ejecutarTareaPeriodica(Command tipoTarea, Date horario, int frecuencia){
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
