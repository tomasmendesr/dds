package ObserversTerminal;

import java.util.HashMap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@DiscriminatorValue("3")
public class ReporteParcial extends AccionesTerminal {
	
	//Atributos
	private HashMap<Terminal,Integer> 		resultadosParcialesPorTerminal;
	
	//Constructor
	public ReporteParcial(){
		resultadosParcialesPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
        Integer cantidadDeResultados;
        cantidadDeResultados = unResultadoBusqueda.cantidadDeResultados();
        this.generarResultadosParciales(unaTerminal, cantidadDeResultados); // Guardo los datos para hacer el repote
    }

	// Reporte de Resultados Parciales por Terminal
	public void generarResultadosParciales(Terminal unaTerminal, Integer cantidadDeResultados){
		resultadosParcialesPorTerminal.put(unaTerminal, cantidadDeResultados);
	}

	
	public Integer generarReporteParcialPorTerminal(Terminal terminal){ // Genero el reporte por terminal cuando me lo pidan
		return resultadosParcialesPorTerminal.get(terminal);
	}
}
