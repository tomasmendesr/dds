package Master;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import Adapters.AdapterConsulta;

public class RepositorioPOIs {

	
	//CONSTRUCTOR
	
	public RepositorioPOIs(){
		this.instanciarNuevaColeccionDePOIs(); //Inicializa ArrayList de POIS
		adapters = new ArrayList<AdapterConsulta>();
		coleccionDeResultados = new ArrayList<Registro>();
	}
	
	//ATRIBUTOS
	
	private List<POI> coleccionDePOIS;
	private List<AdapterConsulta> adapters;
	private List<Registro> coleccionDeResultados;

	//GETTERS Y SETTERS
	
	public void instanciarNuevaColeccionDePOIs(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	public List<Registro> getColeccionDeResultados(){
		return coleccionDeResultados;
	}
	
	//Busqueda por texto libre
	
	public List<POI> buscarPorTextoLibre(String unTag){
		return this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}
	
	//ABM POIs
	
	//ver como hacer para dejar de suponer que el POI ingresado es siempre valido
	
	public void agregarPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void quitarPOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	// Agregar y quitar adapters consulta
	public void agregarAdapter(AdapterConsulta unAdapter){
		adapters.add(unAdapter);
	}
	
	public void quitarAdapter(AdapterConsulta unAdapter){
		adapters.remove(unAdapter);
	}
	
	// Lista de Resultados
	
	public void agregarResultado(Registro unResultado){
		coleccionDeResultados.add(unResultado);
	}
	
	//Consultar Busqueda POIs
	
	public List<POI> consultarPOIs(String unaConsulta){ // no se me ocurre otra forma
		List<POI> poisEncontrados =	this.buscarEnTodosLosOrigenesDeDatos(unaConsulta); 
		return poisEncontrados;			
	}
		
	public List<POI> buscarEnTodosLosOrigenesDeDatos(String unaConsulta){
		List<POI> listaDePOIsACompletar = new ArrayList<POI>();
		adapters.forEach(adapter -> listaDePOIsACompletar.addAll(adapter.realizarConsulta(unaConsulta)));
		listaDePOIsACompletar.addAll(this.buscarPorTextoLibre(unaConsulta));
		return listaDePOIsACompletar;
	}
		
	//Consultar Busqueda POIs con TiempoMax
	
	public List<POI> consultarPOIs(String unaConsulta, double tiempoMax){ // no se me ocurre otra forma
		double tInicio, tFin, tiempo;
		tInicio = System.currentTimeMillis();
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = this.buscarEnTodosLosOrigenesDeDatos(unaConsulta); // agrega todos los pois encontrados a la coleccion poisEncontrados
		tFin = System.currentTimeMillis();
		tiempo = (tFin - tInicio) / 1000;
		if (tiempo > tiempoMax){
			System.out.println("Mail enviado"); // como enviar mail? Y a quien?
		}
		int cantidadPoisEncontrados = poisEncontrados.size();
		this.almacenarResultados(unaConsulta, cantidadPoisEncontrados, tiempo); 
		return poisEncontrados;			
	}
	
	public void almacenarResultados(String consulta, int cantidadPoisEncontrados, double duracion){
		Registro resultado = new Registro(consulta,cantidadPoisEncontrados, duracion);
		this.agregarResultado(resultado);
	}

}