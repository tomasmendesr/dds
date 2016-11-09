package Model;

import Repos.RepositorioBusquedas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity
@Indexes({
	    @Index(fields = @Field("terminalId"))
})

public class ResultadoBusqueda{
	
	// ATRIBUTOS
	@Id
	private Long 			id;
	private String 			fraseBuscada;
	private double 			duracionBusqueda;
	@Embedded
	private List<PoisDeBusqueda>		poisEncontrados;
	private Date			momentoDeBusqueda;
	private Double			tiempoEstimadoBusqueda;
	private Long			terminalId;

	//CONSTRUCTOR
	public ResultadoBusqueda() { }

	public ResultadoBusqueda(String fraseBuscada, List<POI> resultadoBusqueda, double duracionConsulta, Terminal terminal){
		setMomentoDeBusqueda(new Date());
		setFraseBuscada(fraseBuscada);
		setResultadoBusqueda(this.obtenerPoisDeBusqueda(resultadoBusqueda));
		setDuracionBusqueda(duracionConsulta);
		setTerminalId(terminal.getId());
	}

	//METODOS
	public List<PoisDeBusqueda> obtenerPoisDeBusqueda(List<POI> pois){
		List<PoisDeBusqueda> poisDeBusqueda = new ArrayList<PoisDeBusqueda>();
		pois.forEach(poi -> poisDeBusqueda.add(this.obtenerPoiDeBusqueda(poi)));
		return poisDeBusqueda;
	}
	
	public PoisDeBusqueda obtenerPoiDeBusqueda(POI poi){
		PoisDeBusqueda poiDeBusqueda = new PoisDeBusqueda();
		poiDeBusqueda.setId(poi.getID());
		poiDeBusqueda.setUbicacion(poi.getUbicacion());
		poiDeBusqueda.setNombre(poi.getNombre());
		poiDeBusqueda.setDireccion(poi.getDireccion());
		poiDeBusqueda.setTags(poi.getTags());
		poiDeBusqueda.setComuna(poi.getComuna());
		return poiDeBusqueda;
	}
	
	public int cantidadDeResultados(){
		return this.getResultadoBusqueda().size();
	}

	public boolean sinTiempoEstimado(){
		return tiempoEstimadoBusqueda == null;
	}

	public void persistite(){
		RepositorioBusquedas.getInstance().guardarBusqueda(this);
	}
	
	public Boolean seRealizoEn(Terminal unaTerminal){
		return terminalId == unaTerminal.getId();
	}

	// GETTERS Y SETTERS
	public Long getId() { return id;  }

	public void setId(Long id) {  this.id = id;  }

	public int getCantidadDeResultados(){
		return poisEncontrados.size();
	}
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public List<PoisDeBusqueda> getResultadoBusqueda() {
		return poisEncontrados;
	}

	public void setResultadoBusqueda(List<PoisDeBusqueda> resultadoBusqueda) {
		this.poisEncontrados = resultadoBusqueda;
	}

	public double getDuracionBusqueda(){
		return duracionBusqueda;
	}
		
	public void setDuracionBusqueda(double unaDuracionBusqueda){
		duracionBusqueda = unaDuracionBusqueda;
	}
	
	public Date getMomentoDeBusqueda() {
		return momentoDeBusqueda;
	}

	public void setMomentoDeBusqueda(Date momentoDeBusqueda) {
		this.momentoDeBusqueda = momentoDeBusqueda;
	}
	
	public double getTiempoEstimadoBusqueda() {
		return tiempoEstimadoBusqueda;
	}

	public void setTiempoEstimadoBusqueda(double tiempoEstimadoBusqueda) {
		this.tiempoEstimadoBusqueda = tiempoEstimadoBusqueda;
	}

	public Long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

}
