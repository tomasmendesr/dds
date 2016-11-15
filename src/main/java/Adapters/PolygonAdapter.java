package Adapters;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Converter.PointConverter;

@Entity 
public class PolygonAdapter {

	//Atributos
	@Id
	private Long id;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@ElementCollection(targetClass = PointConverter.class)
	private List<PointAdapter> listaDePoints;

	public PolygonAdapter() { }
	
	public PolygonAdapter(Long id) {
		super();
		setId(id);
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
	
	
	public Long getId() {  return id;  }
	
	public void setId(Long id) {
		this.id = id;
	}

	public List<PointAdapter> getListaDePoints() {
		return listaDePoints;
	}

	public void setListaDePoints(List<PointAdapter> listaDePoints) {
		this.listaDePoints = listaDePoints;
	}



}
