package entrega1;

import java.util.ArrayList;
import java.util.List;

public class ObserverCGP extends TipoBusqueda {
	
	//Atributos
	
	private ComponenteCGPExterno buscadorCGP;

	@Override
	public void buscar(POI unPOI, List<POI> resultados) {
		List<POI> resultadosComponente = new ArrayList<POI>();
		//Usar buscarCGPs de la clase ComponenteCGPExterno
		//Falta adaptar el POI recibido a la entrada del componente externo y adaptar su salida a un array de POIs de nuestro sistema
		//Hay que mokear en el sistema las clases: "CentroDTO", "serviciosDTO", "rangosServicioDTO"
		resultados.addAll(resultadosComponente);
	}

}
