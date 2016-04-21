package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public abstract class POIConServicio extends POI {

		
	//ATRIBUTOS
	
	public POIConServicio(Point unaUbicacion, Comuna unaComuna) {
		super(unaUbicacion, unaComuna);
	}

	private ArrayList<Servicio> 	servicios;
	
	
	//GETERS Y SETERS
	
	public ArrayList<Servicio> getColeccionServicios(){
		return servicios;
	}
	
	public void setColeccionServicios(ArrayList<Servicio> coleccionDeServicios){
		servicios = coleccionDeServicios;
	}
	
	public void addServicio(Servicio unServicio){
		servicios.add(unServicio);
	}
	
	//METODOS
	
	public boolean estaDisponible(String unNombreDeServicio,DateTime unTiempo){
		if(unNombreDeServicio == null){
			return this.algunServicioDisponible();
		} else {		
			return this.servicioDisponible(unNombreDeServicio,unTiempo);
		}
	}
	
	public boolean algunServicioDisponible(){
		DateTime 	horaDelMomento = new DateTime();	//Instancio la hora del momento
		return	this.getColeccionServicios().stream().
				anyMatch(servicio -> servicio.estaDentroDelRangoDeAtencion(horaDelMomento));
	}
	
	public boolean servicioDisponible(String unNombreDeServicio, DateTime unTiempo){
		return this.buscarServicio(unNombreDeServicio).estaDentroDelRangoDeAtencion(unTiempo);
	}
	
	public Servicio buscarServicio(String unNombreDeServicio){
		return this.getColeccionServicios().stream().
				filter(servicio -> servicio.getNombre() == unNombreDeServicio).
				collect(Collectors.toList()).get(0); //SE SUPONE QUE EL SERVICIO INGRESADO SIEMPRE ES VALIDO
	}
}
