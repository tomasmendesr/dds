package Master;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTerminales {

	//Atributos
	private static RepositorioTerminales repositorioTerminales;
	private List<Terminal> terminales;
	
	//Constructor
		private RepositorioTerminales() {
			setTerminales(new ArrayList<Terminal>());
		}
			
	//Singleton 
	public static RepositorioTerminales getInstance() {
		if (repositorioTerminales == null) repositorioTerminales = new RepositorioTerminales();
			return repositorioTerminales;
		}
	
	public static void resetTerminales() {
		repositorioTerminales = null;
	}
	
	//Getters y setters
	public List<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}
	
	// Metodos
	public void addTerminal(Terminal terminal){
		terminales.add(terminal);
	}
	
	
	
}
