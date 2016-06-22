package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

public class NotificarAdministrador implements FuncionalidadExtraTerminal{

	private Terminal administrador;
	
	public NotificarAdministrador(Terminal administrador){
		this.setAdministrador(administrador);
	}
	
	public void setAdministrador(Terminal administrador) {
		this.administrador = administrador;
	}
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado())
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
				this.notificarAdministrador();
	}
	
	private void notificarAdministrador(){
		administrador.recibirMail();
	}
}
