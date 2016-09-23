package Procesos;

import java.time.LocalDateTime;

import ObserversTerminal.FuncionalidadExtraTerminal;
import Repos.RepositorioTerminales;


public class AccionesUsuarios extends Proceso{

	//Atributos
	private TipoDeCriterio criterio;
	private FuncionalidadExtraTerminal accion;
	private int agregarOQuitar; // 1 si es "agregar", 2 si es "quitar"
	private RepositorioTerminales repositorioTerminales;

	//Constructor
	public AccionesUsuarios(TipoDeCriterio criterio, String opcionAgregarOQuitar, FuncionalidadExtraTerminal accion){
		this.setTipoDeCriterio(criterio);
		this.asignarValorAlAgregarOQuitar(opcionAgregarOQuitar);
		this.setAccion(accion);		
	}
	
	// Metodos	
	public ResultadoProceso realizarProceso(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		this.getTipoDeCriterio().setTodasLasTerminales(this.repositorioTerminales.getTerminales());
		try{
		switch (agregarOQuitar){
		case 1:
				this.getTipoDeCriterio().agregar(this.getAccion());
			break;
		case 2:
				this.getTipoDeCriterio().quitar(this.getAccion());
			break;
		}
		}catch(NullPointerException e){
			this.falle();
		}
		int afectados = this.getTipoDeCriterio().cantidadDeAfectados();
		resultadoProceso.setCantElementosAfectados(afectados);
		resultadoProceso.setResultadoDelProceso(true);
		//Tareas.guardarTarea(resultadoProceso); tira error de static; Hacer un singleton de la lista de procesos en Tarea?
		return resultadoProceso;
	}	
	
	public void asignarValorAlAgregarOQuitar(String opcionAgregarOQuitar){
		switch(opcionAgregarOQuitar){
		case "agregar":
			agregarOQuitar = 1;
			break;
		case "quitar":
			agregarOQuitar = 2; 
			break;
		default:
			agregarOQuitar = 0; // error
		}
	}
	
	//Getters y setters
	public void setTipoDeCriterio(TipoDeCriterio criterio){
		this.criterio = criterio;
	}
	public TipoDeCriterio getTipoDeCriterio(){
		return criterio;
	}
	
	public void setAccion(FuncionalidadExtraTerminal accion){
		this.accion = accion;
	}
	
	public FuncionalidadExtraTerminal getAccion(){
		return accion;
	}
	
	public void setRepositorioTerminales(RepositorioTerminales repositorioTerminales) {
		this.repositorioTerminales = repositorioTerminales;
	}
}
