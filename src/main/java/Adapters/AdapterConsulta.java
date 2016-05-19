package Adapters;

import java.util.List;


import Master.POI;

public interface AdapterConsulta {
	
	public List<POI> realizarConsulta(String unaConsulta); // Este metodo lo van a entender los componentes
														   // externos. Y cada uno va a retornar la lista de 
														   // POIs correspondiente.
}



