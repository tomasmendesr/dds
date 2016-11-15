package Controller;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView listar(Request req, Response res){
		return new ModelAndView(null, "/admin/consultas/consultas.hbs");
	}
	
}
