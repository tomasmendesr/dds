package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public class AdapterAdministrador implements FuncionalidadExtraTerminal{
	public AdapterAdministrador(ObserverNotificarAdministrador unObserverNotificar) {
		notificarAdministrador = unObserverNotificar;
	}

	private ObserverNotificarAdministrador notificarAdministrador;

	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado()){
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
				notificarAdministrador.notificaAdministrador();
		}
	}
}
