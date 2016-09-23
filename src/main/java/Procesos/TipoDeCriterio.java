package Procesos;

import java.util.ArrayList;
import java.util.List;

import Model.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;

public abstract class TipoDeCriterio {
		
	//Atributos
	protected List<Terminal> todasLasTerminales;
	
	// constructor
	public TipoDeCriterio(){
		todasLasTerminales = new ArrayList<>();
	}
		
	// Metodos
	public void agregar(FuncionalidadExtraTerminal accion){
		this.terminalesAModificar().forEach(terminal -> terminal.addObserver(accion));
	}
	
	public void quitar(FuncionalidadExtraTerminal accion){
		this.terminalesAModificar().forEach(terminal -> terminal.quitarAccion(accion));
	}
	
	public Integer cantidadDeAfectados(){
		return this.terminalesAModificar().size();
	}
	
	public abstract List<Terminal> terminalesAModificar();
	
	//Getters y Setters
	public List<Terminal> getTodasLasTerminales() {
		return todasLasTerminales;
	}
	public void setTodasLasTerminales(List<Terminal> todasLasTerminales) {
		this.todasLasTerminales = todasLasTerminales;
	}
}
