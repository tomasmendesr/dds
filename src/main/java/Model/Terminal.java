package Model;


import java.util.ArrayList;
import java.util.List;
import ObserversTerminal.AccionesTerminal;
import POIsExt.Comuna;
import Repos.RepositorioBusquedas;

import javax.persistence.*;

@Entity //@Table(name = "terminales")
public class Terminal {

	//ATRIBUTOS
	@Id	@GeneratedValue	@Column(name="terminal_id")
	private Long id;
	    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="observers_x_terminal", 
		joinColumns = {@JoinColumn(name="terminal_id", referencedColumnName="terminal_id") },
		inverseJoinColumns= { @JoinColumn(name="accion_id", referencedColumnName="accion_id") } )
	private List<AccionesTerminal> observers; 
    
    @Column(name="Snombre")
	private String nombre;
            
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name="comuna_numero")
	private Comuna comuna;
    

	
	@Transient
    private RepositorioBusquedas repoBusquedas;
    
	//CONSTRUCTOR
    public Terminal() { }

	public Terminal(String nombre){
		this.setNombre(nombre);
		observers = new ArrayList<AccionesTerminal>();
	}
	
	
	//METODOS
	public Integer obtenerResultadosTotales(){ // Obtengo la suma de todos los resultados de las busquedas
		return RepositorioBusquedas.getInstance().resultadosTotalesEn(this);
	}	
	
	// Proceso 3: Agregar o quitar acciones
	public void quitarAccion(AccionesTerminal observer){ // El observer es el encargado de realizar la accion
		observers.remove(observer); 
	}
	public void addObserver(AccionesTerminal observer){
		observers.add(observer);
	}


	//GETTERS Y SETTERS
	public Long getId() {  return id;  }

	public void setId(Long id) {  this.id = id;  }

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public List<AccionesTerminal> getObservers() {
		return observers;
	}

	public void setObservers(List<AccionesTerminal> observers) {
		this.observers = observers;
	}

	public void setComuna(Comuna comuna){
		this.comuna = comuna;
	}

	public Comuna getComuna(){
		return comuna;
	}
	
	public String getUrl(){
		return "terminal/" + getId();
	}


}