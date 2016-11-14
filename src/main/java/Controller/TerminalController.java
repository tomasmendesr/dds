package Controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.POI;
import Model.Terminal;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class TerminalController implements WithGlobalEntityManager, TransactionalOps{

	public ModelAndView home(Request req, Response res){
		Map<String, Terminal> model = new HashMap<>();
		String id = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(id));
		model.put("terminal", terminal);
		return new ModelAndView(model, "terminal/home.hbs");
	}
	
	public ModelAndView buscar(Request req, Response res){
		String id = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(id));
		String palabraBuscada = req.queryParams("fraseBuscada");
		String comuna = req.queryParams("comunaBuscada");
		String tipo = req.queryParams("tipoBuscado");
		String nombre = req.queryParams("nombreBuscado");
		List<POI> pois = RepositorioPOIs.getInstance().buscarPOI(palabraBuscada, comuna, tipo, nombre);
		Map<Terminal, List<POI>> model = new HashMap<>();		
		model.put(terminal, pois);
		return new ModelAndView(model, "terminal/resultadosBusqueda.hbs");
	}
}
