package POIs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.lang.String;

import org.uqbar.geodds.Point;

import Master.POI;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;

@Entity
@DiscriminatorValue("L")
public class LocalComercial extends POI {

	//ATRIBUTOS
	private Rubro 							rubro;
	
	//CONSTRUCTOR
	public LocalComercial(Point unaUbicacion, Rubro unRubro){
		super(unaUbicacion);
		this.setRubro(unRubro);
	}

	
	//METODOS
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return this.getListaDeRangosDeAtencion().stream().
			   anyMatch(rangoDeAtencion -> this.rangoDeAtencionDisponible(rangoDeAtencion,unTiempo));
	}
	
	public boolean rangoDeAtencionDisponible(RangoDeAtencion unRangoDeAtencion, LocalDateTime unTiempo){
		return unRangoDeAtencion.disponible(unTiempo);
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getListaDeRangosDeAtencion().add(unRangoDeAtencion);
	}

	public void addPalabraClave(String unaPalabraClave){
		this.getTags().add(unaPalabraClave);
	}

	public int cantidadDePalabrasClave() { return tags.size(); }

	//Modificar locales comerciales
	public Boolean tieneNombreYPalabrasEspecificadas(String nombre,  String[] unasPalabras){
		if(nombre.equals(nombre)){ return this.tienePalabrasEspecificadas(unasPalabras); }
		else return Boolean.FALSE;
	}

	public Boolean tienePalabrasEspecificadas(String[] unasPalabras) {
		int cantidad = unasPalabras.length;
		Integer marcador = 0;
		for (int i = 0; i < cantidad; i += 1) {
			int iActual = i;
			if (tags.stream().anyMatch(palabraClave -> palabraClave.equals(unasPalabras[iActual]))) {
				this.actualizarPalabrasClaves(unasPalabras);
				marcador = 1;
			}

		}
		if(marcador.equals(1)){ return Boolean.TRUE; }
		else return Boolean.FALSE;

	}

	public void actualizarPalabrasClaves(String[] unasPalabras){
		tags.clear();
		List<String> nuevaColeccionDePalabrasClave = new ArrayList<String>(Arrays.asList(unasPalabras));
		tags.addAll(nuevaColeccionDePalabrasClave);
	}
	
	//GETTERS Y SETTERS
		public Rubro getRubro(){
			return rubro;
		}
		
		public void setRubro(Rubro unRubro){
			rubro = unRubro;
		}


}
