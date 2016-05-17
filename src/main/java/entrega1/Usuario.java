package entrega1;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Usuario {
	
	// Constructor
	public Usuario (Mapa unMapa){
		this.setMapa(unMapa);
	}
	

	// Atributos
	private 	Mapa mapa;
	public 		List<TipoBusqueda> buscadores;
	

	// Busqueda 
	
	public List<POI> consultarPOIs(Consulta unaConsulta){
		return unaConsulta.realizarConsulta();
	}
	
	public List<POI> consultarPois(POI unPOI){
		List<POI> resultados = new ArrayList<POI>();
		buscadores.stream().forEachOrdered(buscador -> buscador.buscar(unPOI, resultados));
		resultados.addAll(this.buscarPorTextoLibre(unPOI.getNombre()));
		return resultados;
	}
	
	// Busqueda por texto libre
	
	protected List<POI> buscarPorTextoLibre(String unTag){
		return mapa.getColeccionDePOIS().stream()
					.filter(poi -> poi.detectarTagBuscado(unTag))
					.collect(Collectors.toList());
	}
	
	// Getters y Setters

	public Mapa getMapa() {
		return mapa;
	}
	
	public void setMapa(Mapa unMapa) {
		mapa = unMapa;
	}
	
	public void agregarBuscador(TipoBusqueda unBuscador) {
		buscadores.add(unBuscador);
	}

}
