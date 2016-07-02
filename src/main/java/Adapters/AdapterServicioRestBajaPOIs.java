package Adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

import ComponentesExternos.servicioRESTBajaPOIsStub;
import Master.GestorProcesos;
import Procesos.Proceso;
import ProcesosExt.POIABajar;

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
	
	public List<POIABajar> getPOIsABajar(){
		List<POIABajar> poisABajar = new ArrayList<POIABajar>();
		JSONObject poisObtenidos = servicio.getPOIs();
		Iterator<Integer> it = poisObtenidos.keySet().iterator();
		while(it.hasNext()){
			Integer id = it.next();
			Date fecha = (Date) poisObtenidos.get(id);
			poisABajar.add(new POIABajar(fecha,id));
		}
		return poisABajar;
	}
	
	
}
