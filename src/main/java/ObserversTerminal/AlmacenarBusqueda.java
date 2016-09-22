package ObserversTerminal;

import Master.RepositorioTerminales;
import Master.ResultadoBusqueda;
import Master.Terminal;

import javax.persistence.Entity;

@Entity
public class AlmacenarBusqueda extends FuncionalidadExtraTerminal  {

	@Override // Guardo resultado en el Repositorio de Terminales
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda){
		RepositorioTerminales.getInstance().addResultadoBusqueda(unResultadoBusqueda);
	}

}
