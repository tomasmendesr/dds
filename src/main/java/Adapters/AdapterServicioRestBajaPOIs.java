package Adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

import ComponentesExternos.servicioRESTBajaPOIsStub;
import Master.GestorProcesos;
import Procesos.BajaDePOIs;
import Procesos.Proceso;
import ProcesosExt.POIABajar;

public class AdapterServicioRestBajaPOIs {

	//CONSTRUCTOR
	
	public AdapterServicioRestBajaPOIs(servicioRESTBajaPOIsStub unServicio){
		this.setServicio(unServicio);
	}
	
	
	//ATRIBUTOS
	
	servicioRESTBajaPOIsStub servicio;
	GestorProcesos gestor;

	
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
			BajaDePOIs procesoBaja = new BajaDePOIs(id,fecha);
			if(!this.existeElProceso(procesoBaja)){
				gestor.getProcesos().add(procesoBaja);
				gestor.ejecutarTarea(procesoBaja, procesoBaja.getFecha());
			}
		}
		return poisABajar;
	} //DESPUES HACER QUE TODAS LAS NOCHES SE PIDAN LOS POIS A BAJAR
		//SERIA UNA NUEVA CLASE QUE HEREDA DE PROCESO Y QUE VA A CONTENER EL CODIGO QUE TIENE ESTE ADAPTER
			// QUE NO DEBERIA TENER
	public Boolean existeElProceso(Proceso unProceso){
		return gestor.getProcesos().stream().anyMatch(proceso -> proceso.equals(unProceso));
	}
	
	
}
