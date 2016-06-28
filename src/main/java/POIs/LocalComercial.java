package POIs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;

import org.uqbar.geodds.Point;

import Master.POI;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;

public class LocalComercial extends POI {

	//CONSTRUCTOR
	

	public LocalComercial(Point unaUbicacion, Rubro unRubro){
		super(unaUbicacion);
		this.setRubro(unRubro);
		ArrayList<RangoDeAtencion> unaColeccionDeRangosDeAtencion = new ArrayList<RangoDeAtencion>();
		this.setColeccionDeRangosDeAtencion(unaColeccionDeRangosDeAtencion);
		ArrayList<String> unaColeccionDePalabrasClave = new ArrayList<String>();
		this.setPalabrasClave(unaColeccionDePalabrasClave);
	}
	
	
	//ATRIBUTOS
	
	private Rubro 							rubro;
	private	List<RangoDeAtencion>			coleccionDeRangosDeAtencion;
	private	List<String>					coleccionDePalabrasClave;

	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	
	public List<RangoDeAtencion> getColeccionDeRangosDeAtencion(){
		return coleccionDeRangosDeAtencion;
	}
	
	public void setColeccionDeRangosDeAtencion(List<RangoDeAtencion> unaColeccionDeRangosDeAtencion){
		coleccionDeRangosDeAtencion = unaColeccionDeRangosDeAtencion;
	}

	public void setPalabrasClave(ArrayList<String> unaColeccionDepalabrasClave) { coleccionDePalabrasClave = unaColeccionDepalabrasClave; }

	public List<String> getColeccionDePalabrasClave(){
		return coleccionDePalabrasClave;
	}

	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return this.getColeccionDeRangosDeAtencion().stream().
			   anyMatch(rangoDeAtencion -> this.rangoDeAtencionDisponible(rangoDeAtencion,unTiempo));
	}
	
	public boolean rangoDeAtencionDisponible(RangoDeAtencion unRangoDeAtencion, LocalDateTime unTiempo){
		return unRangoDeAtencion.disponible(unTiempo);
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getColeccionDeRangosDeAtencion().add(unRangoDeAtencion);
	}

	public void addPalabraClave(String unaPalabraClave){
		this.getColeccionDePalabrasClave().add(unaPalabraClave);
	}

	// Modificar palabras clave(Proceso 1)
	public void modificarPalabrasClave(String[] unasPalabras){
		int cantidad = unasPalabras.length;
		for(int i = 0; i < cantidad ; i+= 1 ){
			int iActual = i;
			if(coleccionDePalabrasClave.stream().anyMatch(palabraClave -> palabraClave.equals(unasPalabras[iActual]))){
				coleccionDePalabrasClave.clear();
				List<String> nuevaColeccionDePalabrasClave = new ArrayList<String>(Arrays.asList(unasPalabras));
				coleccionDePalabrasClave.addAll(nuevaColeccionDePalabrasClave);
			}

		}

	}

}
