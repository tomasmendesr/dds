package ObserversTerminal;

import Repos.RepositorioTerminales;

import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
public class AlmacenarBusqueda extends FuncionalidadExtraTerminal  {

	@Override // Guardo resultado en el Repositorio de Terminales
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda){
		RepositorioTerminales.getInstance().addResultadoBusqueda(unResultadoBusqueda);
	}

}
