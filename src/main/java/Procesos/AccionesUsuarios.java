package Procesos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Master.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;


public class AccionesUsuarios extends Proceso{

	//Atributos
	private TipoDeCriterio criterio;
	private FuncionalidadExtraTerminal accion;
	private List<Terminal> todasLasTerminales;
	private int agregarOQuitar; // 1 si es "agregar", 2 si es "quitar"

	//Constructor
	public AccionesUsuarios(TipoDeCriterio criterio, String opcionAgregarOQuitar, FuncionalidadExtraTerminal accion){
		this.setTipoDeCriterio(criterio);
		this.asignarValorAlAgregarOQuitar(opcionAgregarOQuitar);
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
	public ResultadoProceso realizarProceso(){
		ResultadoProceso resultadoProceso = new ResultadoProceso();
		resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
		this.getTipoDeCriterio().setTodasLasTerminales(this.getTodasLasTerminales());
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
}
