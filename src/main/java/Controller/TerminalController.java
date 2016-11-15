package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Model.Terminal;
import ObserversTerminal.AccionesTerminal;
import ObserversTerminal.AlmacenarBusqueda;
import ObserversTerminal.NotificarAdministrador;
import ObserversTerminal.ReporteParcial;
import ObserversTerminal.ReportePorFecha;
import ObserversTerminal.ReporteTotalesPorUsuario;
import POIsExt.Comuna;
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
	
	public Exception crear(Request req, Response res){
		Terminal nuevaTerminal = new Terminal();
		String nombre = req.queryParams("nombre");
		String comunaNumero = req.queryParams("comuna");
		nuevaTerminal.setNombre(nombre);
		try{
			Comuna comuna = new Comuna(Integer.parseInt(comunaNumero));
			nuevaTerminal.setComuna(comuna);
		}catch(Exception e){
			return e;
		}
		List<AccionesTerminal> acciones = asignarAcciones(req);
		nuevaTerminal.setObservers(acciones);
		RepositorioTerminales.getInstance().agregar(nuevaTerminal);
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
		String nombre = req.queryParams("nombreNuevo");
		String comunaNumero = req.queryParams("comunaNueva");
		List<AccionesTerminal> acciones = asignarAcciones(req);
		if(nombre != null) terminal.setNombre(nombre);
		if(comunaNumero != null){
			try{
				Comuna comuna = new Comuna(Integer.parseInt(comunaNumero));
				terminal.setComuna(comuna);
			}catch(Exception e){
				return e;
			}
		}
		terminal.setObservers(acciones);
		res.redirect("/admin/terminales");  
		return null;
	}
	
	public List<AccionesTerminal> asignarAcciones(Request req){
		String almacenarBusquedas = req.queryParams("almacenarBusquedas");
		String notificarAdmin = req.queryParams("notificarAdmin");
		String reporteParcial = req.queryParams("reporteParcial");
		String reportePorFecha = req.queryParams("reportePorFecha");
		String reporteTotalesPorUsuario = req.queryParams("reporteTotalesPorUsuario");
		List<AccionesTerminal> acciones = new ArrayList<>();
		if(almacenarBusquedas != null) acciones.add(new AlmacenarBusqueda());
		if(notificarAdmin != null) acciones.add(new NotificarAdministrador());
		if(reporteParcial != null) acciones.add(new ReporteParcial());
		if(reportePorFecha != null) acciones.add(new ReportePorFecha());
		if(reporteTotalesPorUsuario != null) acciones.add(new ReporteTotalesPorUsuario());
		return acciones;
	}
	
	public Void eliminar(Request req, Response res){
		String id = req.params("id");
		RepositorioTerminales.getInstance().eliminarTerminal(Long.parseLong(id));
		res.redirect("/admin/terminales");
		return null;
	}
}
