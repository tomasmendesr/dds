package Server;

import Controller.BusquedaController;
import Controller.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
	Spark.staticFiles.location("/public");
	
	BusquedaController busquedaController = new BusquedaController();
	
	Spark.get("/", LoginController::login, engine);
	Spark.get("/terminal/:id", busquedaController::listarPois, engine);
	Spark.get("/poi:id", busquedaController::mostrar, engine);
//	Spark.get("/proyectos/:id", terminalController::mostrar, engine);
//	Spark.post("/proyectos", proyectosController::crear);
	}
}
