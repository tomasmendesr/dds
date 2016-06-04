package Adapters;

import java.util.ArrayList;
import java.util.List;

import AdaptersExt.JSON;
import ComponentesExternos.ComponenteExternoConsulta;
import ComponentesExternos.ComponenteExternoConsultaBanco;
import Master.POI;

public class AdapterConsultaBanco implements AdapterConsulta {

	//CONSTRUCTOR
	
	public AdapterConsultaBanco(ComponenteExternoConsulta componente){
		this.setComponenteExternoConsulta(componente);
	}
	
	//ATRIBUTOS
	
	private ComponenteExternoConsulta componenteExterno;
	
	private void setComponenteExternoConsulta(ComponenteExternoConsulta componente){
		componenteExterno = componente;
	}
	
	private ComponenteExternoConsulta getComponenteExterno(){
		return componenteExterno;
	}
	
	//METODOS
	
	
	public List<POI> realizarConsulta(String unaConsulta) {
		JSON consultaSinAdaptar;
		List<POI> consultaAdaptada;
		consultaSinAdaptar = this.getComponenteExterno().realizarConsultaBanco(unaConsulta);
		consultaAdaptada = this.adaptarConsulta(consultaSinAdaptar);
		return consultaAdaptada;
	}
	
	public List<POI> adaptarConsulta(JSON consultaSinAdaptar){
		List<POI> consultaAdaptada = new ArrayList<POI>();
		//magia que adapta la consulta del formato JSON a un List<POI>
		return consultaAdaptada;
	}

}
