package Master;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ObserversTerminal.FuncionalidadExtraTerminal;

public class Terminal {
	
	//ATRIBUTOS
	private RepositorioPOIs			 				repositorioPOIs;
	private HashMap<LocalDate,Integer>		 		cantidadBusquedasXFecha;
	private List<FuncionalidadExtraTerminal>		observers;
	private String									nombreTerminal;
		
	//CONSTRUCTOR
	public Terminal(String nombre, RepositorioPOIs unRepositorioDePOIs){
		this.setRepositorioPOIs(unRepositorioDePOIs);
		this.setNombreTerminal(nombre);
		observers = new ArrayList<FuncionalidadExtraTerminal>();
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
	}
	
	//GETERS Y SETERS
	public String getNombreTerminal(){
		return nombreTerminal;
	}
	
	public void setNombreTerminal(String nombre){
		nombreTerminal = nombre;
	}
	
	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}
	
	public List<FuncionalidadExtraTerminal> getObservers() {
		return observers;
	}

	public void addObserver(FuncionalidadExtraTerminal observer){
		observers.add(observer);
	}
	
	public void setObservers(List<FuncionalidadExtraTerminal> observers) {
		this.observers = observers;
	}
	
	//METODOS
	
	//Consultar Busqueda POIs con TiempoMax

	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		//this.contabilizarBusquedaXFecha(resultadoBusqueda);
		observers.forEach(observer -> observer.realizarAccion(this, resultadoBusqueda));
		return poisEncontrados;
	}
		
	public void contabilizarBusquedaXFecha(ResultadoBusqueda unResultadoBusqueda){
		LocalDate fechaBusqueda = unResultadoBusqueda.getMomentoDeBusqueda().toLocalDate();
		if(cantidadBusquedasXFecha.get(fechaBusqueda) == null){
			cantidadBusquedasXFecha.put(fechaBusqueda, 0);
		}
		int cantidadAnterior = cantidadBusquedasXFecha.get(fechaBusqueda);
		cantidadBusquedasXFecha.put(fechaBusqueda,cantidadAnterior + 1);
	}
		/*		
	public List<Integer> getResultadosParciales(){ //cuantos resultados hubo en cada busqueda
		return listaDeResultadosBusqueda.stream().
				map(resultadoBusqueda -> resultadoBusqueda.cantidadDeResultados()).
				collect(Collectors.toList());
	}
	
	public Integer getResultadoTotal(){	//suma de todos los resultados parciales
		return this.getResultadosParciales().stream().reduce(0,(a,b)-> a + b);
	}*/

	public void obtenerInformeCantidadBusquedasXFecha(){
		Iterator<LocalDate> it = cantidadBusquedasXFecha.keySet().iterator();
		while(it.hasNext()){
			LocalDate key = it.next();
			System.out.println("Fecha: " + key + " -> Cantidad de busquedas: " + cantidadBusquedasXFecha.get(key));
		}
	}
	
	/*public void generarReporteResultadosParciales(){
		List<Integer> listaDeResultadosParciales = this.getResultadosParciales();
		System.out.println("Parciales por Terminal");
		System.out.println("Usuario: ".concat(this.getNombreTerminal()));
		System.out.println("Cantidad de resultados parciales");
		listaDeResultadosParciales.forEach(num -> System.out.println(num.toString()));
	}	*/
}
