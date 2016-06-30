package Adapters;

import java.util.List;

import org.json.simple.JSONObject;

import ComponentesExternos.servicioRESTBajaPOIsStub;

public class AdapterServicioRestBajaPOIs {

	//CONSTRUCTOR
	
	public AdapterServicioRestBajaPOIs(servicioRESTBajaPOIsStub unServicio){
		this.setServicio(unServicio);
	}
	
	
	//ATRIBUTOS
	
	servicioRESTBajaPOIsStub servicio;

	
	//GETERS Y SETERS
	
	public servicioRESTBajaPOIsStub getServicio() {
		return servicio;
	}

	public void setServicio(servicioRESTBajaPOIsStub servicio) {
		this.servicio = servicio;
	}
	
	//METODOS
	
	/*public List getPOIsABajar(){
		JSONObject poisObtenidos = servicio.getPOIs();
		//poisObtenidos
	}*/
}
