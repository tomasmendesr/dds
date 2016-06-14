package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

import java.util.HashMap;

public class ReporteParcial implements FuncionalidadExtraTerminal {
	
	//Atributos
	private HashMap<Terminal,Integer> 		resultadosParcialesPorTerminal;
	
	//Constructor
	public ReporteParcial(){
		resultadosParcialesPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	//Getters y Setters
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		Integer cantidadDeResultados;
		cantidadDeResultados = unResultadoBusqueda.cantidadDeResultados();
		this.generarResultadosParciales(unaTerminal, cantidadDeResultados);
	}

	// Reporte de Resultados Parciales por Terminal
	public void generarResultadosParciales(Terminal unaTerminal, Integer cantidadDeResultados){
		resultadosParcialesPorTerminal.put(unaTerminal, cantidadDeResultados);
	}
	
	public Integer resultadosEnTerminal(Terminal terminal){ // Sirve para el test por ahora
		return resultadosParcialesPorTerminal.get(terminal);
	}
}
