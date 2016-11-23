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

import java.text.ParseException;
import java.util.*;

public class BusquedaController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView listarSegunCriterio(Request req, Response res) throws ParseException{
		Map<String,List<ResultadoBusqueda>> model = new HashMap<>();
		List<ResultadoBusqueda> busquedas = RepositorioBusquedas.getInstance().listar();
		String deshacer = req.queryParams("deshacer");
		String fechaInicial = req.queryParams("fechaDesde");
		String fechaFinal = req.queryParams("fechaHasta");
		String cantidadDeResultados = req.queryParams("cantidadDeResultados");
		String nombreTerminal = req.queryParams("terminal");
		Terminal terminal = null;
		if (deshacer == null){
			if(nombreTerminal != null && !nombreTerminal.equals("")){
				List<Terminal> terminalesEncontradas = new ArrayList<>();
				terminalesEncontradas = RepositorioTerminales.getInstance().buscarPorNombre(nombreTerminal);
				if(terminalesEncontradas.size() != 0){
					terminal = terminalesEncontradas.get(0);
					busquedas.retainAll(RepositorioBusquedas.getInstance().buscarPorTerminal(terminal));
				}
			}
			if(fechaInicial != null && fechaFinal != null && !fechaInicial.equals("") && !fechaFinal.equals(""))
				busquedas.retainAll(RepositorioBusquedas.getInstance().buscarPorFechas(fechaInicial, fechaFinal));
			if(cantidadDeResultados != null && !cantidadDeResultados.equals(""))
				busquedas.retainAll(RepositorioBusquedas.getInstance().buscarPorCantidadDeResultados(Integer.parseInt(cantidadDeResultados)));
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
