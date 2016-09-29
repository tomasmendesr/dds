package ObserversTerminal;

import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
public class NotificarAdministrador extends FuncionalidadExtraTerminal{

	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado())
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
				this.notificarAdministrador();
	}
	
	public Boolean notificarAdministrador(){
		return true;
	}
}
