package entrega1;

import org.uqbar.geodds.Point;

public class Maquina {

	//CONSTRUCTOR
	
	public Maquina(Point unaUbicacion){
		this.setUbicacion(unaUbicacion);
	}
	
	//ATRIBUTOS
	
	private Point ubicacion;

	//GETTERS Y SETTERS
	
	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Point getUbicacion() {
		return ubicacion;
	}
	
	
	//METODOS
	
	// Calculo de cercania
	public boolean estaCercaDe(POI unPOI){
		return unPOI.estaCercaDeMaquina(this);
	}
	
	/* Busqueda de texto Libre. Acutalmente recibe un POI y un String como parámetro.
	 * Habria que modificar Maquina en un futuro para que tenga una lista de los POIs cargados en el sistema
	 * y el método TextoLibre haria un mappeo de todos los pois buscando aquellos que los tags coinciden
	 * (dejaria de recibir POI como parametro). 
	 * 
	 * */
	
	public boolean textoLibre(POI unPOI, String unTag){ 
		return unPOI.buscaTag(unTag); //Le manda un mensaje a la clase POI para que busque el Tag en su Array
	}
}
