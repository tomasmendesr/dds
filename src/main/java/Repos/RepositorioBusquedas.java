package Repos;

import Model.ResultadoBusqueda;
import Model.Terminal;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import Converter.BigDecimalConverter;

public class RepositorioBusquedas {
	

    private static RepositorioBusquedas repositorioBusquedas;
    private List<ResultadoBusqueda> resultadosBusquedas;
    final Morphia morphia;
    final Datastore datastore;
    private Long contador;

    private RepositorioBusquedas() {
    	resultadosBusquedas = new ArrayList<>();
    	morphia = new Morphia();
    	morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
    	morphia.mapPackage("Model.ResultadoBusqueda");
    	datastore = morphia.createDatastore(new MongoClient(), "resultados_busqueda");
    	datastore.ensureIndexes();
    }

    public static RepositorioBusquedas getInstance() {
		if (repositorioBusquedas == null) repositorioBusquedas = new RepositorioBusquedas();
			return repositorioBusquedas;
	}
    
	public static void resetBusquedas() {
		repositorioBusquedas = null;
	}
	
    public void guardarBusqueda(ResultadoBusqueda unaBusqueda){
    	if(unaBusqueda.getId() == null){
    		unaBusqueda.setId(this.getContador());
    		this.incrementarContador();
    	}
    	final ResultadoBusqueda resultadoAGuardar = unaBusqueda;
    	datastore.save(resultadoAGuardar);
    }

    public List<ResultadoBusqueda> getAllBusquedas(){
    	final Query<ResultadoBusqueda> query = datastore.createQuery(ResultadoBusqueda.class);
    	return query.asList();
    }
    
    public void borrarTodasLasBusquedas(){
    	final Query<ResultadoBusqueda> query = datastore.createQuery(ResultadoBusqueda.class);
    	datastore.delete(query);
    }

    public List<ResultadoBusqueda> getResultadosBusquedasEnUnaTerminal(Terminal unaTerminal){
    	return datastore.createQuery(ResultadoBusqueda.class)
    			.field("terminalId")
    			.equal(unaTerminal.getId())
    			.asList();
    }
    
    public ResultadoBusqueda buscar(Long id){ 
    	return datastore.createQuery(ResultadoBusqueda.class)
    			.field("id")
    			.equal(id)
    			.get();
    }
    
    public Integer resultadosTotalesEn(Terminal unaTerminal){ // Obtengo una lista con todas las cantidades de resultados de las busquedas
    	return	this.getResultadosBusquedasEnUnaTerminal(unaTerminal).stream()
    				.map(resultado -> resultado.getCantidadDeResultados())
    				.reduce(0, (a,b) -> a + b); // Suma 
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
	
	public Long getContador(){
		return this.contador;
	}
	
	public void setContador(Long contador){
		this.contador= contador;
	}
	
	public void incrementarContador(){
		this.contador++;
	}
	

}
