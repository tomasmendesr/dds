package entrega1;

import org.uqbar.geodds.Polygon;

public class Rubro {
	
	//CONSTRUCTOR 
	
	public Rubro(Polygon unRadioDeCercania){
		this.setRadioDeCercania(unRadioDeCercania);
	}	
	
	//ATRIBUTOS
	
	private Polygon radioDeCercania;
	
	//GETERS Y SETERS
	
	public Polygon getRadioDeCercania(){
		return radioDeCercania;
	}
	
	public void setRadioDeCercania(Polygon unRadioDeCercania){
		radioDeCercania = unRadioDeCercania;
	}
	
	
}
