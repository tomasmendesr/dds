package Procesos;

import ObserversTerminal.FuncionalidadExtraTerminal;

public class Todas extends TipoDeCriterio { // Afecta a todas las terminales
	
	//Metodos
	public void agregar(FuncionalidadExtraTerminal accion){
		this.getTodasLasTerminales().forEach(terminal -> terminal.addObserver(accion));
	}
	
	public void quitar(FuncionalidadExtraTerminal accion){
		this.getTodasLasTerminales().forEach(terminal -> terminal.quitarAccion(accion));
	}
	
	public Integer cantidadDeAfectados(){
		return this.getTodasLasTerminales().size();
	}



}
