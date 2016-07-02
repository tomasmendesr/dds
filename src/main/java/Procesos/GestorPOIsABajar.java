package Procesos;

import java.util.List;

import Adapters.AdapterServicioRestBajaPOIs;
import Master.GestorProcesos;
import ProcesosExt.POIABajar;

public class GestorPOIsABajar {
	
	//CONSTRUCTOR
	
	public GestorPOIsABajar(GestorProcesos unGestor){
		gestor = unGestor;
	}

	private AdapterServicioRestBajaPOIs adapter;
	private List<POIABajar>				listaDePOIsABajar;
	private GestorProcesos				gestor;
	

	//GETERS Y SETERS
	
	public List<POIABajar> getListaDePOIsABajar() {
		return listaDePOIsABajar;
	}

	public void setListaDePOIsABajar(List<POIABajar> listaDePOIsABajar) {
		this.listaDePOIsABajar = listaDePOIsABajar;
	}	
	
	//METODOS
	
	public void bajarPOIsDiariamente(){
		//ver como hacer para que se haga todos los dias
		List<POIABajar> poisABajar = adapter.getPOIsABajar();
		poisABajar.forEach(poiABajar -> this.agregarSiNoEstaEnLaListaDePoisABajar(poiABajar));
		listaDePOIsABajar.forEach(poi -> 
		gestor.agregarProcesoAEjecutar(new BajaDePOI(poi.getIdPOI(),poi.getFecha(),this), poi.getFecha(),1));
	}
	
	private void agregarSiNoEstaEnLaListaDePoisABajar(POIABajar unPOI){
		if(!listaDePOIsABajar.contains(unPOI)) listaDePOIsABajar.add(unPOI);
	}

	
	
}
