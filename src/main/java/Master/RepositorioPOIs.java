package Master;

import Adapters.AdapterConsulta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPOIs {

	//ATRIBUTOS
	private static RepositorioPOIs repositorioPOIs;
	private List<POI> coleccionDePOIS;
	private List<AdapterConsulta> adapters;

	//CONSTRUCTOR
	private RepositorioPOIs() {
		coleccionDePOIS = new ArrayList<POI>();
		adapters = new ArrayList<AdapterConsulta>();
	}

	//Singleton del Repo
	public static RepositorioPOIs getInstance() {
		if (repositorioPOIs == null) {
			repositorioPOIs = new RepositorioPOIs();
		}
		return repositorioPOIs;
	}

	public static void resetPOIs() {
		repositorioPOIs = null;
	}


	//GETTERS Y SETTERS
	public void setColeccionDePOIS(List<POI> unaColeccion) {
		coleccionDePOIS = unaColeccion;
	}

	public List<POI> getColeccionDePOIS() {
		return coleccionDePOIS;
	}

	//Busqueda por texto libre

	public List<POI> buscarPorTextoLibre(String unTag) {
		return this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}

	//ABM POIs

	//ver como hacer para dejar de suponer que el POI ingresado es siempre valido

	public void agregarPOI(POI unPOI) {
		coleccionDePOIS.add(unPOI);
	}

	public void quitarPOI(POI unPOI) {
		coleccionDePOIS.remove(unPOI);
	}

	// Agregar y quitar adapters consulta
	public void agregarAdapter(AdapterConsulta unAdapter) {
		adapters.add(unAdapter);
	}

	public void quitarAdapter(AdapterConsulta unAdapter) {
		adapters.remove(unAdapter);
	}


	//Consultar Busqueda POIs

	public List<POI> consultarPOIs(String unaConsulta) {
		List<POI> poisEncontrados = this.buscarEnTodosLosOrigenesDeDatos(unaConsulta);
		return poisEncontrados;
	}

	public List<POI> buscarEnTodosLosOrigenesDeDatos(String unaConsulta) {
		List<POI> listaDePOIsACompletar = new ArrayList<POI>();
		adapters.forEach(adapter -> listaDePOIsACompletar.addAll(adapter.realizarConsulta(unaConsulta)));
		listaDePOIsACompletar.addAll(this.buscarPorTextoLibre(unaConsulta));
		return listaDePOIsACompletar;
	}

	public void actualizarLocalesComerciales(String unTexto) {
		String[] campos = unTexto.split(";");
		String nombre = campos[0];
		String palabras = campos[1];
		String[] palabrasABuscar = palabras.split(" ");
		coleccionDePOIS.stream().forEach(poi -> poi.buscarLocalesPorNombre(nombre,palabrasABuscar));
	}

}