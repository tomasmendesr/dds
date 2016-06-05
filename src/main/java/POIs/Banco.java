package POIS;


import org.uqbar.geodds.Point;

public class Banco extends POIConServicio {

	//CONSTRUCTOR
	
	public Banco(Point ubicacion) {
		super(ubicacion);
	}
	
	//METODOS
	
	public double cercaniaRequerida(){
		return 500;
	}
	
}
