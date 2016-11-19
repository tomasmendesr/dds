package Controller;

import Model.PoisDeBusqueda;
import Model.ResultadoBusqueda;
import Repos.RepositorioBusquedas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView listarSegunCriterio(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		String fechaInicial = req.params("fechaDesde");
		String fechaFinal = req.params("fechaHasta");
		String cantidadDeResultados = req.params("cantidadDeResultados");
		String terminal = req.params("teminal");
		model.put("resultadoBusqueda",RepositorioBusquedas.getInstance().
				getResultadosSegunCriterios(fechaInicial,fechaFinal,cantidadDeResultados,terminal));
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView listar(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultadosBusquedas = RepositorioBusquedas.getInstance().listar();
		model.put("resultadosBusquedas", resultadosBusquedas);
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView cargarDetalleBusqueda(Request req, Response res){
		Map<String,List<PoisDeBusqueda>> model = new HashMap<>();
		Long id = Long.parseLong(req.params(":id").substring(1));
		ResultadoBusqueda resultadoBusqueda = RepositorioBusquedas.getInstance().buscar(id);
		List<PoisDeBusqueda> list = resultadoBusqueda.getResultadoBusqueda();
		model.put("poisEncontrados",list);
		return new ModelAndView(model,"admin/consultas/detalleConsulta.hbs");
	}

	/*
	public ModelAndView buscar(Request req, Response res){
		Map<String, List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultadoBusquedaList = new ArrayList<>();
		resultadoBusquedaList = RepositorioBusquedas.getInstance().getAllBusquedas();
		model.put()

		return new ModelAndView()
	}
	*/
	
}
