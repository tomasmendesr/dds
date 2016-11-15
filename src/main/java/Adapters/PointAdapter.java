package Adapters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.Point;

@Entity
public class PointAdapter {
	
	@Id
	@GeneratedValue
	@Column(name="point_id")
	private Long id;

	private double latitud;
	private double longitud;
	
	
	public PointAdapter() {
		super();
	}
	
	public void almacenaPoint(Point point){
		latitud = point.latitude();
		longitud = point.longitude();
	}
	
	public Point getPoint(){
		return new Point(latitud, longitud);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	

	
	

}
