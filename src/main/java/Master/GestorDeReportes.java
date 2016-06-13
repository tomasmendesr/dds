package Master;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GestorDeReportes {
	//ATRIBUTOS
	private HashMap<LocalDate,Integer>		 		cantidadBusquedasXFecha;
	
	//CONSTRUCTOR
	public GestorDeReportes(){
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
	}
	
	//Getters y setters
	private List<ResultadoBusqueda> listaDeResultados;


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
	
	
}
