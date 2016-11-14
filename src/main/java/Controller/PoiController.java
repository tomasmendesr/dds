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
		List<POI> pois = new ArrayList<>();
		String nombre = req.queryParams("nombreBuscado");
		String tipo = req.queryParams("tipoBuscado");
		if(nombre != null && !nombre.equals(""))
			pois.addAll(RepositorioPOIs.getInstance().buscarPorNombre(nombre));
		else if(tipo != null && !tipo.equals(""))
			pois.addAll(RepositorioPOIs.getInstance().buscarPOIsPorTipo(tipo));
		if((nombre == null || nombre.equals("")) && (tipo == null || tipo.equals(""))) 
			pois.addAll(RepositorioPOIs.getInstance().listar());
		model.put("pois", pois);
		return new ModelAndView(model, "admin/poi/pois.hbs");
	}
		
	public ModelAndView modificarView(Request req, Response res){
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");
		POI poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		model.put("poi", poi);
		return new ModelAndView(model, "admin/poi/modifPoi.hbs");
	}
	
	public Exception modificar(Request req, Response res){
		String id = req.params("id");
		POI poi = RepositorioPOIs.getInstance().buscar(Long.parseLong(id));
		if(req.queryParams("nombre") != null && !req.queryParams("nombre").equals(""))
				poi.setNombre(req.queryParams("nombre"));
		if(req.queryParams("direccion") != null && !req.queryParams("direccion").equals(""))
				poi.setDireccion(req.queryParams("direccion"));
		if(req.queryParams("coordenadaX") != null && req.queryParams("coordenadaY") != null && !req.queryParams("coordenadaX").equals("") && !req.queryParams("coordenadaY").equals(""))
			try{ // mas le vale que me de numeritos
				poi.setCoordenadas(Double.parseDouble(req.queryParams("coordenadaX")), Double.parseDouble(req.queryParams("coordenadaY")));
			}catch(Exception e){
				return e;
			}
		res.redirect("/admin/pois");  
		return null;
	}
	
	public Void eliminarPoi(Request req, Response res){
		String id = req.params("id");
		RepositorioPOIs.getInstance().eliminarPOI(Long.parseLong(id));
		res.redirect("/admin/pois");
		return null;
	}
}
