package Master;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class RepositorioTerminales {

	//Atributos
	private static RepositorioTerminales repositorioTerminales;
	private List<ResultadoBusqueda> resultadosBusquedas;
	
	@OneToMany
	@JoinColumn(name = "TERMINAL_ID")
	private List<Terminal> terminales;
	
	//Constructor
		private RepositorioTerminales() {
			setTerminales(new ArrayList<Terminal>());
			setResultadosBusquedas(new ArrayList<ResultadoBusqueda>());
		}
			
	//Singleton 
	public static RepositorioTerminales getInstance() {
		if (repositorioTerminales == null) repositorioTerminales = new RepositorioTerminales();
			return repositorioTerminales;
		}
	
	public static void resetTerminales() {
		repositorioTerminales = null;
	}
	
	
	// Metodos
	public void addTerminal(Terminal terminal){
		terminales.add(terminal);
	}
	
	public void addResultadoBusqueda(ResultadoBusqueda resultadoBusqueda){
		resultadosBusquedas.add(resultadoBusqueda);
	}
	
	//Getters y setters
		public List<Terminal> getTerminales() {
			return terminales;
		}

		public void setTerminales(List<Terminal> terminales) {
			this.terminales = terminales;
		}

		public List<ResultadoBusqueda> getResultadosBusquedas() {
			return resultadosBusquedas;
		}

		public void setResultadosBusquedas(List<ResultadoBusqueda> resultadosBusquedas) {
			this.resultadosBusquedas = resultadosBusquedas;
		}
	
	
	
}
