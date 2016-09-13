package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("AB")
public class AlmacenarBusqueda extends FuncionalidadExtraTerminal  {

	//ATRIBUTOS
	private List<ResultadoBusqueda>			listaDeResultados;

	//CONSTRUCTOR
	public AlmacenarBusqueda(){
		listaDeResultados = new ArrayList<ResultadoBusqueda>();
	}

	//Getters y setters
	public List<ResultadoBusqueda> getListaDeResultados() {
		return listaDeResultados;
	}

	public void setListaDeResultados(List<ResultadoBusqueda> listaDeResultados) {
		this.listaDeResultados = listaDeResultados;
	}
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda){
		this.almacenarResultado(unResultadoBusqueda);
	}
	public void almacenarResultado(ResultadoBusqueda unResultadoBusqueda){
		listaDeResultados.add(unResultadoBusqueda);
	}

}
