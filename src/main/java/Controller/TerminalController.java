package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.Terminal;
import Repos.RepositorioTerminales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class TerminalController implements WithGlobalEntityManager, TransactionalOps{

	public ModelAndView home(Request req, Response res){
		Map<String, Terminal> model = new HashMap<>();
		String id = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(id));
		model.put("terminal", terminal);
		return new ModelAndView(model, "terminal/home.hbs");
	}
	
	public ModelAndView listar(Request req, Response res){
		Map<String, List<Terminal>> model = new HashMap<>();		
		List<Terminal> terminales = new ArrayList<>();
		terminales.addAll(RepositorioTerminales.getInstance().listar());
		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/terminal/terminales.hbs");
	}
	
	public ModelAndView crearView(Request req, Response res){
		return new ModelAndView(null,null);
	}
	
	public ModelAndView modifView(Request req, Response res){
		return new ModelAndView(null,null);
	}
	
	public Void eliminar(Request req, Response res){
		return null;
	}
}
