package Procesos;

import java.util.List;
import java.util.stream.Collectors;

import Master.Terminal;
import ObserversTerminal.FuncionalidadExtraTerminal;
import POIsExt.Comuna;

public class SegunComuna extends TipoDeCriterio {
	//atributo
	private Comuna comunaSeleccionada;
	
	//getters y setters
	public Comuna getComunaSeleccionada() {
		return comunaSeleccionada;
	}
	public void setComunaSeleccionada(Comuna comunaSeleccionada) {
		this.comunaSeleccionada = comunaSeleccionada;
	}
	
	//Metodos
	public void agregar(FuncionalidadExtraTerminal accion){
		this.terminalesDeLaComuna(this.getComunaSeleccionada()).forEach(terminal -> terminal.addObserver(accion));
	}
	
	public void quitar(FuncionalidadExtraTerminal accion){
		this.terminalesDeLaComuna(this.getComunaSeleccionada()).forEach(terminal -> terminal.quitarAccion(accion));
	}
	
	public List<Terminal> terminalesDeLaComuna(Comuna comuna){
		return this.getTodasLasTerminales().stream()
		.filter(terminal -> terminal.getComuna() == comuna)
		.collect(Collectors.toList());
	}
	
	public Integer cantidadDeAfectados(){
		return this.terminalesDeLaComuna(this.getComunaSeleccionada()).size();
	}
	
}
