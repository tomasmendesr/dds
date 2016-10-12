package Model;

import java.util.List;

import com.mongodb.client.model.geojson.Point;

import POIsExt.Comuna;

public class PoisDeBusqueda { //Es un DTO. Se guarda en el resultadoBusqueda

	//Atributos
	private Long id;
	private Point ubicacion;
	private String nombre;
	private String direccion;
	private List<String> tags;
	private Comuna comuna;
	
	//Getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Point getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

}
