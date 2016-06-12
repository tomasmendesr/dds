package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;

public class AlmacenarBusqueda implements FuncionalidadExtraTerminal  {

	private GestorDeReportes gestorDeReportes;
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		this.almacenarResultado(unResultadoBusqueda);
	}
	
	private void almacenarResultado(ResultadoBusqueda unResultadoBusqueda){
		gestorDeReportes.getListaDeReusltados().add(unResultadoBusqueda);
	}

}
