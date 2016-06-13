package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class GenerarReporte implements FuncionalidadExtraTerminal{

	private GestorDeReportes gestorDeReportes;
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		gestorDeReportes.contabilizarBusquedaXFecha(unResultadoBusqueda); 
		
		
		Integer cantidadDeResultados;
		cantidadDeResultados = unResultadoBusqueda.cantidadDeResultados();
		gestorDeReportes.generarResultadosParciales(unaTerminal, cantidadDeResultados);
		
		
		gestorDeReportes.generarTotalesPorUsuario(unaTerminal);
	}
	 
	
}
