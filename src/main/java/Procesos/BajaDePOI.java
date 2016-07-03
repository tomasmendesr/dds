package Procesos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import Adapters.AdapterServicioRestBajaPOIs;
import Master.RepositorioPOIs;

public class BajaDePOI extends Proceso{
	
	//CONSTRUCTOR
	
	public BajaDePOI(Integer id, Date fecha, GestorPOIsABajar gestorPB){
		this.setRepositorioDePOIs(RepositorioPOIs.getInstance());
		this.setFecha(fecha);
		this.setIdPOI(id);
		gestorPOIsABajar = gestorPB;
	}


	//ATRIBUTOS

	
	RepositorioPOIs repositorioDePOIs;
	Integer idPOI;
	Date	fecha;
	GestorPOIsABajar gestorPOIsABajar;

	//GETERS Y SETERS

	public RepositorioPOIs getRepositorioDePOIs() {
		return repositorioDePOIs;
	}

	public void setRepositorioDePOIs(RepositorioPOIs repositorioDePOIs) {
		this.repositorioDePOIs = repositorioDePOIs;
	}

	
	public Integer getIdPOI() {
		return idPOI;
	}

	public void setIdPOI(Integer idPOI) {
		this.idPOI = idPOI;
	}

	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	//METODOS

	@Override
	public ResultadoProceso realizarProceso(){
		ResultadoProceso resultado = new ResultadoProceso();
		resultado.setMomentoDeEjecucion(LocalDateTime.now());
		this.darDeBaja();
		if(this.fueBorrado()) {
			resultado.setCantElementosAfectados(1);
			resultado.setResultadoDelProceso(true);
			} else {
				resultado.setCantElementosAfectados(0);
				resultado.setResultadoDelProceso(false);
				}
		return resultado;
	}
	
	public void darDeBaja(){
		this.getRepositorioDePOIs().getColeccionDePOIS().remove(this.getIdPOI());
		if(this.fueBorrado()) 	gestorPOIsABajar.getListaDePOIsABajar().
								removeIf(poi -> poi.getIdPOI().equals(this.getIdPOI()));
	}
	
	public Boolean fueBorrado(){
		return !this.getRepositorioDePOIs().getColeccionDePOIS().stream().anyMatch(poi -> poi.getID() == this.getIdPOI());
	}


	
	
}