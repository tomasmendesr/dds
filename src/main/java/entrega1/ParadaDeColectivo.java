package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class ParadaDeColectivo extends POI {

	public ParadaDeColectivo(Point miUbicacion, Polygon miComuna) {
		super(miUbicacion, miComuna);
		ArrayList<Servicio> coleccionDeServiciosDeTransporte = new ArrayList<Servicio>();
		this.setColeccionServicios(coleccionDeServiciosDeTransporte);
		//una parada de colecitvo va a tener una coleccion de servicios con un solo servicio (servicio de transporte)
	}

	@Override
	public double cercaniaRequerida(){
		return 100.0;
	}
	
	@Override
	public boolean estaDisponible(String unNombreDeServicio,Tiempo unTiempo){
		return true;
	}
	
	
}
