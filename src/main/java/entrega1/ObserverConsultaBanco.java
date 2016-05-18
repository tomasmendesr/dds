package entrega1;

import java.util.ArrayList;
import java.util.List;

public class ObserverConsultaBanco implements ObserverConsulta{
	
	public ObserverConsultaBanco(ComponenteExternoConsultaBanco componente){
		this.setComponente(componente);
	}
	
	//atributos
	
	private ComponenteExternoConsultaBanco componente;
	
	//geters y setters
	
	public ComponenteExternoConsultaBanco getComponente() {
		return componente;
	}

	public void setComponente(ComponenteExternoConsultaBanco componente) {
		this.componente = componente;
	}
	
	public List<POI> realizarConsulta(Consulta unaConsulta) {
		// mockito de componente externo mandandole la consulta adaptada
		// adaptar lo que devuelve componente externo
		// retornar lo adaptado como una List<POI>
		return null;
	}

}
