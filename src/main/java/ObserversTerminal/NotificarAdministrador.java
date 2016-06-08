package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public class NotificarAdministrador implements FuncionalidadExtraTerminal {

	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado()){
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
			this.notificarAlAdministrador();
		}
	}
	
	public void notificarAlAdministrador(){};
	
	
	
}
