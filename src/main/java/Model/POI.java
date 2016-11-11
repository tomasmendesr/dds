package Model;

import org.uqbar.geodds.Point;

import POIsExt.Comuna;
import java.util.ArrayList;
import Converter.*;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.lang.String;


@Entity // Para morphia y Hibernate
//@Table(name="pois")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.INTEGER)
// 1: ParadaaDeColectivo - 2: CGP - 3: Banco - 4: LocalComercial
public abstract class POI {

	//ATRIBUTOS
	@Id //Morphia y Hibernate
	@GeneratedValue @Column(name="poi_id")
	private long id;
	
	@Column(name="ubicacion") @Convert(converter = PointConverter.class)
	private Point ubicacion;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@ElementCollection
	@CollectionTable(name="tags", joinColumns=@JoinColumn(name="poi_id"))
	@Column(name="tag")
	@Embedded //De morphia
	protected List<String> tags; // palabras claves
	
	@ManyToOne	@JoinColumn(name = "comuna_numero")
	private Comuna comuna;
	
//	@Column
//	private String tipo;

	
	//CONSTRUCTOR

	public POI() { }

	public POI(Point unaUbicacion) {
		this.setUbicacion(unaUbicacion);
		this.instanciarNuevaColeccionDeTags(); //Para inicializar el ArrayList de Tags
//		this.setTipo(this.getClass().toString());
	}
		
	

	//METODOS
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return this.getUbicacion().distance(otroPOI.getUbicacion())*1000 < metros;	// Para pasarlo a metros
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getUbicacion() != null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null;
	}
	
	
	// Busqueda por texto libre
	public boolean detectarTagBuscado(String unTag){
		return tags.contains(unTag);
	}

	//Calculo de cercania
	
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getUbicacion().distance(unaUbicacion) * 1000 < this.cercaniaRequerida();
	}
	
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	public boolean estaCercaDeUnPOI(POI unPOI){
		return this.estaAMenosDeXMetrosDeOtroPOI(unPOI, 500); //para pasar a metros
	}
	
	public void quitarTagAModificar(String tagAModificar){
		tags.removeIf(tag -> tag == tagAModificar);
	}
	
	// Es valido
	public boolean esValido(){
		return (this.estaGeolocalizado() && this.tieneNombre()); 
	}

	// Identificar si cumple los requisitos: si tiene el nombre especificado en el texto(para modificar locales comerciales)
	public Boolean tieneNombreYPalabrasEspecificadas(String unNombre,  String[] unasPalabras){
		if(nombre.equals(unNombre)){ return this.tienePalabrasEspecificadas(unasPalabras); }
		else return Boolean.FALSE;
	}

	public Boolean tienePalabrasEspecificadas(String[] unasPalabras) { return Boolean.FALSE; }

	public void actualizarPalabrasClaves(String[] unasPalabras){ }

	
	//GETTERS Y SETTERS

		public void setUbicacion(Point unaUbicacion) {
			ubicacion = unaUbicacion;
			
		}

		public Point getUbicacion(){
			return ubicacion;
		}
		
		public String getNombre(){
			return nombre;
		}
		
		public void setNombre(String unNombre){
			nombre = unNombre;
		}
		
		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String unaDireccion) {
			direccion = unaDireccion;
		}
		
		public void instanciarNuevaColeccionDeTags(){
			tags = new ArrayList<String>();
		}
		
		public List<String> getTags(){
			return tags;
		}
		
		public void addTag(String tag){//Agrega un tag al ArrayList
			tags.add(tag);
		}
		
		public void removeTag(String tag){
			tags.remove(tag);
		}
		
		public void setComuna(Comuna unaComuna){
			comuna = unaComuna;
		}
		
		public Comuna getComuna(){
			return this.comuna;
		}
		
		public long getID() {
			return id;
		}

		public void setID(long id) {
			this.id = id;
		}
		
		public String getUrl(){
			return "poi/" + getID();
		}
		
		/*public String getTipo(){
			return tipo;
		}
		
		public void setTipo(String tipo){
			this.tipo = tipo;
		}*/


}
