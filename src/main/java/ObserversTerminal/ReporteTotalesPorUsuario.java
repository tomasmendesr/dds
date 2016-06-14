package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

import java.util.HashMap;

public class ReporteTotalesPorUsuario implements FuncionalidadExtraTerminal {
	
	private GestorDeReportes 				gestorDeReportes;
	private HashMap<Terminal,Integer> 		resultadosPorUsuario;
	
	public ReporteTotalesPorUsuario(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
		resultadosPorUsuario = new HashMap<Terminal,Integer>();
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {		
		this.generarTotalesPorUsuario(unaTerminal);
	}

	// Reporte totales por usuario
	public void generarTotalesPorUsuario(Terminal unaTerminal){
		resultadosPorUsuario.put(unaTerminal, unaTerminal.obtenerResultadosTotales());

	}
}
