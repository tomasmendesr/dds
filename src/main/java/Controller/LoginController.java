package Controller;

import java.util.List;

import Model.Terminal;

import Repos.RepositorioTerminales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	public static ModelAndView home(Request req, Response res){
		return new ModelAndView(null, "login/login.hbs");
	}
	
	public Void login(Request req, Response res){
		String user = req.queryParams("nombre");
		List<Terminal> terminales = RepositorioTerminales.getInstance().buscarPorNombre(user);
		if(terminales.size() != 0){
			Terminal terminal = terminales.get(0);
			if(terminal.getNombre().equals(req.queryParams("password"))){
				res.redirect("/terminal/"+terminal.getId().toString());
			}
		}
		else if(req.queryParams("nombre").equals("admin") && req.queryParams("password").equals("admin")){
			res.redirect("/admin");
		}
		else	
			res.redirect("/login");
			return null;
	}
	
	

}
