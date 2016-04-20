package entrega1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI {

	//CONSTRUCTOR
	
	public CGP(Point ubicacion, Polygon comuna) {
		super(ubicacion, comuna);
		this.instanciarColeccionDeServiciosDeCGP();
		
	}
	
	public void instanciarColeccionDeServiciosDeCGP(){
		//Instancio servicio de tramite de DNI
		double horarioDeApertura 			= 10.0;
		double horarioDeCierre 				= 19.0;
		int	   diaDeInicioDeAtencion		= 1;
		int	   diaDeFinDeAtencion			= 6;
		RangoDeAtencion rangoDeAtencionDNI = 
				new RangoDeAtencion(horarioDeApertura,horarioDeCierre,diaDeInicioDeAtencion,diaDeFinDeAtencion);
		Servicio tramiteDeDNI = new Servicio("Tramite de DNI",rangoDeAtencionDNI);
			
		//Instancion servicio de licnencias de conducir
		double horarioDeApertura1 			= 11.0;
		double horarioDeCierre1 			= 16.0;
		int	   diaDeInicioDeAtencion1		= 1;
		int	   diaDeFinDeAtencion1			= 5;
		RangoDeAtencion rangoDeAtencionLicenciaConducir =
				new RangoDeAtencion(horarioDeApertura1,horarioDeCierre1,diaDeInicioDeAtencion1,diaDeFinDeAtencion1);
		Servicio licenciaConducir = new Servicio("Tramite de licencia de conducir",rangoDeAtencionLicenciaConducir);
				
		//Termino de setear la coleccion de servicios de CGP
		ArrayList<Servicio> coleccionDeServiciosDeCGP = new ArrayList<Servicio>();
		coleccionDeServiciosDeCGP.add(licenciaConducir);
		coleccionDeServiciosDeCGP.add(tramiteDeDNI);
		this.setColeccionServicios(coleccionDeServiciosDeCGP);
	}
	
	//ATRIBUTOS
	
	
	
	
	//METODOS
	
	@Override
	public boolean estaCercaDeDispositivo(Dispositivo unDispositivo){
		return this.getComuna().isInside(unDispositivo.getUbicacion());		
	}
	
	
	
	
}
