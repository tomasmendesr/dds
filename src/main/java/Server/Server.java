package Server;
import java.io.IOException;

import spark.Spark;
import spark.debug.DebugScreen;
import spark.utils.Stub;

public class Server {
	public static void main(String[] args) throws IOException {
		Spark.port(8000);

		try{
			Stub.persistirModelo(); // instancio todo
			DebugScreen.enableDebugScreen();
			Router.configure();
		}catch (Exception e){
			System.out.println("Error al abrir socket de mongo\n");
		}finally{
			//Stub.cerrarConeccion();
		}
	}
}