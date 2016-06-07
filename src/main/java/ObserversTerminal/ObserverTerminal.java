package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public interface ObserverTerminal {

	void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda);
	
	
}
