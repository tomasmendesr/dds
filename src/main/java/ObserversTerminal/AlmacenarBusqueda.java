package ObserversTerminal;

import Master.GestorDeReportes;
import Master.ResultadoBusqueda;
import Master.Terminal;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class AlmacenarBusqueda implements FuncionalidadExtraTerminal  {

	//ATRIBUTOS
	private GestorDeReportes 				gestorDeReportes;
	private List<ResultadoBusqueda>			listaDeResultados;

	//CONSTRUCTOR
	public AlmacenarBusqueda(GestorDeReportes unGestor){
		listaDeResultados = new ArrayList<ResultadoBusqueda>();
		this.setGestorDeReportes(unGestor);
	}

	//Getters y setters
	public List<ResultadoBusqueda> getListaDeResultados() {
		return listaDeResultados;
	}

	public void setListaDeResultados(List<ResultadoBusqueda> listaDeResultados) {
		this.listaDeResultados = listaDeResultados;
	}

	public void setGestorDeReportes(GestorDeReportes unGestor){
		this.gestorDeReportes = unGestor;
	}
	
	@Override
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda){
		this.almacenarResultado(unResultadoBusqueda);
	}
	public void almacenarResultado(ResultadoBusqueda unResultadoBusqueda){
		this.getListaDeResultados().add(unResultadoBusqueda);
	}

}
