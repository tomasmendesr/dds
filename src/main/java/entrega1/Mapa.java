package entrega1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {

	//CONSTRUCTOR
	
	public Mapa(){
		this.instanciarNuevaColeccionDePOIs(); //Inicizializa ArrayList de POIS
	}
	
	//ATRIBUTOS
	
	private List<POI> coleccionDePOIS;

	//GETTERS Y SETTERS
	
	public void instanciarNuevaColeccionDePOIs(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void removePOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	// Busqueda por texto libre
	
	public List<POI> buscarPorTextoLibre(String unTag){
		return this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}
	

}
