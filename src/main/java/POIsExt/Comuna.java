package POIsExt;

import javax.persistence.*;

import Adapters.PolygonAdapter;

@Entity
public class Comuna {
	
	//ATRIBUTOS
	@Id	@Column(name="comuna_numero")
	private Long			numeroDeComuna;

	@OneToOne(cascade = CascadeType.ALL)
	private PolygonAdapter 	zona;

	//Constructor
	public Comuna(){ }
	
	public Comuna(Long numeroDeComuna){
		this.setNumeroDeComuna(numeroDeComuna);
	}

	//GETTERS Y SETTERS 
	public PolygonAdapter getZona() {
		return zona;
	}

	public void setZona(PolygonAdapter zona) {
		this.zona = zona;
	}

	public Long getNumeroDeComuna() {
		return numeroDeComuna;
	}

	public void setNumeroDeComuna(Long numeroDeComuna) {
		this.numeroDeComuna = numeroDeComuna;
	}
	
	
}


