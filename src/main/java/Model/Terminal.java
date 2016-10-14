package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ObserversTerminal.AccionesTerminal;
import POIsExt.Comuna;
import Repos.RepositorioBusquedas;
import Repos.RepositorioPOIs;

import javax.persistence.*;

@Entity @Table(name = "terminales")
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
	private RepositorioPOIs	repositorioPOIs;
	
	@Transient
    private RepositorioBusquedas repoBusquedas;
    
	//CONSTRUCTOR
    public Terminal() { }

	public Terminal(String nombre){
		this.setRepositorioPOIs(RepositorioPOIs.getInstance());
		this.setNombre(nombre);
		observers = new ArrayList<AccionesTerminal>();
	}
	
	
	//METODOS
		
	//Consultar Busqueda POIs con TiempoMax
	public List<POI> consultarPOIsXTiempoEstimado(String unaConsulta, double tiempoMax){
		double tInicio = System.currentTimeMillis(), tFin, duracion;
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = repositorioPOIs.consultarPOIs(unaConsulta);
		tFin = System.currentTimeMillis();
		duracion = (tFin - tInicio);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(unaConsulta,poisEncontrados,duracion,this);
		resultadoBusqueda.setTiempoEstimadoBusqueda(tiempoMax);
		observers.forEach(observer -> observer.realizarAccion(this, resultadoBusqueda));
		return poisEncontrados;
	}

	
	public Integer obtenerResultadosTotales(){ // Obtengo la suma de todos los resultados de las busquedas
		return (this.resultadosTotales().stream().reduce(0, (a,b) -> a + b));
	}
	
	public List<Integer> resultadosTotales(){ // Obtengo una lista con todas las cantidades de resultados de las busquedas
		return (this.busquedasRealizadas().stream()
				.map(resultado -> resultado.getCantidadDeResultados())
				.collect(Collectors.toList()));
	}
	
	public List<ResultadoBusqueda> busquedasRealizadas(){ // Busquedas realizadas en esta Terminal
		return (RepositorioBusquedas.getInstance().getResultadosBusquedas().stream()
				.filter(busq -> busq.getTerminalId() == this.getId()) 
				.collect(Collectors.toList()));
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

	public RepositorioPOIs getRepositorioPOIs() {
		return repositorioPOIs;
	}

	public void setRepositorioPOIs(RepositorioPOIs repositorioPOIs) {
		this.repositorioPOIs = repositorioPOIs;
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


}