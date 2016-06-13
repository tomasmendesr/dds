package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class AlmacenarBusqueda implements FuncionalidadExtraTerminal  {

	private GestorDeReportes gestorDeReportes;
	
	public AlmacenarBusqueda(GestorDeReportes unGestor){
		this.setGestorDeReportes(unGestor);
	}
	
	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		gestorDeReportes.almacenarResultado(unResultadoBusqueda);
	}
	
}
