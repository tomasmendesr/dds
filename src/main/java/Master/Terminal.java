package Master;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ObserversTerminal.ObserverTerminal;

public class Terminal {

	//CONSTRUCTOR
	
	public Terminal(RepositorioPOIs unRepositorioDePOIs){
		this.setRepositorioPOIs(unRepositorioDePOIs);
		listaDeResultadosBusqueda = new ArrayList<ResultadoBusqueda>();
	}
	
	//ATRIBUTOS
	
	private RepositorioPOIs repositorioPOIs;
	private HashMap<LocalDate,Integer> cantidadBusquedasXFecha;
	private List<ResultadoBusqueda> listaDeResultadosBusqueda;
	private ObserverTerminal		observers;

	//GETERS Y SETERS
	
	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
	}
	
	//METODOS
	
	//Consultar Busqueda POIs con TiempoMax
	
	public List<POI> consultarPOIsXTiempo(String unaConsulta, double tiempoMax){ // no se me ocurre otra forma
		double tInicio = System.currentTimeMillis(), tFin, tiempo;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta); // agrega todos los pois encontrados a la coleccion poisEncontrados
		tFin = System.currentTimeMillis();
		tiempo = (tFin - tInicio) / 1000;
		if (tiempo > tiempoMax) this.notificarXMailAAdministrador(); // como enviar mail? Y a quien?
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,tiempo);
		this.almacenarResultadoBusqueda(resultadoBusqueda);
		this.agregarCantidadDeBusquedasPorFecha(resultadoBusqueda);
		return poisEncontrados;			
	}
		
	public void agregarCantidadDeBusquedasPorFecha(ResultadoBusqueda unResultadoBusqueda){
		LocalDate fechaBusqueda = unResultadoBusqueda.getMomentoDeBusqueda().toLocalDate();
		int cantidadAnterior = cantidadBusquedasXFecha.get(fechaBusqueda);
		cantidadBusquedasXFecha.put(fechaBusqueda,cantidadAnterior + 1);
	}
	
	public void notificarXMailAAdministrador(){}
	
	public void almacenarResultadoBusqueda(ResultadoBusqueda unResultadoBusqueda){
		//PARA MI (FEDE) AHORA SI DEBERIAMOS HACER UNA CLASE TERMINAL, PERO BUEN AHORA HAGO COMO SI NO TUVIERAMOS QUE
		listaDeResultadosBusqueda.add(unResultadoBusqueda);
	}
		
																
	public void cantidadDeBusquedasXTerminal(){/* HAY QUE HACER UNA TERMINAL :o */}
	
	public void obtenerInformeCantidadBusquedasXFecha(){
		Iterator<LocalDate> it = cantidadBusquedasXFecha.keySet().iterator();
		while(it.hasNext()){
			LocalDate key = it.next();
			System.out.println("Fecha: " + key + " -> Cantidad de busquedas: " + cantidadBusquedasXFecha.get(key));
		}
	}
}
