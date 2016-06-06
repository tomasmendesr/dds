package Master;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import Adapters.AdapterConsulta;

public class RepositorioPOIs {

	
	//CONSTRUCTOR
	
	public RepositorioPOIs(){
		this.instanciarNuevaColeccionDePOIs(); //Inicializa ArrayList de POIS
		adapters = new ArrayList<AdapterConsulta>();
		coleccionDeResultados = new ArrayList<Resultado>();
		coleccionDeCantidadPorFecha = new ArrayList<CantidadPorFecha>();
	}
	
	//ATRIBUTOS
	
	private List<POI> coleccionDePOIS;
	private List<AdapterConsulta> adapters;
	private List<Resultado> coleccionDeResultados;
	private List<CantidadPorFecha> coleccionDeCantidadPorFecha;

	//GETTERS Y SETTERS
	
	public void instanciarNuevaColeccionDePOIs(){
		coleccionDePOIS = new ArrayList<POI>();
	}
		
	public List<POI> getColeccionDePOIS(){
		return coleccionDePOIS;
	}
	
	public List<Resultado> getColeccionDeResultados(){
		return coleccionDeResultados;
	}

	public List<CantidadPorFecha> getColeccionDeResultadosPorFecha(){
		return coleccionDeCantidadPorFecha;
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
	
	// Lista de Resultados
	
	public void agregarResultado(Resultado unResultado){
		coleccionDeResultados.add(unResultado);
	}
	
	public void agregarCantidadPorFecha(CantidadPorFecha unRegistro){
		coleccionDeCantidadPorFecha.add(unRegistro);
	}
	
	//Consultar Busqueda POIs
	
	public List<POI> consultarPOIs(String unaConsulta){ // no se me ocurre otra forma
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
	
	public List<POI> consultarPOIs(String unaConsulta, double tiempoMax){ // no se me ocurre otra forma
		double tInicio, tFin, tiempo;
		Date date = new Date();
		tInicio = System.currentTimeMillis();
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = this.buscarEnTodosLosOrigenesDeDatos(unaConsulta); // agrega todos los pois encontrados a la coleccion poisEncontrados
		tFin = System.currentTimeMillis();
		tiempo = (tFin - tInicio) / 1000;
		if (tiempo > tiempoMax){
			System.out.println("Mail enviado"); // como enviar mail? Y a quien?
		}
		int cantidadPoisEncontrados = poisEncontrados.size();
		this.almacenarResultado(unaConsulta, cantidadPoisEncontrados, tiempo); 
		this.almacenarCantidadPorFecha(date,cantidadPoisEncontrados);
		return poisEncontrados;			
	}
	
	// Almacenar Resultado
	
	public void almacenarResultado(String consulta, int cantidadPoisEncontrados, double duracion){
		Resultado resultado = new Resultado(consulta,cantidadPoisEncontrados, duracion);
		this.agregarResultado(resultado);
	}
	
	// Almacenar CantidadPorFecha
	
	public void almacenarCantidadPorFecha(Date date, int cantidadResultados){
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = dateFormat.format(date);
//  Falta recorrer la coleccion para ir agregando las cantidades
//	si la fecha todavia no esta en la lista
//	CantidadPorFecha registro = new CantidadPorFecha(fecha, cantidadResultados);
//	this.agregarCantidadPorFecha(registro);
	}
	
}