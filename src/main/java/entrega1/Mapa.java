package entrega1;

import java.util.ArrayList;

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
	
	public ArrayList<POI> getCollecionDePOIS(){
		return coleccionDePOIS;
	}
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	

}
