package Master;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GestorDeReportes {
	
	//ATRIBUTOS
	private HashMap<LocalDate,Integer>		 		cantidadBusquedasXFecha;
	private HashMap<Terminal,Integer>				resultadosParcialesPorTerminal;
	private List<ResultadoBusqueda> listaDeResultados;
	
	//CONSTRUCTOR
	public GestorDeReportes(){
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
		resultadosParcialesPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	//Getters y setters
	public List<ResultadoBusqueda> getListaDeResultados() {
		return listaDeResultados;
	}

	public void setListaDeResultados(List<ResultadoBusqueda> listaDeResultados) {
		this.listaDeResultados = listaDeResultados;
	}
	
	
	//METODOS
	
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
	
}
