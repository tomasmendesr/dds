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
	private int id;
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
	

	
	

}
