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
	
	public void quitarTag(POI unPOI, String tag){
		unPOI.removeTag(tag);
	}
}
