package entrega1;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Usuario {
	
	// Constructor
	public Usuario (Mapa unMapa){
		mapa = unMapa;
	}
	

	// Atributos
	private 	Mapa mapa;
	public 		List<TipoBusqueda> buscadores;
	

	//Definir Alguna solucion
	
	public ArrayList <POI> consultarPois(POI unPOI){
	ArrayList<POI> resultados = new ArrayList<POI>();
		
/*
 	//Solucion 1
	resultados.addAll(buscadores.contains(BusquedaCGP).buscar(unPOI));
	resultados.addAll(buscadores.contains(BusquedaBanco).buscar(unPOI));
	resultados.addAll(mapa.buscarPorTextoLibre(unPOI.getNombre()));
	return resultados;
	}
		
		
*/	//Solucion 2
	
	List<List<POI>> busqueda = buscadores.stream().map(buscador -> buscador.buscar(unPOI)).collect(Collectors.toList());
	int i;
	for (i = 0; i < busqueda.size(); i++)
	{
		resultados.addAll(busqueda.get(i));
	}
	
	return resultados;
					
	}
	
	// Busqueda por texto libre
	
	public List<POI> buscarPorTextoLibre(String unTag){
		return mapa.getColeccionDePOIS().stream()
					.filter(poi -> poi.detectarTagBuscado(unTag))
					.collect(Collectors.toList());
	}
	
	// Getters y Setters

	public Mapa getMapa() {
		return mapa;
	}
	
	
}
