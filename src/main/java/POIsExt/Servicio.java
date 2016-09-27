package POIsExt;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="servicios")
public class Servicio {

	//ATRIBUTOS
	@Id @GeneratedValue @Column(name="servicio_id")
	Long						id;
	@Column(name="nombre")
	String 						nombre;
	@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	List<RangoDeAtencion>		listaDeRangosDeAtencion;
	
	//CONSTRUCTOR 
	public Servicio(String unNombre,List<RangoDeAtencion> listaDeRangosDeAtencion){
		this.setNombre(unNombre);
		this.setListaDeRangosDeAtencion(listaDeRangosDeAtencion);
	}
	
	public Servicio(){}

	
	//METODOS
	public boolean estaDisponible(LocalDateTime unaMomento){
		return this.algunRangoDeAtencionDisponible(unaMomento);
	}

	public boolean algunRangoDeAtencionDisponible(LocalDateTime unMomento){
		return listaDeRangosDeAtencion.stream().anyMatch(rangoDeAtencion -> rangoDeAtencion.disponible(unMomento));
	}
	
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
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getListaDeRangosDeAtencion().add(unRangoDeAtencion);
	}
		
}
