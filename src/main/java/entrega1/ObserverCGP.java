package entrega1;

import java.util.ArrayList;

public class ObserverCGP extends TipoBusqueda {
	
	//Atributos
	
	private ComponenteCGPExterno buscadorCGP;

	@Override
	public ArrayList<POI> buscar(POI unPOI) {
		ArrayList<POI> resultados = new ArrayList<POI>();
		//Usar buscarCGPs de la clase ComponenteCGPExterno
		//Falta adaptar el POI recibido a la entrada del componente externo y adaptar su salida a un array de POIs de nuestro sistema
		//Hay que mokear en el sistema las clases: "CentroDTO", "serviciosDTO", "rangosServicioDTO"
		return resultados;
	}

}