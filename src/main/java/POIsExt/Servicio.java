package POIsExt;

import java.time.LocalDateTime;
import java.util.List;

public class Servicio {

	//CONSTRUCTOR 
	
	public Servicio(String unNombre,List<RangoDeAtencion> listaDeRangosDeAtencion){
		this.setNombre(unNombre);
		
	}
	
	//ATRIBUTOS
	
	String 						nombre;
	List<RangoDeAtencion>		listaDeRangosDeAtencion;
	
	//GETERS Y SETERunNombreS
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public List<RangoDeAtencion> getListaDeRangosDeAtencion() {
		return listaDeRangosDeAtencion;
	}

	public void setListaDeRangosDeAtencion(List<RangoDeAtencion> listaDeRangosDeAtencion) {
		this.listaDeRangosDeAtencion = listaDeRangosDeAtencion;
	}

	//METODOS
	

	public boolean estaDisponible(LocalDateTime unaMomento){
		return this.algunRangoDeAtencionDisponible(unaMomento);
	}

	public boolean algunRangoDeAtencionDisponible(LocalDateTime unMomento){
		return listaDeRangosDeAtencion.stream().anyMatch(rangoDeAtencion -> rangoDeAtencion.disponible(unMomento));
	}
		
}
