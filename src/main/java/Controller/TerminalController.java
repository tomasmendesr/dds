package Controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.GestorConsultas;
import Model.POI;
import Model.Terminal;
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
		String idTerminal = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(idTerminal));
		String fraseBuscada = req.queryParams("fraseBuscada");
		GestorConsultas gestorConsultas = new GestorConsultas();
		Map<String, List<POI>> model = new HashMap<>();		
		List<POI> pois = gestorConsultas.consultarPOIsXTiempoEstimado(fraseBuscada, 160, terminal);
		model.put("terminal", pois);
		return new ModelAndView(model, "terminal/resultadosBusqueda.hbs");
	}
}
