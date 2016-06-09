package Master;

import java.util.ArrayList;
import java.util.List;

public class TerminalCentral {	//Clase que conoce los Terminales y genera los reportes de todos
	
	//CONSTRUCTOR
	public TerminalCentral(){
		terminales = new ArrayList<Terminal>();
	}
	//ATRIBUTOS
	private List<Terminal> terminales;
	
	//GETERS Y SETTERS
	
	public List<Terminal> getTerminales() {
		return terminales;
	}
	public void agregarTerminal(Terminal terminal) {
		this.terminales.add(terminal);
	}
	
	//METODOS
	public void generarReportePorTerminales(){
		System.out.println("Totales por Usuarios");
		terminales.forEach(terminal -> this.mostrarResultados(terminal));
	}
	public void mostrarResultados(Terminal terminal){
		System.out.println(terminal.getNombreTerminal());
		System.out.println(terminal.getResultadoTotal().toString());
	}

}
