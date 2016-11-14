package Server;


import Controller.LoginController;
import Controller.PoiController;
import Controller.TerminalController;
import Controller.AdminController;
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
	
	Spark.get("/", LoginController::home, engine);
	Spark.post("/login", loginController::login);
	Spark.get("/terminal/:id", terminalController::home, engine);
	Spark.get("/terminal/:id/buscar", terminalController::buscar, engine);
	Spark.get("/admin", adminController::menu, engine);
	Spark.get("/admin/pois", poiController::listar, engine);
	Spark.post("/admin/pois", poiController::listar, engine);
	Spark.get("/admin/pois/modificar/poi/:id", poiController::modificarView, engine);
//	Spark.("/admin/pois/eliminar/poi/:id", poiController::eliminar);
	Spark.post("admin/pois/poi/:id", poiController::modificar);
	Spark.get("/terminal/:id", poiController::listar, engine); //Terminal controller deberia listarlos
	Spark.get("/admin/terminales", adminController::listarTerminales, engine);
	Spark.get("/admin/busquedas", adminController::listarBusquedas, engine);
	Spark.get("/admin/busquedas/:id", adminController::listarPoisBusquedas, engine);
	}
}
