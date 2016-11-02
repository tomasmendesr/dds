package Repos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import Converter.BigDecimalConverter;
import Model.POI;
import POIs.Banco;
import POIs.CGP;

public class RepositorioPOIsExternos {
	
    //Atributos
	private static RepositorioPOIsExternos repositorioPOIsExternos;
    final Morphia morphia;
    final Datastore datastore;
    private long contador;
    
    //Singleton
    private RepositorioPOIsExternos() {
    	morphia = new Morphia();
    	morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
    	morphia.mapPackage("Model.POI");
    	datastore = morphia.createDatastore(new MongoClient(), "pois_externos");
    	datastore.ensureIndexes();
    }

    public static RepositorioPOIsExternos getInstance() {
		if (repositorioPOIsExternos == null) repositorioPOIsExternos = new RepositorioPOIsExternos();
			return repositorioPOIsExternos;
	}
    
    public void almacenaListaPois(List<POI> pois){
    	pois.forEach(poi -> this.almacenaPoi(poi));
    }
    
    public void almacenaPoi(POI poi){
    	if(poi.getID() == 0L){
    		poi.setID(this.getContador());
    		this.incrementarContador();
    	}
    	final POI poiAGuardar = poi;
    	datastore.save(poiAGuardar);
    }
    
    public void borrarTodosLosPois(){
    	final Query<Banco> querybanco = datastore.createQuery(Banco.class);
    	datastore.delete(querybanco);
    	final Query<CGP> queryCGP = datastore.createQuery(CGP.class);
    	datastore.delete(queryCGP);
    }
    
    public List<Banco> buscarTodosLosBancos(){
    	final Query<Banco> query = datastore.createQuery(Banco.class);
    	return query.asList();
    }
    
    public List<CGP> buscarTodosLosCGP(){
    	final Query<CGP> query = datastore.createQuery(CGP.class);
    	return query.asList();
    }
    
    public List<POI> buscarTodosLosPois(){
    	List<Banco> bancos = this.buscarTodosLosBancos();
    	List<CGP> CGPs = this.buscarTodosLosCGP();
    	List<POI> pois = new ArrayList<POI>();
    	pois.addAll(bancos);
    	pois.addAll(CGPs);
    	return pois;
    }
    
    public List<POI> buscarPoisPorConsulta(String unaConsulta){
    	List<POI> pois = this.buscarTodosLosPois();
    	List<POI> poisFiltrados = new ArrayList<POI>();
    	poisFiltrados.addAll(pois.stream()
				.filter(poi -> poi.detectarTagBuscado(unaConsulta))
				.collect(Collectors.toList()));
    	poisFiltrados.addAll(pois.stream()
				.filter(poi -> (poi.getNombre() == unaConsulta))
				.collect(Collectors.toList()));
    	return poisFiltrados;
    }
    
	public long getContador(){
		return this.contador;
	}
	
	public void setContador(long contador){
		this.contador= contador;
	}
	
	public void incrementarContador(){
		this.contador++;
	}

}
