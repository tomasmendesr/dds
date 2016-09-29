package Repos;

import Model.ResultadoBusqueda;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by fede on 29/09/16.
 */
public class RepoBusquedas {


    public static RepoBusquedas getInstance() {
        return ourInstance;
    }
    private static RepoBusquedas ourInstance = new RepoBusquedas();
    private MongoDatabase db;

    private RepoBusquedas() {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        db = mongoClient.getDatabase("tp");
        System.out.println("Conectado a MongoDB.");
    }

    public void guardarBusqueda(ResultadoBusqueda unaBusqueda){

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
}
