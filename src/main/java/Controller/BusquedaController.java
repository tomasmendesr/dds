package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.POI;
import Repos.RepositorioPOIs;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps{

	public ModelAndView listar(Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> terminal = RepositorioPOIs.getInstance().listar();
		
		model.put("terminal", terminal);
		return new ModelAndView(model, "terminal/index.hbs");
	}
}
