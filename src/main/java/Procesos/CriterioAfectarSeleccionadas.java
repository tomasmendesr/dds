package Procesos;

import java.util.ArrayList;
import java.util.List;

import Master.Terminal;

public class CriterioAfectarSeleccionadas extends TipoDeCriterio {
	
	//atributo
	private List<Terminal> terminalesSeleccionadas;
	
	//constructor
	public CriterioAfectarSeleccionadas(){
		terminalesSeleccionadas = new ArrayList<Terminal>();
	}
	
	//Getters y setters
	public void setTerminalesSeleccionadas(List<Terminal> terminalesSeleccionadas) {
		this.terminalesSeleccionadas = terminalesSeleccionadas;
	}
	
	public void agregarTerminalSeleccionadaPorAdmin(Terminal unaTerminal){
		terminalesSeleccionadas.add(unaTerminal);
	}
	
	//Metodos
	public List<Terminal> terminalesAModificar() { // Devuelve la lista de terminales seleccionadas
		return terminalesSeleccionadas;
	}
			
}
