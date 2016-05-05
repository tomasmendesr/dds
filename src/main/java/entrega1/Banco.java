package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class Banco extends POIConServicio {

	//CONSTRUCTOR
	
	public Banco(Point ubicacion, Comuna comuna8) {
		super(ubicacion, comuna8);
		this.instancioColeccionDeServiciosDeBanco();
	}
	
	public void instancioColeccionDeServiciosDeBanco(){
		//Instacio rango de atencion de banco
		double	horaDeApertura 				= 10.0;
		double	horaDeCierre				= 15.0;
		int		diaDeInicioDeAtencion		= 1;
		int		diaDeFinDeAtencion			= 5;
		RangoDeAtencion rangoDeAtencionDeBanco = 
				new RangoDeAtencion(horaDeApertura,
									horaDeCierre,
									diaDeInicioDeAtencion,
									diaDeFinDeAtencion);
		
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
