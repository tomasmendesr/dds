package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

public class Mapa {

	//CONSTRUCTOR
	
	public Mapa(Point unaUbicacion){
		this.setColeccionDePOIS(); //Inicizializa ArrayList de POIS
		this.setUbicacion(unaUbicacion);
	}
	
	//ATRIBUTOS
	
	private ArrayList<POI> coleccionDePOIS;
	private Point ubicacion;

	//GETTERS Y SETTERS
	
	public Point getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public void setColeccionDePOIS(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	public ArrayList<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	
	// Calculo de cercania
	public boolean estaCercaDe(POI unPOI){
		return unPOI.estaCercaDe(this.getUbicacion());
	}

	// Busqueda por texto libre
	public ArrayList<POI> buscarPorTextoLibre(String unTag){
		return (ArrayList<POI>) this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}
	
	

}
