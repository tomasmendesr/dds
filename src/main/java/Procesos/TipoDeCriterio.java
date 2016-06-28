package Procesos;

import java.util.ArrayList;
import java.util.List;

import Master.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;

public abstract class TipoDeCriterio {
		
	//Atributos
	protected List<Terminal> todasLasTerminales;
	
	public void TipoDeCriteiro(){
		todasLasTerminales = new ArrayList<Terminal>();
	}

	//Getters y Setters
		public List<Terminal> getTodasLasTerminales() {
			return todasLasTerminales;
		}
		public void setTodasLasTerminales(List<Terminal> todasLasTerminales) {
			this.todasLasTerminales = todasLasTerminales;
		}
		
			
	public abstract Integer cantidadDeAfectados();
	
	public abstract void agregar(FuncionalidadExtraTerminal accion);
	
	public abstract void quitar(FuncionalidadExtraTerminal accion);
	
		
}
