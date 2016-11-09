package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.POI;
import Model.Terminal;
import Repos.RepositorioPOIs;
import Repos.RepositorioTerminales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	public ModelAndView menu(Request req, Response res){
		return new ModelAndView(null, "admin/index.hbs");
	}
	
	public ModelAndView listarPois(Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = RepositorioPOIs.getInstance().listar();
		
		model.put("admin", pois);
		return new ModelAndView(model, "admin/pois.hbs");
	}
	
	public ModelAndView listarTerminales(Request req, Response res){
		Map<String, List<Terminal>> model = new HashMap<>();
		List<Terminal> terminales = RepositorioTerminales.getInstance().listar();
		
		model.put("admin", terminales);
		return new ModelAndView(model, "admin/terminales.hbs");
	}
	
	public ModelAndView listarBusquedas(Request req, Response res){
		return null;
	}
	
	public ModelAndView modifPoi(Request req, Response res){
		return null;
	}
	
	public ModelAndView elimPoi(Request req, Response res){
		return null;
	}
}
