package Adapters;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Converter.PointConverter;

@Entity 
public class PolygonAdapter {

	//Atributos
	@Id
	@GeneratedValue
	private int id;
	@OneToMany (cascade = CascadeType.ALL)
	@ElementCollection(targetClass = PointConverter.class)
	private List<PointAdapter> listaDePoints;
	
	public PolygonAdapter() {
		super();
		listaDePoints = new ArrayList<PointAdapter>();
	}
	
	//Metodos
	
	public PolygonAdapter agregarPoint(Point point){
		PointAdapter pointAdaptado = new PointAdapter();
		pointAdaptado.almacenaPoint(point);
		listaDePoints.add(pointAdaptado);
		return this;
	}
	
	public Polygon getPolygon(){
		Polygon poligono = new Polygon();
		listaDePoints.forEach( point -> poligono.add(point.getPoint()));
		return poligono;
	}
	
	public Integer getId() {  return id;  }
	public void setId(Integer id) {  this.id = id;  }

}
