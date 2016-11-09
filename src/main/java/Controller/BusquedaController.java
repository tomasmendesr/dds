package Controller;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.POI;
import Repos.RepositorioPOIs;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps{
	
	public ModelAndView mostrar(Request req, Response res){
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");
		
		POI	poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		model.put("terminal", poi);
		return new ModelAndView(model, "terminal/showPoi.hbs");
	}

}
