package POIsExt;

import org.uqbar.geodds.Polygon;

import Master.Terminal;

import javax.persistence.*;

@Entity
public class Comuna {
	
	//Constructor

	public Comuna(){ }
	
	public Comuna(int numeroDeComuna){
		this.setNumeroDeComuna(numeroDeComuna);
	}
	
	
	//ATRIBUTOS
	
	private Polygon 	zona;
	
	@Id
	@Column(name="COMUNA_NUMERO")
	private int			numeroDeComuna;
	
	@OneToMany
	@Column(name = "TERMINAL")
	@JoinColumn (name = "TERMINAL_ID", foreignKey = @ForeignKey(name = "TERMINAL_ID_FK"))
	private Terminal terminal;
	
	@Column(name="POIs")
	@OneToMany
	@JoinColumn(name = "POI_ID", foreignKey = @ForeignKey(name = "POI_ID_FK"))
	private Master.POI					POI;
	
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


