package Command;

import Procesos.ActualizarLocalesComerciales;

public class ActualizarLocales implements Proceso{

	//Atributos
	private ActualizarLocalesComerciales local;
	
	//Constructor
	public ActualizarLocales(ActualizarLocalesComerciales local){
		this.local = local;
	}
	
	public void ejecutar(){
		local.realizarAccion(); //En este metodo tienen que acoplarse con lo que hagan sus procesos
	}
}
