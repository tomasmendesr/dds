package POIs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.lang.String;

import org.uqbar.geodds.Point;

import Model.POI;
import POIsExt.RangoDeAtencion;
import POIsExt.Rubro;

@Entity @DiscriminatorValue("4")
public class LocalComercial extends POI {

	//ATRIBUTOS
	@ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name="rubro_id")
	private Rubro rubro;
	@OneToMany
	private List<RangoDeAtencion> listaDeRangosDeAtencion;
	
	//CONSTRUCTOR
	public LocalComercial(Point unaUbicacion, Rubro unRubro){
		super(unaUbicacion);
		this.setRubro(unRubro);
	}
	
	public LocalComercial() { }

	
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
	public List<RangoDeAtencion> getListaDeRangosDeAtencion() {
		return listaDeRangosDeAtencion;
	}

	public void setListaDeRangosDeAtencion(List<RangoDeAtencion> unaListaDeRangosDeAtencion) {
		listaDeRangosDeAtencion =  unaListaDeRangosDeAtencion;
	}
	
	public void addRangoDeAtencion(RangoDeAtencion unRangoDeAtencion){
		this.getListaDeRangosDeAtencion().add(unRangoDeAtencion);
	}
	
	public Rubro getRubro(){
			return rubro;
		}
		
	public void setRubro(Rubro unRubro){
			rubro = unRubro;
	}


}
