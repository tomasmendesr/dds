package Master;

import java.time.LocalDateTime;
import java.util.List;

public class ResultadoBusqueda{
	
	//CONSTRUCTOR
	
	public ResultadoBusqueda(String fraseBuscada, List<POI> resultadoBusqueda, double duracionConsulta){
		this.setMomentoDeBusqueda(LocalDateTime.now());
		this.setFraseBuscada(fraseBuscada);
		this.setResultadoBusqueda(resultadoBusqueda);
		this.setDuracionBusqueda(duracionConsulta);
	}
	
	// ATRIBUTOS
	
	String 			fraseBuscada;
	double 			duracionBusqueda;
	List<POI>		resultadoBusqueda;
	LocalDateTime	momentoDeBusqueda;
	

	// GETTERS Y SETTERS
	 
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

	//METODOS
	
	public int cantidadDeResultados(){
		return this.getResultadoBusqueda().size();
	}
}
