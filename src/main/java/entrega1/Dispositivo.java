package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

public class Dispositivo {

	//CONSTRUCTOR
	
	public Dispositivo(Point unaUbicacion){
		this.setUbicacion(unaUbicacion);
		this.setColeccionDePOIS(); //Inicizializa ArrayList de POIS
	}
	
	//ATRIBUTOS
	
	private Point ubicacion;
	private ArrayList<POI> coleccionDePOIS;

	//GETTERS Y SETTERS
	
	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Point getUbicacion() {
		return ubicacion;
	}
	
	public void setColeccionDePOIS(){
		coleccionDePOIS = new ArrayList<POI>();
	}
	
	public ArrayList<POI> getCollecionDePOIS(){
		return coleccionDePOIS;
	}
	
	public void addPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	//METODOS
	
	// Calculo de cercania
	
	public boolean estaCercaDe(POI unPOI){
		return unPOI.estaCercaDeDispositivo(this);
	}
	
	
	// Calculo de disponibilidad
	
	public boolean estaDisponible(POI unPOI, String unNombreDeServicio,Tiempo unTiempo){
		return unPOI.estaDisponible(unNombreDeServicio,unTiempo);
	}	
		
	
	// Busqueda por texto libre

	public ArrayList<POI> buscarPorTextoLibre(String tagBuscado){
		return coleccionDePOIS .stream()
				.filter(poi -> poi.detectarTagBuscado(tagBuscado))
				.collect(Collectors.toCollection(ArrayList<POI>::new));
	}

	public String obtenerDireccion(POI unPOI){
		return unPOI.getDireccion();
	}
}
