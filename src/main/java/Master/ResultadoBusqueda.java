package Master;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class ResultadoBusqueda{
	
	//CONSTRUCTOR

	public ResultadoBusqueda(){ }

	public ResultadoBusqueda(String fraseBuscada, List<POI> resultadoBusqueda, double duracionConsulta){
		this.setMomentoDeBusqueda(LocalDateTime.now());
		this.setFraseBuscada(fraseBuscada);
		this.setResultadoBusqueda(resultadoBusqueda);
		this.setDuracionBusqueda(duracionConsulta);
	}
	
	// ATRIBUTOS

	@Id
	@GeneratedValue
	@Column(name="RESULTADO_BUSQUEDA_ID")
	int 			id;
	@Column(name="FRASE_BUSCADA")
	String 			fraseBuscada;
	@Column(name="DURACION_BUSQUEDA")
	double 			duracionBusqueda;
	@Transient
	List<POI>		resultadoBusqueda;
	@Transient
	LocalDateTime	momentoDeBusqueda;
	@Column(name="TIEMPO_ESTIMADO_BUSQUEDA")
	Double			tiempoEstimadoBusqueda;
	

	// GETTERS Y SETTERS


	public int getId() { return id;  }

	public void setId(int id) {  this.id = id;  }

	public int getCantidadDeResultados(){
		return resultadoBusqueda.size();
	}
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public List<POI> getResultadoBusqueda() {
		return resultadoBusqueda;
	}

	public void setResultadoBusqueda(List<POI> resultadoBusqueda) {
		this.resultadoBusqueda = resultadoBusqueda;
	}

	public double getDuracionBusqueda(){
		return duracionBusqueda;
	}
		
	public void setDuracionBusqueda(double unaDuracionBusqueda){
		duracionBusqueda = unaDuracionBusqueda;
	}
	
	public LocalDateTime getMomentoDeBusqueda() {
		return momentoDeBusqueda;
	}

	public void setMomentoDeBusqueda(LocalDateTime momentoDeBusqueda) {
		this.momentoDeBusqueda = momentoDeBusqueda;
	}
	
	public double getTiempoEstimadoBusqueda() {
		return tiempoEstimadoBusqueda;
	}

	public void setTiempoEstimadoBusqueda(double tiempoEstimadoBusqueda) {
		this.tiempoEstimadoBusqueda = tiempoEstimadoBusqueda;
	}

	//METODOS
	public int cantidadDeResultados(){
		return this.getResultadoBusqueda().size();
	}
	
	public boolean sinTiempoEstimado(){
		return tiempoEstimadoBusqueda == null;
	}
}
