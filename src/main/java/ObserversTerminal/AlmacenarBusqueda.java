package ObserversTerminal;

import Repos.RepositorioBusquedas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@DiscriminatorValue("1")
public class AlmacenarBusqueda extends AccionesTerminal  {

	 // Guardo resultado en el Repositorio de Terminales
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda){
		RepositorioBusquedas.getInstance().guardarBusqueda(unResultadoBusqueda);
	}

}
