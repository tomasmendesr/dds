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
	
}
