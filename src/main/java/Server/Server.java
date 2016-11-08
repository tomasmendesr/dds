package Server;
import static spark.Spark.*;

import spark.Spark;

public class Server {
	    public static void main(String[] args) {
	    	Spark.port(9000);
	        get("/hello", (req, res) -> "Hello World");
	    }
	    
	    // para probarlo en  navegador -> localhost:9000/hello 
}