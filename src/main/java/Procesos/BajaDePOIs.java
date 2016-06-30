package Procesos;

import Master.RepositorioPOIs;

public class BajaDePOIs extends Tareas{
	
	//CONSTRUCTOR
	
	public BajaDePOIs(RepositorioPOIs unRepo){
		this.setRepositorioDePOIs(unRepo);
	}
	
	//ATRIBUTOS
	
	RepositorioPOIs repositorioDePOIs;

	
	//GETERS Y SETERS
	
	public RepositorioPOIs getRepositorioDePOIs() {
		return repositorioDePOIs;
	}

	public void setRepositorioDePOIs(RepositorioPOIs repositorioDePOIs) {
		this.repositorioDePOIs = repositorioDePOIs;
	}
	
	//METODOS
	
	private void bajarUnPOI(Integer unID){
		this.getRepositorioDePOIs().getColeccionDePOIS().remove(unID);
	}
	
}