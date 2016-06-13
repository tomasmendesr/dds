package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class ReportePorFecha implements FuncionalidadExtraTerminal{

	private GestorDeReportes gestorDeReportes;
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		gestorDeReportes.contabilizarBusquedaXFecha(unResultadoBusqueda); 	
	}
	 
	
}
