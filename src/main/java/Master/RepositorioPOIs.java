package Master;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.HashMap;

import Adapters.AdapterConsulta;

public class RepositorioPOIs {

	
	//CONSTRUCTOR
	
	public RepositorioPOIs(){
		coleccionDePOIS = new ArrayList<POI>();
		adapters = new ArrayList<AdapterConsulta>();
	}
	
	//ATRIBUTOS

	private List<POI> coleccionDePOIS;
	private List<AdapterConsulta> adapters;

	//GETTERS Y SETTERS
	
	public void setColeccionDePOIS(List<POI> unaColeccion){
		coleccionDePOIS = unaColeccion;
	}
	
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
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
	
	
	//Consultar Busqueda POIs
	
	public List<POI> consultarPOIs(String unaConsulta){ 
		List<POI> poisEncontrados =	this.buscarEnTodosLosOrigenesDeDatos(unaConsulta); 
		return poisEncontrados;			
	}
		
	public List<POI> buscarEnTodosLosOrigenesDeDatos(String unaConsulta){
		List<POI> listaDePOIsACompletar = new ArrayList<POI>();
		adapters.forEach(adapter -> listaDePOIsACompletar.addAll(adapter.realizarConsulta(unaConsulta)));
		listaDePOIsACompletar.addAll(this.buscarPorTextoLibre(unaConsulta));
		return listaDePOIsACompletar;
	}
		
	
}