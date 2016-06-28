package Procesos;

import java.util.List;

import Master.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;

public class Seleccionada extends TipoDeCriterio {
	
	//atributo
	private List<Terminal> terminalesSeleccionadas;
	
	//getters y setters
	public List<Terminal> getTerminalesSeleccionadas() {
		return terminalesSeleccionadas;
	}
	
	public void setTerminalesSeleccionadas(List<Terminal> terminalesSeleccionadas) {
		this.terminalesSeleccionadas = terminalesSeleccionadas;
	}
	
	public void agregarTerminalSeleccionadaPorAdmin(Terminal unaTerminal){
		terminalesSeleccionadas.add(unaTerminal);
	}
	
	//Metodos
	public void agregar(FuncionalidadExtraTerminal accion){
		terminalesSeleccionadas.forEach(terminal -> terminal.addObserver(accion));
	}
	
	public void quitar(FuncionalidadExtraTerminal accion){
		terminalesSeleccionadas.forEach(terminal -> terminal.quitarAccion(accion));
	}
		
	public Integer cantidadDeAfectados(){
		return terminalesSeleccionadas.size();
	}
	
}
