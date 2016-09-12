package POIsExt;

import org.uqbar.geodds.Polygon;

import javax.persistence.*;

public class Comuna {
	
	//Constructor

	public Comuna(){ }
	
	public Comuna(int numeroDeComuna){
		this.setNumeroDeComuna(numeroDeComuna);
	}
	
	
	//ATRIBUTOS
	
	private Polygon 	zona;
	@Id
	@GeneratedValue
	@Column(name="COMUNA_ID")
	private int			numeroDeComuna;
	
	//GETERS Y SETERS 
	
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


