package Procesos;

import java.util.*;

import Adapters.AdapterServicioRestBajaPOIs;
import Master.GestorProcesos;
import ProcesosExt.POIABajar;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class GestorPOIsABajar {
	
	//CONSTRUCTOR
	
	public GestorPOIsABajar(GestorProcesos unGestor){
		gestor = unGestor;
		this.iniciarBajaDePOIsAutomaticamente();
	}

	private AdapterServicioRestBajaPOIs adapter;
	private List<POIABajar>				listaDePOIsABajar;
	private GestorProcesos				gestor;
	private Timer 						timer;

	
	
	//GETERS Y SETERS
	
	public List<POIABajar> getListaDePOIsABajar() {
		return listaDePOIsABajar;
	}

	public void setListaDePOIsABajar(List<POIABajar> listaDePOIsABajar) {
		this.listaDePOIsABajar = listaDePOIsABajar;
	}	
	

	//METODOS

		//MAIN

		public void iniciarBajaDePOIsAutomaticamente(){
			GestorPOIsABajar miGestor = this;
            TimerTask taskBajarPOIs = new TimerTask() {
                @Override
				public void run() {
					miGestor.ponerPOIsABajar();
				}
			};
			Date diaDeHoy = this.getDiaDeHoy();
			Integer cada24hs = (60*60*24*1000) ;
			timer.schedule(taskBajarPOIs,diaDeHoy,cada24hs);
		}

		public void ponerPOIsABajar(){
		List<POIABajar> poisABajar = adapter.getPOIsABajar();
		poisABajar.forEach(poiABajar -> this.agregarSiNoEstaEnLaListaDePoisABajar(poiABajar));
		listaDePOIsABajar.forEach(poi ->
				gestor.agregarProcesoAEjecutar(new BajaDePOI(poi.getIdPOI(),poi.getFecha(),this), poi.getFecha(),1));
		}

		//EXTRAS

		private void agregarSiNoEstaEnLaListaDePoisABajar(POIABajar unPOI){
			if(!listaDePOIsABajar.contains(unPOI)) listaDePOIsABajar.add(unPOI);
		}

		private Date getDiaDeHoy(){
			Date diaDeHoy = new Date();
			LocalDateTime dia = new LocalDateTime().now();
			diaDeHoy.setYear(dia.getYear());
			diaDeHoy.setDate(dia.getDayOfMonth());
			diaDeHoy.setHours(dia.getHourOfDay());
			diaDeHoy.setMinutes(dia.getMinuteOfHour());
			diaDeHoy.setSeconds(dia.getSecondOfMinute());
			return diaDeHoy;
		}
	
}
