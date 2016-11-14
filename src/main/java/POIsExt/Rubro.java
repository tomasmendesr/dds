package POIsExt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Rubro {
	
	//ATRIBUTOS
	@Id @GeneratedValue @Column(name = "rubro_id")
	private Long id;
	
	@Column(name = "radio_cercania")
	private Double radioDeCercania;
	
	//CONSTRUCTOR 	
	public Rubro(Double unRadioDeCercania){
		this.setRadioDeCercania(unRadioDeCercania);
	}	
	
	public Rubro(){ }
	
	
	//GETTERS Y SETTERS
	public Double getRadioDeCercania(){
		return radioDeCercania;
	}
	
	public void setRadioDeCercania(Double unRadioDeCercania){
		radioDeCercania = unRadioDeCercania;
	}
	
	
}
