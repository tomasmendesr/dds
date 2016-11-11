package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.POI;
import Model.PoisDeBusqueda;
import Model.ResultadoBusqueda;
import Model.Terminal;
import Repos.RepositorioBusquedas;
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
		String nombre = req.queryParams("nombreBuscado");
		Map<String, List<POI>> model = new HashMap<>();		
		List<POI> pois = new ArrayList<>();
		if(nombre != null){
			pois = RepositorioPOIs.getInstance().buscarPorNombre(nombre);
		}
		else {
			 pois = RepositorioPOIs.getInstance().listar();
		}
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
		Map<String, List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultados = RepositorioBusquedas.getInstance().getAllBusquedas();
		model.put("admin", resultados);
		return new ModelAndView(model, "admin/busquedas.hbs");
	}
	
	public ModelAndView modifPoi(Request req, Response res){
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");
		POI poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		model.put("poi", poi);
		return new ModelAndView(model, "admin/modifPoi.hbs");
	}
	
	
	public ModelAndView listarPoisBusquedas(Request req, Response res){
		Map<String, List<PoisDeBusqueda>> model = new HashMap<>();
		String id = req.params("id");
		ResultadoBusqueda resultBusqueda = RepositorioBusquedas.getInstance().buscar(Long.parseLong(id));
		List<PoisDeBusqueda> resultados = resultBusqueda.getResultadoBusqueda();
		model.put("busqueda", resultados);
		return new ModelAndView(model, "admin/resultadosBusqueda.hbs");
	}
	
	public ModelAndView eliminarPoi (Request req, Response res){
		Map<String, List<POI>> model = new HashMap<>();
		//String "12" = req.params(":id");
		Long poi = new Long("1");
		POI poiEncontrado = RepositorioPOIs.getInstance().buscar(poi);
		RepositorioPOIs.getInstance().eliminarPOI(poiEncontrado.getID());
		model.put("admin", RepositorioPOIs.getInstance().listar());
		return new ModelAndView(model, "admin/pois.hbs");
	}
	
	public static ModelAndView buscarPois(Request req, Response res){
		 /*String nombre = req.queryParams("nombreBuscado");
		 String tipo = null;
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> poisEncontrados = new ArrayList<>();
		if(nombre != null) {
			nombre = req.queryParams("nombreBuscado");
			poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorNombre(nombre));
		}
		/*if(req.queryParams("tipo") != null){
		   tipo = req.queryParams("tipo");
		   poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorTipo(tipo));
		}
		model.put("admin", poisEncontrados);*/
		return new ModelAndView(null, null);
	}
	
	public Void modificar(Request req, Response res){
		res.redirect("/admin/pois");
		return null;
	}
}
