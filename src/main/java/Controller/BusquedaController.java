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
import java.util.stream.Stream;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView listarSegunCriterio(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> busquedas = new ArrayList<>();
		String deshacer = req.queryParams("deshacer");
		if (deshacer != null){
			busquedas = RepositorioBusquedas.getInstance().listar();
		}
		else{ 
			String fechaInicial = req.queryParams("fechaDesde");
			String fechaFinal = req.queryParams("fechaHasta");
			String cantidadDeResultados = req.queryParams("cantidadDeResultados");
			String nombreTerminal = req.queryParams("terminal");
			Terminal terminal = null;
			if(nombreTerminal != null && !nombreTerminal.equals(""))
				terminal = RepositorioTerminales.getInstance().buscarPorNombre(nombreTerminal).get(0);
			busquedas = RepositorioBusquedas.getInstance().
					getResultadosSegunCriterios(fechaInicial,fechaFinal,cantidadDeResultados,terminal);
		}
		model.put("resultadosBusquedas",busquedas);
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView listar(Request req, Response res){
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> resultadosBusquedas = RepositorioBusquedas.getInstance().listar();
		aplicarFiltros(req,resultadosBusquedas);
		model.put("resultadosBusquedas", resultadosBusquedas);
		return new ModelAndView(model,"admin/consultas/consultas.hbs");
	}

	public ModelAndView cargarDetalleBusqueda(Request req, Response res){
		Map<String,ResultadoBusqueda> model = new HashMap<>();
		String id = req.params(":id");
		ResultadoBusqueda resultadoBusqueda = RepositorioBusquedas.getInstance().buscar(Long.parseLong(id));
		model.put("busqueda",resultadoBusqueda);
		return new ModelAndView(model,"admin/consultas/detalleConsulta.hbs");4
	}

	private void aplicarFiltros(Request req, List<ResultadoBusqueda> list){
		Map<String,String> filtros = new HashMap<>();
		filtros.put("fechaInicial",req.queryParams("fechaDesde"));
		filtros.put("fechaFinal", req.queryParams("fechaHasta"));
		filtros.put("cantidadDeResultados",req.queryParams("cantidadDeResultados"));
		filtros.put("nombreTerminal",req.queryParams("terminal"));
		for (Map.Entry<String,String> filtro : filtros.entrySet()) {
			if(!filtro.getValue().equals("")) filtrar(filtro,list);
		}
	}

	private void filtrar(Map.Entry<String,String> filtro, List<ResultadoBusqueda> list){
		Stream<ResultadoBusqueda> streamList = list.stream();
		//if(filtro.getKey().equals("fechaInicial")) streamList.filter(elem -> elem.getMomentoDeBusqueda().after(Date.parse(filtro.getValue())));
	}
}
