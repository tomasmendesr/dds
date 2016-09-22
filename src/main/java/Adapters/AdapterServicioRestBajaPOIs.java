package Adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ComponentesExternos.ServicioRESTBajaPOIsStub;
import org.json.simple.JSONObject;

import ProcesosExt.POIABajar;

public class AdapterServicioRestBajaPOIs {

	//CONSTRUCTOR
	
	public AdapterServicioRestBajaPOIs(){
		this.setServicio(new ServicioRESTBajaPOIsStub());
	}
	
	
	//ATRIBUTOS
	
	ServicioRESTBajaPOIsStub servicio;

	
	//GETERS Y SETERS
	
	public ServicioRESTBajaPOIsStub getServicio() {
		return servicio;
	}

	public void setServicio(ServicioRESTBajaPOIsStub servicio) {
		this.servicio = servicio;
	}
	
	//METODOS
	
	@SuppressWarnings("unchecked")
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
