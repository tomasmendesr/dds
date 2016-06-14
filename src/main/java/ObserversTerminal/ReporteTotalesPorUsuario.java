package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

import java.util.HashMap;

public class ReporteTotalesPorUsuario implements FuncionalidadExtraTerminal {
	
	//Atributos
	private HashMap<Terminal,Integer> 		resultadosPorTerminal;
	
	//Constructor
	public ReporteTotalesPorUsuario(){
		resultadosPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	//Getters y Setters
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {		
		this.generarTotalesPorUsuario(unaTerminal);
	}

	// Reporte totales por usuario
	public void generarTotalesPorUsuario(Terminal unaTerminal){
		resultadosPorTerminal.put(unaTerminal, unaTerminal.obtenerResultadosTotales());
	}
	
	public Integer resultadosTotalesEnTerminal(Terminal terminal){ // Sirve para el test por ahora
		return resultadosPorTerminal.get(terminal);
	}
}
