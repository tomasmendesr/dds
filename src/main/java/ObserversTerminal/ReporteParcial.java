package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class ReporteParcial implements FuncionalidadExtraTerminal {
	
	private GestorDeReportes gestorDeReportes;
	
	public ReporteParcial(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		Integer cantidadDeResultados;
		cantidadDeResultados = unResultadoBusqueda.cantidadDeResultados();
		gestorDeReportes.generarResultadosParciales(unaTerminal, cantidadDeResultados);
	}
	
}
