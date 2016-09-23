package Procesos;

import java.util.*;

import Adapters.AdapterServicioRestBajaPOIs;
import ProcesosExt.POIABajar;

public class GestorProcesos {

	//VER COMENTARIO EN ponerPOIsABajar()

	//Atributos
		private List<ResultadoProceso> 			resultadosProcesos;
		private List<Proceso> 					procesos;
		private AdapterServicioRestBajaPOIs		adapterServicioRestBajaPOIs;
		private Timer		 					timerBajaPOIs;
		
	//Constructor
	public GestorProcesos(){
		resultadosProcesos = new ArrayList<ResultadoProceso>();
		procesos = new ArrayList<Proceso>();
		adapterServicioRestBajaPOIs = new AdapterServicioRestBajaPOIs();
		timerBajaPOIs = new Timer();
	}

	public static GestorProcesos nuevoGestorProcesosConBajaPOIs(){
		GestorProcesos gestorProcesos = new GestorProcesos();
		gestorProcesos.iniciarProcesoDeBajaAutomaticaDePOIs();
		return gestorProcesos;

	}

	//Metodos

		//region MAIN

		public void agregarProcesoAEjecutar(Proceso proceso, Date horario, int cantAIterar){
			if(!procesos.contains(proceso)) { procesos.add(proceso);
			this.ejecutarTarea(proceso,horario,cantAIterar); }
		}

		public void ejecutarTarea(Proceso proceso, Date horario, int cantAIterar){
			Timer timer = new Timer();
			TimerTask tareaProgramada = new TimerTask(){
				@Override
				public void run(){
					ResultadoProceso resultado = proceso.realizarProceso();
					proceso.setCantidadAIterar(cantAIterar);
					resultadosProcesos.add(resultado);
					procesos.remove(proceso);
				}
			};
			timer.schedule(tareaProgramada, horario,cantAIterar);
		}

		private void iniciarProcesoDeBajaAutomaticaDePOIs(){
			GestorProcesos miGestor = this;
			TimerTask taskBajaPOIs = new TimerTask() {
				@Override
				public void run() {
					miGestor.ponerPOIsABajar();
				}
			};
			Date diaDeHoy = this.getDiaDeHoy();
			Integer cada24hs = (60*60*24*1000) ;
			timerBajaPOIs.schedule(taskBajaPOIs,diaDeHoy,cada24hs);
		}

		private void ponerPOIsABajar(){
			List<POIABajar> listaDePOIsABajar = this.getAdapterServicioRestBajaPOIs().getPOIsABajar();
			listaDePOIsABajar.forEach(poiABorrar -> {
					BajaDePOI procesoDeBaja = new BajaDePOI(poiABorrar.getIdPOI(), poiABorrar.getFecha());
					this.agregarProcesoAEjecutar(procesoDeBaja,procesoDeBaja.getFecha(),0); } );
			//VER SI LA CANTIDAD DE VECES A ITERAR ES 0 O 1
		}

		//endregion

		//region EXTRAS

		private Date getDiaDeHoy(){
			Date diaDeHoy = new Date();
			org.joda.time.LocalDateTime dia = new org.joda.time.LocalDateTime().now();
			diaDeHoy.setYear(dia.getYear());
			diaDeHoy.setDate(dia.getDayOfMonth());
			diaDeHoy.setHours(dia.getHourOfDay());
			diaDeHoy.setMinutes(dia.getMinuteOfHour());
			diaDeHoy.setSeconds(dia.getSecondOfMinute());
			return diaDeHoy;
		}

		//endregion


	//region Getters Y setters

	public List<ResultadoProceso> getResultadosProcesos(){
		return resultadosProcesos;
	}

	public List<Proceso> getProcesos(){
		return procesos;
	}

	public AdapterServicioRestBajaPOIs getAdapterServicioRestBajaPOIs() {return adapterServicioRestBajaPOIs;}

	//endregion

}



