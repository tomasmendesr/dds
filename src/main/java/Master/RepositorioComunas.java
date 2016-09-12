package Master;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import POIsExt.Comuna;

@Entity
public class RepositorioComunas {
	
	@OneToMany 
	@JoinColumn(name = "COMUNA_NUMERO")
	private List<Comuna> listaDeComunas;

	public List<Comuna> getListaDeComunas() {
		return listaDeComunas;
	}

	public void setListaDeComunas(List<Comuna> listaDeComunas) {
		this.listaDeComunas = listaDeComunas;
	}
	
}
