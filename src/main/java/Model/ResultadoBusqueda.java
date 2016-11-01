package Model;

import Repos.RepositorioBusquedas;

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
	private List<POI>		poisEncontrados;
	private Date	momentoDeBusqueda;
	private Double			tiempoEstimadoBusqueda;
	private Long			terminalId;

	//CONSTRUCTOR
	public ResultadoBusqueda() { }

	public ResultadoBusqueda(String fraseBuscada, List<POI> resultadoBusqueda, double duracionConsulta, Terminal terminal){
		setMomentoDeBusqueda(new Date());
		setFraseBuscada(fraseBuscada);
		setResultadoBusqueda(resultadoBusqueda);
		setDuracionBusqueda(duracionConsulta);
		setTerminalId(terminal.getId());
	}

	//METODOS
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

	public List<POI> getResultadoBusqueda() {
		return poisEncontrados;
	}

	public void setResultadoBusqueda(List<POI> resultadoBusqueda) {
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
