package Master;

import java.util.ArrayList;
import java.util.List;


import ObserversTerminal.FuncionalidadExtraTerminal;

public class Terminal {
	
	//ATRIBUTOS
	private RepositorioPOIs			 				repositorioPOIs;
	private List<FuncionalidadExtraTerminal>		observers;
	private String									nombreTerminal;
		
	//CONSTRUCTOR
	public Terminal(String nombre, RepositorioPOIs unRepositorioDePOIs){
		this.setRepositorioPOIs(unRepositorioDePOIs);
		this.setNombreTerminal(nombre);
		observers = new ArrayList<FuncionalidadExtraTerminal>();
	}
	
	//GETERS Y SETERS
	public String getNombreTerminal(){
		return nombreTerminal;
	}
	
	public void setNombreTerminal(String nombre){
		nombreTerminal = nombre;
	}
	
	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}
	
	public List<FuncionalidadExtraTerminal> getObservers() {
		return observers;
	}

	public void addObserver(FuncionalidadExtraTerminal observer){
		observers.add(observer);
	}
	
	public void setObservers(List<FuncionalidadExtraTerminal> observers) {
		this.observers = observers;
	}
	
	
	//METODOS
	
	//Consultar Busqueda POIs con TiempoMax
	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		//this.contabilizarBusquedaXFecha(resultadoBusqueda);
		observers.forEach(observer -> observer.realizarAccion(this, resultadoBusqueda));
		return poisEncontrados;
	}
		
	
	/*public Integer getResultadoTotal(){	//suma de todos los resultados parciales
		return this.getResultadosParciales().stream().reduce(0,(a,b)-> a + b);
	}*/

}
