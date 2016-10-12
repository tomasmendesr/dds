package ObserversTerminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@DiscriminatorValue("2")
public class NotificarAdministrador extends AccionesTerminal{

	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		if(!unResultadoBusqueda.sinTiempoEstimado())
			if(unResultadoBusqueda.getDuracionBusqueda()>unResultadoBusqueda.getTiempoEstimadoBusqueda())
				this.notificarAdministrador();
	}
	
	public Boolean notificarAdministrador(){
		return true;
	}
}
