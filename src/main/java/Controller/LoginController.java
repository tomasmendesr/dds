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

public class LoginController {
	public static ModelAndView home(Request req, Response res){
		return new ModelAndView(null, "login/login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res){
		String user = req.queryParams("nombre");
		Terminal terminal = RepositorioTerminales.getInstance().buscarPorNombre(user).get(0);
		if(terminal != null){
			if(terminal.getNombre().equals(req.queryParams("password"))){
				Map<String, List<POI>> model = new HashMap<>();
				List<POI> pois = RepositorioPOIs.getInstance().listar();
				model.put("terminal", pois);
				return new ModelAndView(model, "terminal/index.hbs");
			}
		}
		else if(req.queryParams("nombre").equals("admin") && req.queryParams("password").equals("admin")){
			return new ModelAndView(null, "admin/index.hbs");
		}
		return new ModelAndView(null, "login/home.hbs");
	}
	
	

}
