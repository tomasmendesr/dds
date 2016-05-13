package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Mapa {

	//CONSTRUCTOR
	
	public Mapa(){
		this.setColeccionDePOIS(); //Inicizializa ArrayList de POIS
	}
	
	//ATRIBUTOS
	
	private ArrayList<POI> coleccionDePOIS;

	//GETTERS Y SETTERS
	
	public void setColeccionDePOIS(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	public ArrayList<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	
	// Busqueda por texto libre
	public ArrayList<POI> buscarPorTextoLibre(String unTag){
		return (ArrayList<POI>) this.getColeccionDePOIS().stream()
								.filter(poi -> poi.detectarTagBuscado(unTag))
								.collect(Collectors.toList());
	}
	
	// Agregar, quitar y modificar
	public void agregarPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void quitarPOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	public void modificarPOI(POI unPOI){}
}
