package ObserversTerminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Master.ResultadoBusqueda;
import Master.Terminal;

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
