package ObserversTerminal;

import java.time.LocalDate;
import java.util.HashMap;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@DiscriminatorValue("4")
public class ReportePorFecha extends AccionesTerminal{

	//Atributos
	private HashMap<LocalDate,Integer>		cantidadBusquedasXFecha;

	//Constructor
	public ReportePorFecha(){
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
	}
	
	//Getters y Setters	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		this.contabilizarBusquedaXFecha(unResultadoBusqueda); // Guardo los datos para hacer el reporte
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

	public Integer generarReportePorFecha(LocalDate fecha){ // Genero el reporte cuando me lo pidan
		return cantidadBusquedasXFecha.get(fecha);
	}
}
