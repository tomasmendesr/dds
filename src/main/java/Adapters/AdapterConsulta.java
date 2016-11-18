package Adapters;

import java.util.List;

import Model.POI;

public interface AdapterConsulta  {
	public List<POI> realizarConsulta(String unaConsulta); // Este metodo lo van a entender los componentes
	   // externos. Y cada uno va a retornar la lista de
	   // POIs correspondiente.
// HACER una cache persistente con mongo para los pois que encuentra el servicio externo
//en el momento de buscar los pois fijarse primero si esta en la cache y sino buscar en todos los origenes de datos

}
