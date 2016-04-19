package entrega1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Banco extends POI {

	//CONSTRUCTOR
	
	public Banco(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
		this.instancioColeccionDeServiciosDeBanco();
	}
	
	public void instancioColeccionDeServiciosDeBanco(){
		//Instacio rango de atencion de banco
		List<Double> 	horasDeAtencionDeBanco = Arrays.asList(10.0,11.0,12.0,13.0,14.0);
		List<Integer>	diasDeAtencionDeBanco = Arrays.asList(1,2,3,4,5);
		RangoDeAtencion rangoDeAtencionDeBanco = new RangoDeAtencion(horasDeAtencionDeBanco,diasDeAtencionDeBanco);
		
		//Instancio servicios de banco
		Servicio atencionAlCliente = new Servicio("Atencion al cliente", rangoDeAtencionDeBanco);
		Servicio atencionTarjetasDeCredito = new Servicio("Atencion a tarjetas de credito", rangoDeAtencionDeBanco);
		
		//Instancio y seteo coleccion de servicios de banco
		ArrayList<Servicio> coleccionDeServiciosDeBanco = new ArrayList<Servicio>();
		coleccionDeServiciosDeBanco.add(atencionTarjetasDeCredito);
		coleccionDeServiciosDeBanco.add(atencionAlCliente);
		this.setColeccionServicios(coleccionDeServiciosDeBanco);
	}
	
	//METODOS
	
	public double cercaniaRequerida(){
		return 500;
	}
	
}
