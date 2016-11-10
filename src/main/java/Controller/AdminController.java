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
		Map<String, List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultados = RepositorioBusquedas.getInstance().getAllBusquedas();
		model.put("admin", resultados);
		return new ModelAndView(model, "admin/busquedas.hbs");
	}
	
	public ModelAndView modifPoi(Request req, Response res){
		String id = req.queryParams("id");
		POI poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		return new ModelAndView(poi, "admin/modifPoi.hbs"); // no se si esta bien
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
		 String nombre = null, tipo = null;
		List<POI> poisEncontrados = new ArrayList<>();
		if(req.queryParams("nombre") != null) {
			nombre = req.queryParams("nombre");
			poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorNombre(nombre));
		}
		/*if(req.queryParams("tipo") != null){
		   tipo = req.queryParams("tipo");
		   poisEncontrados.addAll(RepositorioPOIs.getInstance().buscarPorTipo(tipo));
		}*/
		Map<String, List<POI>> model = new HashMap<>();
		model.put("admin", poisEncontrados);
		System.out.println(poisEncontrados.get(0).getNombre());
		return new ModelAndView(model, "admin/pois.hbs");
	}
}
