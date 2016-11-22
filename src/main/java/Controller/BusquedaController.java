package Controller;

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

	public ModelAndView listarSegunCriterio(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> busquedas = new ArrayList<>();
		String deshacer = req.queryParams("deshacer");
		if (deshacer != null){
			busquedas = RepositorioBusquedas.getInstance().listar();
		}
		else{ 
			String fechaInicial = req.params("fechaDesde");
			String fechaFinal = req.params("fechaHasta");
			String cantidadDeResultados = req.params("cantidadDeResultados");
			String nombreTerminal = req.params("teminal");
			Terminal terminal = RepositorioTerminales.getInstance().buscarPorNombre(nombreTerminal).get(0);
			busquedas = RepositorioBusquedas.getInstance().
					getResultadosSegunCriterios(fechaInicial,fechaFinal,cantidadDeResultados,terminal);
		}
		model.put("resultadosBusquedas",busquedas);
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView listar(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultadosBusquedas = RepositorioBusquedas.getInstance().listar();
		model.put("resultadosBusquedas", resultadosBusquedas);
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView cargarDetalleBusqueda(Request req, Response res){
		Map<String,ResultadoBusqueda> model = new HashMap<>();
		String id = req.params(":id");
		ResultadoBusqueda resultadoBusqueda = RepositorioBusquedas.getInstance().buscar(Long.parseLong(id));
		model.put("busqueda",resultadoBusqueda);
		return new ModelAndView(model,"admin/consultas/detalleConsulta.hbs");
	}	
}
