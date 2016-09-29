package Repos;

import Model.ResultadoBusqueda;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

/**
 * Created by fede on 29/09/16.
 */
public class RepoBusquedas {


    public static RepoBusquedas getInstance() {
        return ourInstance;
    }
    private static RepoBusquedas ourInstance = new RepoBusquedas();

    private RepoBusquedas() {

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
        try {
            collection.insertOne(dbObject);
            System.out.println("Se inserto correctamente un documento");
        } catch (Exception e){
            System.out.println("Hubo un error");
        }

    }

    public List<ResultadoBusqueda> getAllBusquedas(){
        MongoDatabase db = conectarAMongo();
        MongoCollection collection = db.getCollection("busquedas");
        FindIterable result = collection.find();
        return adaptarResultado(result);
    }

    private List<ResultadoBusqueda> adaptarResultado(FindIterable unResultado){
        return null;
    }
}
