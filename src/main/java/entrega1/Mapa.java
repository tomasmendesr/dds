package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

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
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	
	// Busqueda por texto libre
	public ArrayList<POI> buscarPorTextoLibre(String unTag){
		return (ArrayList<POI>) this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}
	
	

}
