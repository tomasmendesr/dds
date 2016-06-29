package Command;

import Procesos.AccionesUsuarios;

public class ConfigAccionesUsuarios implements Command{
	
	
	//Atributos
	private AccionesUsuarios acciones;
	
	//Constructor
	public ConfigAccionesUsuarios(AccionesUsuarios accion){
		this.acciones = accion;
	}
	
	public void ejecutar(){
//		acciones.configurar(); En este metodo tienen que acoplarse a lo que haga su proceso
	}
}