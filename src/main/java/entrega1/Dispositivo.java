package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class Dispositivo {

	//CONSTRUCTOR
	
	public Dispositivo(Point unaUbicacion){
		this.setUbicacion(unaUbicacion);
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
	
	public void setColeccionDePOIS(ArrayList<POI> unaColeccionDePOIS){
		coleccionDePOIS = unaColeccionDePOIS;
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
	
	// Busqueda de puntos
	/* Busqueda de texto Libre. Acutalmente recibe un POI y un String como parámetro.
	 * Habria que modificar cispositivo en un futuro para que tenga una lista de los POIs cargados en el sistema
	 * y el método TextoLibre haria un mappeo de todos los pois buscando aquellos que los tags coinciden
	 * (dejaria de recibir POI como parametro). 
	 * 
	 * */
	
	
	// Busqueda por texto libre
	public void ArrayList<String> buscarPorTextoLibre(){
		
	}
	
	public boolean textoLibre(POI unPOI, String unTag){ 
		return unPOI.buscaTag(unTag); //Le manda un mensaje a la clase POI para que busque el Tag en su Array
	}
}
