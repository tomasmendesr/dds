package Controller;

import Model.GestorConsultas;
import Model.POI;
import Model.ResultadoBusqueda;
import Model.Terminal;
import Repos.RepositorioBusquedas;
import Repos.RepositorioTerminales;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps {

	//public ModelAndView listar(Request req, Response res){ return new ModelAndView(null, "/admin/consultas/consultas.hbs"); }

	public ModelAndView listarSegunCriterio(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		String fechaInicial = req.params("fechaDesde");
		String fechaFinal = req.params("fechaHasta");
		String cantidadDePOIs = req.params("cantidadDePOIs");
		String terminal = req.params("teminal");
		model.put("resultadoBusqueda",RepositorioBusquedas.getInstance().
				getResultadosSegunCriterios(fechaInicial,fechaFinal,cantidadDePOIs,terminal));
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView listar(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		model.put("resultadoBusqueda",RepositorioBusquedas.getInstance().getAllBusquedas());
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}


	/*
	public ModelAndView buscar(Request req, Response res){
		Map<String, List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultadoBusquedaList = new ArrayList<>();
		resultadoBusquedaList = RepositorioBusquedas.getInstance().getAllBusquedas();
		model.put()

		return new ModelAndView()
	}*/
	
}
