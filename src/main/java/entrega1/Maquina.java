package entrega1;

import org.uqbar.geodds.Point;

public class Maquina {

	//ATRIBUTOS
	
	private Point ubicacion;

	//GETERS Y SETERS
	
	

	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	//METODOS
	
	//CALCULO DE CERCANIA
	
	public boolean estaCercaDe(POI unPOI){
		return unPOI.estaCercaDeMaquina(this);
	}

	public Point getUbicacion() {
		return ubicacion;
	}
	
}
