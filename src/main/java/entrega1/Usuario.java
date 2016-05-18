package entrega1;

import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
	
	// Constructor
	public Usuario (Mapa unMapa){
		this.setMapa(unMapa);
	}
	

	// Atributos
	private Mapa mapa;
	private List<Observer> observers; //Alguna clase del sistema la tiene que conocer

	// Busqueda 
	
	/*Este metodo podria llamarse consultarPoisPorTipoConsulta que recibe un solo tipo de consulta
	 * y despues otro que se llame consultarPois que recibe una lista de consultas y 
	 * llama a esta funcion por cada tipo (ademas de hacer buscarPorTextoLibre)*/
	public List<POI> consultarPois(Consulta unaConsulta){
		return unaConsulta.realizarConsultaDe(this);
	}
	
	// Busqueda por texto libre
	
	protected List<POI> buscarPorTextoLibre(String unTag){
		return mapa.getColeccionDePOIS().stream()
					.filter(poi -> poi.detectarTagBuscado(unTag))
					.collect(Collectors.toList());
	}
	
	// Getters y Setters

	public Mapa getMapa() {
		return mapa;
	}
	
	public void setMapa(Mapa unMapa) {
		mapa = unMapa;
	}
	
	public void agregarObserver(Observer unObserver) {
		observers.add(unObserver);
	}

}
