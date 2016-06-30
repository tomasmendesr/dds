package Procesos;

import Master.RepositorioPOIs;

public class BajaDePOIs extends Proceso{
	
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
	
	@Override
	public ResultadoProceso realizarProceso(){
		ResultadoProceso resultado = new ResultadoProceso();
		
		
		
		return null; 
	}
	
	
	
}