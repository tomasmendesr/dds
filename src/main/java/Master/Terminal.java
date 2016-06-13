package Master;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ObserversTerminal.FuncionalidadExtraTerminal;

public class Terminal {
	
	//ATRIBUTOS
	private RepositorioPOIs			 				repositorioPOIs;
	private List<FuncionalidadExtraTerminal>		observers;
	private String									nombreTerminal;
	private List<ResultadoBusqueda>					busquedas;
		
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
	
	public List<ResultadoBusqueda> getResultadosBusqueda(){
		return busquedas;
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
		observers.forEach(observer -> observer.realizarAccion(this, resultadoBusqueda));
		this.guardarBusqueda(resultadoBusqueda); // Para hacer el reporte por usuarios
		return poisEncontrados;
	}
	
	
	
	// Necesite hacer esto para poder hacer el Reporte de Busquedas por Usuario. Ver como solucionarlo
	public void guardarBusqueda(ResultadoBusqueda unResultado){
		busquedas.add(unResultado);
	}
	
	public Integer obtenerResultadosTotales(){ // Obtengo la suma de la lista creada en resultadosTotales()
		return this.resultadosTotales().stream().reduce(0, (a,b) -> a + b);
	}
	
	public List<Integer> resultadosTotales(){ // Obtengo una lista con todas las cantidades de resultados de las busquedas
		return this.getResultadosBusqueda().stream()
		.map(resultado -> resultado.getCantidadDeResultados())
		.collect(Collectors.toList());
	}
	

}
