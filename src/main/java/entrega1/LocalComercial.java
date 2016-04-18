package entrega1;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class LocalComercial extends POI {

	//CONSTRUCTOR
	
	public LocalComercial(Point unaUbicacion, Polygon unaComuna, Rubro unRubro){
	
		super(unaUbicacion,unaComuna);
		this.setRubro(unRubro);
		
	}
	
	
	//ATRIBUTOS
	
	private Rubro rubro;
	
	//GETERS Y SETERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	//METODOS
	
	public boolean estaCercaDeMaquina(Maquina unaMaquina){
		return this.getRubro().getRadioDeCercania().isInside(unaMaquina.getUbicacion());
	}
}
