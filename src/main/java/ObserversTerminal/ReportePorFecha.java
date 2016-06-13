package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class ReportePorFecha implements FuncionalidadExtraTerminal{

	private GestorDeReportes gestorDeReportes;
	
	public ReportePorFecha(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		gestorDeReportes.contabilizarBusquedaXFecha(unResultadoBusqueda); 	
	}
	 
	
}
