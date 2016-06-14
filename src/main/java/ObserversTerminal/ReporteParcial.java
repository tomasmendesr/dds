package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

import java.util.HashMap;

public class ReporteParcial implements FuncionalidadExtraTerminal {
	
	private GestorDeReportes 				gestorDeReportes;
	private HashMap<Terminal,Integer> 		resultadosParcialesPorTerminal;
	
	public ReporteParcial(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
		resultadosParcialesPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		Integer cantidadDeResultados;
		cantidadDeResultados = unResultadoBusqueda.cantidadDeResultados();
		this.generarResultadosParciales(unaTerminal, cantidadDeResultados);
	}

	// Reporte de Resultados Parciales por Terminal
	public void generarResultadosParciales(Terminal unaTerminal, Integer cantidadDeResultados){
		resultadosParcialesPorTerminal.put(unaTerminal, cantidadDeResultados);
	}
	
}
