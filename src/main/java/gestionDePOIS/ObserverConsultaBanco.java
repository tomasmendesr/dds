package gestionDePOIS;

import java.util.List;

public class ObserverConsultaBanco implements ObserverConsulta{
	
	public ObserverConsultaBanco(ComponenteBanco componente){
		this.setComponente(componente);
	}
	
	//atributos
	
	private ComponenteBanco componente;
	
	//getters y setters
	
	public ComponenteBanco getComponente() {
		return componente;
	}

	public void setComponente(ComponenteBanco componente) {
		this.componente = componente;
	}
	
	public List<POI> realizarConsulta(String unaConsulta) {
		
		// mockito de componente externo mandandole la consulta adaptada
		// adaptar lo que devuelve componente externo
		// retornar lo adaptado como una List<POI>
		return null;
	}

}
