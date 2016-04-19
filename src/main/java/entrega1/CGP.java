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
		ArrayList<Servicio> coleccionDeServiciosDeCGP = new ArrayList<Servicio>();
		List<Double> horasDeAtencionDNI = Arrays.asList(10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0);
		List<Integer> diasDeAtencionDNI = Arrays.asList(1,2,3,4,5,6);
		RangoDeAtencion rangoDeAtencionDNI = new RangoDeAtencion(horasDeAtencionDNI,diasDeAtencionDNI);
		Servicio tramiteDeDNI = new Servicio("Tramite de DNI",rangoDeAtencionDNI);
			
		//Instancion servicio de licnencias de conducir
		List<Double> horasDeAtencionLicenciaConducir = Arrays.asList(11.0,12.0,13.0,14.0,15.0,16.0);
		List<Integer> diasDeAtencionLicenciaConducir = Arrays.asList(1,2,3,4,5);
		RangoDeAtencion rangoDeAtencionLicenciaConducir = new RangoDeAtencion(horasDeAtencionLicenciaConducir,diasDeAtencionLicenciaConducir);
		Servicio licenciaConducir = new Servicio("Tramite de licencia de conducir",rangoDeAtencionLicenciaConducir);
				
		//Termino de setear la coleccion de servicios de CGP
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
