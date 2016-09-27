package POIsExt;

import org.hibernate.annotations.Type;
import org.uqbar.geodds.Polygon;
import javax.persistence.*;

@Entity @Table(name = "comunas")
public class Comuna {
	
	//ATRIBUTOS
	@Id	@Column(name="comuna_numero")
	private int			numeroDeComuna;

	/* @Column(name = "zona_numero",columnDefinition="Geometry",  nullable = true)
	@Type(type = "org.hibernate.spatial.GeometryType") */
	private Polygon 	zona;

	//Constructor
	public Comuna(){ }
	
	public Comuna(int numeroDeComuna){
		this.setNumeroDeComuna(numeroDeComuna);
	}

	//GETTERS Y SETTERS 
	public Polygon getZona() {
		return zona;
	}

	public void setZona(Polygon zona) {
		this.zona = zona;
	}

	public int getNumeroDeComuna() {
		return numeroDeComuna;
	}

	public void setNumeroDeComuna(int numeroDeComuna) {
		this.numeroDeComuna = numeroDeComuna;
	}
	
	
}


