package entrega1;

import java.util.List;

public class ConsultaCGP implements Consulta {
	
	//Constructor
	public ConsultaCGP(ObserverConsultaCGP unObserver){
		observerCGP = unObserver;
	}
	
	//Atributos
	private ObserverConsultaCGP observerCGP;
	private String zonaOCalle;

	//Métodos//Métodos

	
	public List<POI> realizarConsulta() {
		return observerCGP.buscarCGP(this.getZonaOCalle());
	}
	
	//Getters y Setters
	public void setZonaOCalle(String unaZonaOCalle){
		zonaOCalle = unaZonaOCalle;
	}
	
	public String getZonaOCalle(){
		return zonaOCalle;
	}


}