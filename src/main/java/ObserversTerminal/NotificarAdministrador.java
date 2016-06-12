package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;
import Master.TerminalAdministradora;

public class NotificarAdministrador implements FuncionalidadExtraTerminal{

	public NotificarAdministrador(TerminalAdministradora administrador){
		this.setAdministrador(administrador);
	}
	
	private TerminalAdministradora administrador;
	
	public TerminalAdministradora getAdministrador() {
		return administrador;
	}

	public void setAdministrador(TerminalAdministradora administrador) {
		this.administrador = administrador;
	}
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado())
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
				this.notificarAdministrador();
	}
	
	private void notificarAdministrador(){}
}
