package gestionDePOIS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

public class Mapa {

	//CONSTRUCTOR
	
	public Mapa(){
		this.instanciarNuevaColeccionDePOIs(); //Inicializa ArrayList de POIS
		coleccionDeObserversConsulta = new ArrayList<ObserverConsulta>();
		//this.inyectarDependencias();
	}
	
	//ATRIBUTOS
	
	private List<POI> coleccionDePOIS;
	private List<ObserverConsulta> coleccionDeObserversConsulta;

	//GETTERS Y SETTERS
	
	public void instanciarNuevaColeccionDePOIs(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	protected void inyectarDependencias(){
		this.inyectarObserversConsulta();
	}
	
	protected void inyectarObserversConsulta(){
		coleccionDeObserversConsulta = new ArrayList<ObserverConsulta>();
		coleccionDeObserversConsulta.add(new ObserverConsultaCGP());
		coleccionDeObserversConsulta.add(new ObserverConsultaBanco());
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
	
	public void esPOIvalido(POI unPOI){
		this.coleccionDePOIS.contains(unPOI);
	}
	
	public void agregarPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void quitarPOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	public void modificarUbicacion(POI unPOI, Point nuevaUbicacion){
		unPOI.setUbicacion(nuevaUbicacion);
	}
	
	public void modificarDireccion(POI unPOI, String nuevaDireccion){
		unPOI.setDireccion(nuevaDireccion);
	}
	
	public void modificarNombre(POI unPOI, String nuevoNombre){
		unPOI.setNombre(nuevoNombre);
	}
	
	public void modificarComuna(POI unPOI, Comuna nuevaComuna){
		unPOI.setComuna(nuevaComuna);
	}
	
	public void modificarRangoDeAtencion(POI unPOI, RangoDeAtencion nuevoRango){
		unPOI.setRangoDeAtencion(nuevoRango);
	}
	
	public void agregarTag(POI unPOI, String nuevoTag){
		unPOI.addTag(nuevoTag);
	}
	
	public void modificarTag(POI unPOI, String tagAModificar, String nuevoTag){
		unPOI.quitarTagAModificar(tagAModificar);
		unPOI.addTag(nuevoTag);
	}
	
	public void quitarTag(POI unPOI, String tag){
		unPOI.removeTag(tag);
	}
	
	// Agregar y quitar observers consulta
	
	public void agregarObserverConsulta(ObserverConsulta unObserver){
		coleccionDeObserversConsulta.add(unObserver);
	}
	
	public void quitarObserverConsulta(ObserverConsulta unObserver){
		coleccionDeObserversConsulta.remove(unObserver);
	}
	
	//Consultar Busqueda POIs
	
	public List<POI> consultarPOIs(String unaConsulta){
		List<POI> poisEncontrados = new ArrayList<POI>();
		this.buscarEnTodosLosOrigenesDeDatos(poisEncontrados, unaConsulta); // agrega todos los pois encontrados a la coleccion poisEncontrados
		return poisEncontrados;											  
	}
	
	public void buscarEnTodosLosOrigenesDeDatos(List<POI> listaDePOIsACompletar, String unaConsulta){
		coleccionDeObserversConsulta.forEach(observer -> listaDePOIsACompletar.addAll(observer.realizarConsulta(unaConsulta)));
		listaDePOIsACompletar.addAll(this.buscarPorTextoLibre(unaConsulta));
	}

}