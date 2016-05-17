package entrega1;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

	//CONSTRUCTOR
	
	public Mapa(){
		this.instanciarNuevaColeccionDePOIs(); //Inicializa ArrayList de POIS
	}
	
	//ATRIBUTOS
	
	private List<POI> coleccionDePOIS;

	//GETTERS Y SETTERS
	
	public void instanciarNuevaColeccionDePOIs(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void removePOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
		
}
