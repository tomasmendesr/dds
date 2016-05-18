package entrega1;

import java.util.List;

public class ConsultaBanco implements Consulta {
	
	//Constructor
	public ConsultaBanco(ObserverBanco unObserver){
		observerBanco = unObserver;
	}
	
	//Atributos
	private ObserverBanco observerBanco;
	private String nombreBanco;
	private String servicioBanco;
	/*Este metodo podria llamarse consultarPoisPorTipoConsulta que recibe un solo tipo de consulta
	 * y despues otro que se llame consultarPois que recibe una lista de consultas y 
	 * llama a esta funcion por cada tipo (ademas de hacer buscarPorTextoLibre)*/

	//Métodos
	@Override
	public List<POI> realizarConsulta() {
		return observerBanco.buscarBanco(this.getNombreBanco(), this.getServicioBanco());
	}
	
	//Getters y Setters
	public void setNombreBanco(String unNombre){
		nombreBanco = unNombre;
	}
	
	public String getNombreBanco(){
		return nombreBanco;
	}
	
	public void setServicioBanco(String unServicio){
		servicioBanco = unServicio;		
	}
	
	public String getServicioBanco(){
		return servicioBanco;
	}
	
	public ObserverBanco getObserverBanco() {
		return observerBanco;
	}

	public void setObserverBanco(ObserverBanco observerBanco) {
		this.observerBanco = observerBanco;
	}

}
