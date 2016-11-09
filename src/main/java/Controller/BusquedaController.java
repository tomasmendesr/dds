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

	public ModelAndView listarPois(Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = RepositorioPOIs.getInstance().listar();
		
		model.put("terminal", pois);
		return new ModelAndView(model, "terminal/index.hbs");
	}
	
	public ModelAndView mostrar(Request req, Response res){
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");
		
		POI	poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		model.put("terminal", poi);
		return new ModelAndView(model, "terminal/showPOI.hbs");
	}
	
	public ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null, "poi/new.hbs");
	}
}
