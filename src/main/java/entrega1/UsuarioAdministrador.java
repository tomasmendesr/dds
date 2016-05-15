package entrega1;

import org.uqbar.geodds.Point;

public class UsuarioAdministrador extends Usuario {
	
	public UsuarioAdministrador(Mapa unMapa) {
		super(unMapa);
	}

	public void agregarPOI(POI unPOI){
		this.getMapa().addPOI(unPOI);
	}
	
	public void quitarPOI(POI unPOI){
		this.getMapa().removePOI(unPOI);
	}
	
	public void modificarUbicacion(POI unPOI, Point nuevaUbicacion){
		unPOI.setUbicacion(nuevaUbicacion);
	}
	
	public void modificarDireccion(POI unPOI, String nuevaDireccion){
		unPOI.setDireccion(nuevaDireccion);
	}
	
	public void modificarNombre(POI unPOI, String nuevoNombre){
		unPOI.setNombre(nuevoNombre);
	}
	
	public void modificarComuna(POI unPOI, Comuna nuevaComuna){
		unPOI.setComuna(nuevaComuna);
	}
	
	public void modificarRangoDeAtencion(POI unPOI, RangoDeAtencion nuevoRango){
		unPOI.setRangoDeAtencion(nuevoRango);
	}
	
	public void agregarTag(POI unPOI, String nuevoTag){
		unPOI.addTag(nuevoTag);
	}
	
	public void quitarTag(POI unPOI, String tag){
		unPOI.removeTag(tag);
	}

}
