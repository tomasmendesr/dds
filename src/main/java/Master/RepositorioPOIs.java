package Master;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

import Adapters.AdapterConsulta;

public class RepositorioPOIs {

	
	//CONSTRUCTOR
	
	public RepositorioPOIs(){
		coleccionDePOIS = new ArrayList<POI>();
		adapters = new ArrayList<AdapterConsulta>();
		listaDeResultadosBusqueda = new ArrayList<ResultadoBusqueda>();
	}
	
	//ATRIBUTOS

	private List<POI> coleccionDePOIS;
	private List<AdapterConsulta> adapters;
	private List<ResultadoBusqueda> listaDeResultadosBusqueda;
	private HashMap<LocalDate,Integer> cantidadBusquedasXFecha;

	//GETTERS Y SETTERS
	
	public List<ResultadoBusqueda> getListaDeResultadosBusqueda() {
		return listaDeResultadosBusqueda;
	}

	public void setListaDeResultadosBusqueda(List<ResultadoBusqueda> listaDeResultadosBusqueda) {
		this.listaDeResultadosBusqueda = listaDeResultadosBusqueda;
	}	
	
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	//Busqueda por texto libre
	
	public List<POI> buscarPorTextoLibre(String unTag){
		return this.getColeccionDePOIS().stream()
				.filter(poi -> poi.detectarTagBuscado(unTag))
				.collect(Collectors.toList());
	}
	
	//ABM POIs
	
	//ver como hacer para dejar de suponer que el POI ingresado es siempre valido
	
	public void agregarPOI(POI unPOI){
		coleccionDePOIS.add(unPOI);
	}
	
	public void quitarPOI(POI unPOI){
		coleccionDePOIS.remove(unPOI);
	}
	
	// Agregar y quitar adapters consulta
	public void agregarAdapter(AdapterConsulta unAdapter){
		adapters.add(unAdapter);
	}
	
	public void quitarAdapter(AdapterConsulta unAdapter){
		adapters.remove(unAdapter);
	}
	
	
	//Consultar Busqueda POIs
	
	public List<POI> consultarPOIs(String unaConsulta){ 
		List<POI> poisEncontrados =	this.buscarEnTodosLosOrigenesDeDatos(unaConsulta); 
		return poisEncontrados;			
	}
		
	public List<POI> buscarEnTodosLosOrigenesDeDatos(String unaConsulta){
		List<POI> listaDePOIsACompletar = new ArrayList<POI>();
		adapters.forEach(adapter -> listaDePOIsACompletar.addAll(adapter.realizarConsulta(unaConsulta)));
		listaDePOIsACompletar.addAll(this.buscarPorTextoLibre(unaConsulta));
		return listaDePOIsACompletar;
	}
		
	//Consultar Busqueda POIs con TiempoMax
	
	public List<POI> consultarPOIsXTiempo(String unaConsulta, double tiempoMax){ // no se me ocurre otra forma
		double tInicio = System.currentTimeMillis(), tFin, tiempo;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = this.consultarPOIs(unaConsulta); // agrega todos los pois encontrados a la coleccion poisEncontrados
		tFin = System.currentTimeMillis();
		tiempo = (tFin - tInicio) / 1000;
		if (tiempo > tiempoMax) this.notificarXMailAAdministrador(); 
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,tiempo);
		this.almacenarResultadoBusqueda(resultadoBusqueda);
		this.agregarCantidadDeBusquedasPorFecha(resultadoBusqueda);
		return poisEncontrados;			
	}
	
	public void agregarCantidadDeBusquedasPorFecha(ResultadoBusqueda unResultadoBusqueda){
		LocalDate fechaBusqueda = unResultadoBusqueda.getMomentoDeBusqueda().toLocalDate();
		int cantidadAnterior = cantidadBusquedasXFecha.get(fechaBusqueda);
		cantidadBusquedasXFecha.put(fechaBusqueda,cantidadAnterior + 1); // hay que inicializar el hashMap en 0
	}
	
	public void notificarXMailAAdministrador(){}// como enviar mail? Y a quien?
	
	public void almacenarResultadoBusqueda(ResultadoBusqueda unResultadoBusqueda){
		//PARA MI (FEDE) AHORA SI DEBERIAMOS HACER UNA CLASE TERMINAL, PERO BUEN AHORA HAGO COMO SI NO TUVIERAMOS QUE
		listaDeResultadosBusqueda.add(unResultadoBusqueda);
		
	}
	
	public void obtenerInformeCantidadBusquedasXFecha(){
		Iterator<LocalDate> it = cantidadBusquedasXFecha.keySet().iterator();
		while(it.hasNext()){
			LocalDate key = it.next();
			System.out.println("Fecha: " + key + " -> Cantidad de busquedas: " + cantidadBusquedasXFecha.get(key));
		}
		
	}
																	
	public void cantidadDeBusquedasXTerminal(){/* HAY QUE HACER UNA TERMINAL :o */}
}