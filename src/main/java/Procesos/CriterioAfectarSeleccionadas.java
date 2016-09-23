package Procesos;

import java.util.ArrayList;
import java.util.List;

import Model.Terminal;

public class CriterioAfectarSeleccionadas extends TipoDeCriterio {
	
	//atributos
	private List<Terminal> terminalesSeleccionadas;
	
	//constructor
	public CriterioAfectarSeleccionadas(){
		terminalesSeleccionadas = new ArrayList<Terminal>();
	}
	
	//Metodos
	public List<Terminal> terminalesAModificar() { // Devuelve la lista de terminales seleccionadas
		return terminalesSeleccionadas;
	}
	
	
	//Getters y setters
	public void setTerminalesSeleccionadas(List<Terminal> terminalesSeleccionadas) {
		this.terminalesSeleccionadas = terminalesSeleccionadas;
	}
	
	public void agregarTerminalSeleccionadaPorAdmin(Terminal unaTerminal){
		terminalesSeleccionadas.add(unaTerminal);
	}
			
}
