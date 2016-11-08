package Server;
import static spark.Spark.*;

import spark.Spark;

public class Server {
	    public  void testServer(String[] args) {
	    	Spark.port(9000);
	        get("/hello", (req, res) -> "Hello World");
	    }
}