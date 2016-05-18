package gestionDePOIS;

import java.util.List;

public class ObserverConsultaCGP implements ObserverConsulta{

	//constructor
	
	public ObserverConsultaCGP(ComponenteCGP componente){
		this.setComponente(componente);
	}
	
	//atributos
	
	private ComponenteCGP componente;

	//getters y setters

	public ComponenteCGP getComponente() {
		return componente;
	}

	public void setComponente(ComponenteCGP componente) {
		this.componente = componente;
	}
		
	//metodos
	
	public List<POI> realizarConsulta(String unaConsulta) {
		// mockito de componente externo mandandole la consulta adaptada
		// adaptar lo que devuelve componente externo
		// retornar lo adaptado como una List<POI>
		return null;
	}
	

}