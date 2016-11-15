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
		return new ModelAndView(null,"admin/terminal/crear.hbs");
	}
	
	public Void crear(Request req, Response res){
		String nombre = req.queryParams("nombre");
		String comuna = req.queryParams("comuna");
		Terminal nuevaTerminal = new Terminal();
		nuevaTerminal.setNombre(nombre);
		
		// faltan acciones y comuna
		res.redirect("/admin/terminales");
		return null;
	}
	
	public ModelAndView modifView(Request req, Response res){
		Map<String, Terminal> model = new HashMap<>();
		String id = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(id));
		model.put("terminal", terminal);
		return new ModelAndView(model, "admin/terminal/modificar.hbs");
	}
	
	public Exception modificar(Request req, Response res){
		String id = req.params("id");
		Terminal terminal = RepositorioTerminales.getInstance().buscar(Long.parseLong(id));
		if(req.queryParams("nombreNuevo") != null && !req.queryParams("nombreNuevo").equals(""))
				terminal.setNombre(req.queryParams("nombreNuevo"));
		res.redirect("/admin/terminales");  
		return null;
	}
	
	public Void eliminar(Request req, Response res){
		String id = req.params("id");
		RepositorioTerminales.getInstance().eliminarTerminal(Long.parseLong(id));
		res.redirect("/admin/terminales");
		return null;
	}
}
