package Server;


import Controller.LoginController;
import Controller.PoiController;
import Controller.TerminalController;
import Controller.AdminController;
import Controller.BusquedaController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();
		
	Spark.staticFiles.location("/public");
	
	LoginController loginController = new LoginController();
	TerminalController terminalController = new TerminalController();
	PoiController poiController = new PoiController();
	AdminController adminController = new AdminController();
	BusquedaController busquedasController = new BusquedaController();
	
	// Login 
	// Usuario: nombreTerminal Password: nombreTerminal
	// Usuario: admin		   Password: admin
	Spark.get("/", LoginController::home, engine);
	Spark.post("/login", loginController::login);
	
	// Busqueda de pois por usuario
	Spark.get("/terminal/:id", terminalController::home, engine);
	Spark.get("/terminal/:id/buscarCercanos", poiController::buscarCeranos, engine);
	Spark.get("/poi/:id", poiController::mostrar, engine);
	
	// Admin
	Spark.get("/admin", adminController::menu, engine);
	
	// Administracion de pois
	Spark.get("/admin/pois", poiController::listar, engine);
	Spark.post("/admin/pois", poiController::listar, engine);
	Spark.get("/admin/pois/modificar/poi/:id", poiController::modificarView, engine);
	Spark.post("admin/pois/poi/:id", poiController::modificar);
	Spark.get("/admin/pois/eliminar/poi/:id", poiController::eliminar);
	
	// Administracion de terminales
	Spark.get("/admin/terminales", terminalController::listar, engine);
	Spark.post("admin/terminales", terminalController::listar, engine);
	Spark.get("/admin/terminales/nueva", terminalController::crearView, engine);
	Spark.post("/admin/terminales/crear", terminalController::crear);
	Spark.get("/admin/terminales/modificar/terminal/:id", terminalController::modifView, engine);
	Spark.post("/admin/terminales/modificar/terminal/:id", terminalController::modificar);
	Spark.post("/admin/terminales/eliminar/terminal/:id", terminalController::eliminar);
	
	//Administracion de consultas
	Spark.get("/admin/busquedas", busquedasController::listar, engine);
	}
}
