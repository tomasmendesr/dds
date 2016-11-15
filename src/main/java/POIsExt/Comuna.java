package POIsExt;

import javax.persistence.*;

import Adapters.PolygonAdapter;

@Entity @Table(name = "comunas")
public class Comuna {
	
	//ATRIBUTOS
	@Id	@Column(name="comuna_numero")
	private int			numeroDeComuna;

	@OneToOne(cascade = CascadeType.ALL)
	private PolygonAdapter 	zona;

	//Constructor
	public Comuna(){ }
	
	public Comuna(int numeroDeComuna){
		this.setNumeroDeComuna(numeroDeComuna);
	}

	//GETTERS Y SETTERS 
	public PolygonAdapter getZona() {
		return zona;
	}

	public void setZona(PolygonAdapter zona) {
		this.zona = zona;
	}

	public int getNumeroDeComuna() {
		return numeroDeComuna;
	}

	public void setNumeroDeComuna(int numeroDeComuna) {
		this.numeroDeComuna = numeroDeComuna;
	}
	
	
}


