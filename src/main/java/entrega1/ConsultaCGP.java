package entrega1;

import java.util.List;

public class ConsultaCGP implements Consulta {
	
	//Constructor
	public ConsultaCGP(ObserverCGP unObserver){
		observerCGP = unObserver;
	}
	
	//Atributos
	private ObserverCGP observerCGP;
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

	public ObserverCGP getObserverCGP() {
		return observerCGP;
	}

	public void setObserverCGP(ObserverCGP observerCGP) {
		this.observerCGP = observerCGP;
	}
}