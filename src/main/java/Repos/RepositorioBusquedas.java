package Repos;

import Model.ResultadoBusqueda;
import Model.Terminal;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioBusquedas {
	

    private static RepositorioBusquedas repositorioBusquedas;
    private List<ResultadoBusqueda> resultadosBusquedas;

    private RepositorioBusquedas() {
    	resultadosBusquedas = new ArrayList<>();
    }

    public static RepositorioBusquedas getInstance() {
		if (repositorioBusquedas == null) repositorioBusquedas = new RepositorioBusquedas();
			return repositorioBusquedas;
	}
    
	public static void resetBusquedas() {
		repositorioBusquedas = null;
	}
	
    private MongoDatabase conectarAMongo(){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        System.out.println("Conectado a MongoDB.");
        return mongoClient.getDatabase("tp");
    }

    public void guardarBusqueda(ResultadoBusqueda unaBusqueda){
        MongoDatabase db = conectarAMongo();
        MongoCollection collection = db.getCollection("busquedas");

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("terminal",unaBusqueda.getTerminalId()).
        append("frase buscada",unaBusqueda.getFraseBuscada()).
        append("fecha",unaBusqueda.getMomentoDeBusqueda().toString()).
        append("cantidad de pois encontrados",unaBusqueda.getCantidadDeResultados()).
        append("pois encontrados", unaBusqueda.getResultadoBusqueda().stream().
                        map(poi -> poi.getNombre().toString())).
        append("duracion",unaBusqueda.getDuracionBusqueda());

        collection.insertOne(dbObject);

        //usar morphia

    }

    public List<ResultadoBusqueda> getAllBusquedas(){
        MongoDatabase db = conectarAMongo();
        MongoCollection collection = db.getCollection("busquedas");
        FindIterable result = collection.find();
        return adaptarResultado(result);
    }

    public List<ResultadoBusqueda> getResultadosBusquedasEnUnaTerminal(Terminal unaTerminal){
    	return resultadosBusquedas.stream()
			.filter(busq -> busq.seRealizoEn(unaTerminal)) 
			.collect(Collectors.toList());
    }
    
    public Integer resultadosTotalesEn(Terminal unaTerminal){ // Obtengo una lista con todas las cantidades de resultados de las busquedas
    	return	this.getResultadosBusquedasEnUnaTerminal(unaTerminal).stream()
    				.map(resultado -> resultado.getCantidadDeResultados())
    				.reduce(0, (a,b) -> a + b); // Suma 
    }
    
    private List<ResultadoBusqueda> adaptarResultado(FindIterable unResultado){
        return null;
    }
    
	public List<ResultadoBusqueda> getResultadosBusquedas() {
		return resultadosBusquedas;
	}

	public void setResultadosBusquedas(List<ResultadoBusqueda> resultadosBusquedas) {
		this.resultadosBusquedas = resultadosBusquedas;
	}

	public void addResultadoBusqueda(ResultadoBusqueda resultadoBusqueda){
		resultadosBusquedas.add(resultadoBusqueda);
	}
	

}
