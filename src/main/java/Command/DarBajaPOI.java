package Command;

import Procesos.BajaDePOIs;

public class DarBajaPOI implements Command{
	
	//Atributos
	private BajaDePOIs bajaDePois;
	
	//Constructor
	public DarBajaPOI(BajaDePOIs baja){
		this.bajaDePois = baja;
	}
	
	public void ejecutar(){
	//	bajaDePois.darDeBaja(); En este metodo tienen que acoplarse a lo que haga su proceso
	};

}
