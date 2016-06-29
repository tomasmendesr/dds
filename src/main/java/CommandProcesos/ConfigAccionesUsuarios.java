package CommandProcesos;

import Procesos.AccionesUsuarios;

public class ConfigAccionesUsuarios implements Proceso{
	
	
	//Atributos
	private AccionesUsuarios acciones;
	
	//Constructor
	public ConfigAccionesUsuarios(AccionesUsuarios accion){
		this.acciones = accion;
	}
	
	public void ejecutar(){
		acciones.realizarAccion(); //En este metodo tienen que acoplarse a lo que haga su proceso
	}
}