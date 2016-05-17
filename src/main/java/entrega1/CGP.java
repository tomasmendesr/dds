package entrega1;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class CGP extends POIConServicio {

	//CONSTRUCTOR
	
	public CGP(Point ubicacion) {
		super(ubicacion);
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
		
	
	//METODOS
	
	@Override
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getComuna().getZona().isInside(unaUbicacion);
	}


	
	
}
