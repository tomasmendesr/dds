package ObserversTerminal;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;


import Model.ResultadoBusqueda;
import Model.Terminal;

@Entity
@DiscriminatorValue("5")
public class ReporteTotalesPorUsuario extends AccionesTerminal {
	
	//Atributos
	@ElementCollection
	@CollectionTable(name = "resultadosPorTerminal", joinColumns = @JoinColumn(name = "accion_id"))
	@MapKeyJoinColumn(name = "terminal_id")
	@Column(name = "cantidad")
	private Map<Terminal,Integer> 		resultadosPorTerminal;
	
	//Constructor
	public ReporteTotalesPorUsuario(){
		resultadosPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	//Metodos
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {		
		this.generarTotalesPorUsuario(unaTerminal); // Guardo los datos
	}

	// Reporte totales por usuario
	public void generarTotalesPorUsuario(Terminal unaTerminal){
		resultadosPorTerminal.put(unaTerminal, unaTerminal.obtenerResultadosTotales());
	}
	
	public Integer generarReporteTotalPorTerminal(Terminal terminal){ // Devuelve cant resultados de la terminal
		return resultadosPorTerminal.get(terminal);
	}
}
