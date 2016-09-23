package POIsExt;

import org.uqbar.geodds.Polygon;


import javax.persistence.*;

@Entity
public class Comuna {
	
	//ATRIBUTOS
	@Id	@Column(name="COMUNA_NUMERO")
	private int			numeroDeComuna;
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


