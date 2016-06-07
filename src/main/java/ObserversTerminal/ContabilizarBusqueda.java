package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public class ContabilizarBusqueda implements ObserverTerminal {

	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		unaTerminal.contabilizarBusquedaXFecha(unResultadoBusqueda);
	}
}
