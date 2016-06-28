package Procesos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Master.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;


public class AccionesUsuarios extends Tareas{

	//Atributos
	private TipoDeCriterio criterio;
	private String agregarOQuitar;
	private FuncionalidadExtraTerminal accion;
	private List<Terminal> todasLasTerminales;

	//Constructor
	public AccionesUsuarios(TipoDeCriterio criterio, String agregarOQuitar, FuncionalidadExtraTerminal accion){
		this.setTipoDeCriterio(criterio);
		this.setAgregarOQuitar(agregarOQuitar);
		this.setAccion(accion);
		todasLasTerminales = new ArrayList<Terminal>();
	}
	
	//Getters y setters
	public void setTipoDeCriterio(TipoDeCriterio criterio){
		this.criterio = criterio;
	}
	public TipoDeCriterio getTipoDeCriterio(){
		return criterio;
	}
	public void setAgregarOQuitar(String agregarOQuitar){
		this.agregarOQuitar = agregarOQuitar;
	}
	
	public void setAccion(FuncionalidadExtraTerminal accion){
		this.accion = accion;
	}
	
	public FuncionalidadExtraTerminal getAccion(){
		return accion;
	}
	
	public List<Terminal> getTodasLasTerminales() {
		return todasLasTerminales;
	}
	public void setTodasLasTerminales(List<Terminal> todasLasTerminales) {
		this.todasLasTerminales = todasLasTerminales;
	}
	
	public void agregarTerminal(Terminal unaTerminal){
		todasLasTerminales.add(unaTerminal);
	}
	// Metodos
	public ResultadoProceso realizarAccion(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		this.getTipoDeCriterio().setTodasLasTerminales(this.getTodasLasTerminales());
		switch (agregarOQuitar){
		case "agregar":
			this.getTipoDeCriterio().agregar(this.getAccion());
			break;
		case "quitar":
			this.getTipoDeCriterio().quitar(this.getAccion());
			break;
		}
		int afectados = this.getTipoDeCriterio().cantidadDeAfectados();
		resultadoProceso.setCantElementosAfectados(afectados);
		resultadoProceso.setResultadoDelProceso(true);
		return resultadoProceso;
	}	
}
