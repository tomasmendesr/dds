package entrega1;

import java.util.ArrayList;
import java.util.List;

public class ObserverConsultaCGP implements ObserverConsulta {

	//constructor
	
	public ObserverConsultaCGP(ComponenteExternoConsultaCGP componente){
		this.setComponente(componente);
	}
	
	//atributos
	
	private ComponenteExternoConsultaCGP componente;

	//geters y seters
	
	public ComponenteExternoConsultaCGP getComponente() {
		return componente;
	}

	public void setComponente(ComponenteExternoConsultaCGP componente) {
		this.componente = componente;
	}
		
	//metodos
	
	public List<POI> realizarConsulta(Consulta unaConsulta) {
		// mockito de componente externo mandandole la consulta adaptada
		// adaptar lo que devuelve componente externo
		// retornar lo adaptado como una List<POI>
		return null;
	}
	

}