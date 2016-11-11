package Controller;




import java.util.HashMap;
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
	
}
