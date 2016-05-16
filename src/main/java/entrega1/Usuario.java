package entrega1;

import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
	
	// Constructor
	public Usuario (Mapa unMapa){
		this.setMapa(unMapa);
	}
	

	// Atributos
	private 	Mapa mapa;
	public 		List<TipoBusqueda> buscadores;
	

	//Definir Alguna solucion
	
	/*public ArrayList <POI> consultarPois(POI unPOI){
		ArrayList<POI> resultados = new ArrayList<POI>();
		
		//Solucion 1
		resultados.addAll(buscadores.contains(BusquedaCGP).buscar(unPOI));
		resultados.addAll(buscadores.contains(BusquedaBanco).buscar(unPOI));
		resultados.addAll(mapa.buscarPorTextoLibre(unPOI.getNombre()));
		
		
		
		//Solucion 2
		ArrayList<ArrayList<POI>> busqueda = new ArrayList<ArrayList<POI>>();
		busqueda = (ArrayList<ArrayList<POI>>) buscadores.stream().map(buscador -> buscador.buscar(unPOI)).collect(Collectors.toList());
		int i;
		for (i = 0; i < busqueda.size(); i++){
			resultados.addAll(busqueda.get(i));
		}
		
		return resultados;
	}*/
	
	// Getters y Setters

	public Mapa getMapa() {
		return mapa;
	}
	
	public void setMapa(Mapa unMapa) {
		mapa = unMapa;
	}
	
	
	
}
