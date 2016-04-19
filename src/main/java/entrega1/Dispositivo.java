package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class Dispositivo {

	//CONSTRUCTOR
	
	public Dispositivo(Point unaUbicacion){
		this.setUbicacion(unaUbicacion);
		this.setColeccionDePOIS(coleccionDePOIS);
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
		
	// Busqueda por texto libre
	
	/*public ArrayList<String> buscarPorTextoLibre(String texto){
		return coleccionDePOIS 
				.stream()
				.filter(poi -> poi.detectarTagBuscado(texto))
				.map(poi -> poi.getDireccion());
	}*/
	
	
	// Creo que si usamos el metodo buscarPorTextoLibre, este metodo textoLibre esta de mas
	public boolean textoLibre(POI unPOI, String unTag){ 
		return unPOI.detectarTagBuscado(unTag); //Le manda un mensaje a la clase POI para que busque el Tag en su Array
	} 
}
