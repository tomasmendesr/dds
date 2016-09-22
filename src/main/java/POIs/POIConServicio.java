package POIs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import Master.POI;
import POIsExt.Servicio;

public abstract class POIConServicio extends POI {

		
	//ATRIBUTOS
	
	public POIConServicio(Point unaUbicacion) {
		super(unaUbicacion);
	}

	private List<Servicio> 	servicios;

	//METODOS
	
	public boolean estaDisponible(String unNombreDeServicio,LocalDateTime unTiempo){
		if(unNombreDeServicio == null){
			return this.algunServicioDisponible();
		} else {		
			return this.servicioDisponible(unNombreDeServicio,unTiempo);
		}//CAMBIAR NOMBRES de meTODOS EN ESTA CLASE
	}
	
	public boolean algunServicioDisponible(){
		LocalDateTime 	horaDelMomento = LocalDateTime.now();	//Instancio la hora del momento
		return	this.getColeccionServicios().stream().
				anyMatch(servicio -> servicio.estaDisponible(horaDelMomento));
	}
	
	public boolean servicioDisponible(String unNombreDeServicio, LocalDateTime unTiempo){
		return this.buscarServicio(unNombreDeServicio).estaDisponible(unTiempo);
	}
	
	public Servicio buscarServicio(String unNombreDeServicio){
		return this.getColeccionServicios().stream().
				filter(servicio -> servicio.getNombre() == unNombreDeServicio).
				collect(Collectors.toList()).get(0); //SE SUPONE QUE EL SERVICIO INGRESADO SIEMPRE ES VALIDO
	}

	//GETERS Y SETERS

	public List<Servicio> getColeccionServicios(){
		return servicios;
	}

	public void setListaDeServicios(List<Servicio> nuevaListaDeServicios){
		servicios = nuevaListaDeServicios;
	}

	public void addServicio(Servicio unServicio){
		servicios.add(unServicio);
	}
}
