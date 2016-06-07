package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public class AlmacenarResultados implements ObserverTerminal {

	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		unaTerminal.almacenarResultadoBusqueda(unResultadoBusqueda);
	}

}
