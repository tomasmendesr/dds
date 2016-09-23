package POIs;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.uqbar.geodds.Point;

import POIsExt.RangoDeAtencion;

@Entity
@DiscriminatorValue("B")
public class Banco extends POIConServicio {

	//CONSTRUCTOR
	public Banco(Point ubicacion) {
		super(ubicacion);
		this.setearRangoDeAtencionBancario();
	}
	
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
	
	public double cercaniaRequerida(){
		return 500;
	}
	
}
