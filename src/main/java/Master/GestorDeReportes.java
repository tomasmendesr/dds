package Master;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorDeReportes {
	
	//ATRIBUTOS
	private HashMap<LocalDate,Integer>		 		cantidadBusquedasXFecha;
	private HashMap<Terminal,Integer>				resultadosParcialesPorTerminal;
	private HashMap<Terminal,Integer>				resultadosPorUsuario;
	private List<ResultadoBusqueda>					listaDeResultados;
	
	//CONSTRUCTOR
	public GestorDeReportes(){
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
		resultadosParcialesPorTerminal = new HashMap<Terminal,Integer>();
		resultadosPorUsuario = new HashMap<Terminal,Integer>();
		listaDeResultados = new ArrayList<ResultadoBusqueda>();
	}
	
	//Getters y setters
	public List<ResultadoBusqueda> getListaDeResultados() {
		return listaDeResultados;
	}

	public void setListaDeResultados(List<ResultadoBusqueda> listaDeResultados) {
		this.listaDeResultados = listaDeResultados;
	}

	
	//METODOS
	// Almacenar un resultado de busqueda
	public void almacenarResultado(ResultadoBusqueda unResultadoBusqueda){
		this.getListaDeResultados().add(unResultadoBusqueda);
	}
	
	
	// Reporte de busquedas por Fecha
	public void contabilizarBusquedaXFecha(ResultadoBusqueda unResultadoBusqueda){
		LocalDate fechaBusqueda = unResultadoBusqueda.getMomentoDeBusqueda().toLocalDate();
		if(cantidadBusquedasXFecha.get(fechaBusqueda) == null){ // Verifica si ya se hicieron busquedas en esa fecha
			cantidadBusquedasXFecha.put(fechaBusqueda, 0);
		}
		int cantidadAnterior = cantidadBusquedasXFecha.get(fechaBusqueda);
		cantidadBusquedasXFecha.put(fechaBusqueda,cantidadAnterior + 1);
	}
	
	// Reporte de Resultados Parciales por Terminal
	public void generarResultadosParciales(Terminal unaTerminal, Integer cantidadDeResultados){ 
		resultadosParcialesPorTerminal.put(unaTerminal, cantidadDeResultados);
	}
	
	// Reporte totales por usuario
	public void generarTotalesPorUsuario(Terminal unaTerminal){
		resultadosPorUsuario.put(unaTerminal, unaTerminal.obtenerResultadosTotales());
	
	}

}
