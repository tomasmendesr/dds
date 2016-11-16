package POIs;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.uqbar.geodds.Point;

import POIsExt.RangoDeAtencion;

@Entity @DiscriminatorValue("3")
public class Banco extends POIConServicio {

	//CONSTRUCTOR
	public Banco(Point ubicacion) {
		super(ubicacion);
		this.setearRangoDeAtencionBancario();
	}
	@OneToMany(cascade = CascadeType.ALL) 
	private List<RangoDeAtencion> listaDeRangosDeAtencion;
	
	public Banco(){ }
	
	//METODOS
	private void setearRangoDeAtencionBancario(){
		RangoDeAtencion rangoBancarioLunes = new RangoDeAtencion(1,10,0,15,0);
		RangoDeAtencion rangoBancarioMartes = new RangoDeAtencion(2,10,0,15,0);
		RangoDeAtencion rangoBancarioMiercoles = new RangoDeAtencion(3,10,0,15,0);
		RangoDeAtencion rangoBancarioJueves = new RangoDeAtencion(4,10,0,15,0);
		RangoDeAtencion rangoBancarioViernes = new RangoDeAtencion(5,10,0,15,0);
		List<RangoDeAtencion> rangoDeAtencionBancario = new ArrayList<RangoDeAtencion>();
		rangoDeAtencionBancario.add(0, rangoBancarioLunes);		rangoDeAtencionBancario.add(1, rangoBancarioMartes);
		rangoDeAtencionBancario.add(2, rangoBancarioMiercoles);	rangoDeAtencionBancario.add(3, rangoBancarioJueves);
		rangoDeAtencionBancario.add(4, rangoBancarioViernes);
		this.setListaDeRangosDeAtencion(rangoDeAtencionBancario);
	}
	
	public boolean estaDisponible(String unNombreDeServicio,LocalDateTime unTiempo){
		if( listaDeRangosDeAtencion.stream().anyMatch(rangoDeAtencion -> rangoDeAtencion.disponible(unTiempo))){
			return super.estaDisponible(unNombreDeServicio, unTiempo);
		} else {
			return false;
		}
	}
	
	public double cercaniaRequerida(){
		return 500;
	}
	
	//GETTERS y SETTERS
	public List<RangoDeAtencion> getListaDeRangosDeAtencion() {
		return listaDeRangosDeAtencion;
	}

	public void setListaDeRangosDeAtencion(List<RangoDeAtencion> unaListaDeRangosDeAtencion) {
		listaDeRangosDeAtencion =  unaListaDeRangosDeAtencion;
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getListaDeRangosDeAtencion().add(unRangoDeAtencion);
	}
}
