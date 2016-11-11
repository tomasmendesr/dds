package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.POI;
import Repos.RepositorioPOIs;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoiController {
	public ModelAndView listar(Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();		
		List<POI> pois = RepositorioPOIs.getInstance().listar();
		model.put("pois", pois);
		return new ModelAndView(model, "admin/poi/pois.hbs");
	}
	
	public static ModelAndView buscar(Request req, Response res){
		 String nombre = req.queryParams("nombreBuscado");
//		 String tipo = null;
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> poisEncontrados = new ArrayList<>();
		if(nombre != null) {
			nombre = req.queryParams("nombreBuscado");
			poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorNombre(nombre));
		}
		/*if(req.queryParams("tipo") != null){
		   tipo = req.queryParams("tipo");
		   poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorTipo(tipo));
		}*/
		model.put("admin", poisEncontrados);
		return new ModelAndView(model, "/admin/poi/pois.hbs");
	}
	
	public ModelAndView modificarView(Request req, Response res){
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");
		POI poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		model.put("poi", poi);
		return new ModelAndView(model, "admin/poi/modifPoi.hbs");
	}
	
	public Void modificar(Request req, Response res){
		res.redirect("admin/pois");
		return null;
	}
	
	public ModelAndView eliminarPoi (Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();
		//String "12" = req.params(":id");
		Long poi = new Long("1");
		POI poiEncontrado = RepositorioPOIs.getInstance().buscar(poi);
		RepositorioPOIs.getInstance().eliminarPOI(poiEncontrado.getID());
		model.put("admin", RepositorioPOIs.getInstance().listar());
		return new ModelAndView(model, "admin/poi/pois.hbs");
	}
}
