package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class ReporteTotalesPorUsuario implements FuncionalidadExtraTerminal {
	
	private GestorDeReportes gestorDeReportes;
	
	public ReporteTotalesPorUsuario(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {		
		gestorDeReportes.generarTotalesPorUsuario(unaTerminal);
	}
	 
}
