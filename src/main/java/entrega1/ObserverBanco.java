package entrega1;

import java.util.ArrayList;

public class ObserverBanco extends TipoBusqueda {
	
	//Atributo
	
	private ComponenteBancoExterno buscadorBanco;

	@Override
	public ArrayList<POI> buscar(POI unPOI) {
		ArrayList<POI> resultados = new ArrayList<POI>();
		//Usar buscarBancos de la clase ComponenteBancoExterno
		//Falta adaptar el POI recibido a la entrada del componente externo y adaptar su salida a un array de POIs de nuestro sistema
		return resultados;
	}

}
