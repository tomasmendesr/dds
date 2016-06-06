package Master;

public class Resultado {
	
	// ATRIBUTOS
	
	String fraseBuscada;
	int cantidadResultados;
	double duracionBusqueda;  
	
	// CONSTRUCTOR
	
	public Resultado(String unaFraseBuscada,
		int unaCantidadResultados,
		double unaDuracionBusqueda) {
		this.setCantidadResultados(unaCantidadResultados);
		this.setDuracionBusqueda(unaDuracionBusqueda);
		this.setFraseBuscada(unaFraseBuscada);
	}

	// GETTERS Y SETTERS
	 
	public String getfraseBuscada(){
		return fraseBuscada;
	}
		
	public void setFraseBuscada(String unaFraseBuscada){
		fraseBuscada = unaFraseBuscada;
	}
	
	public int getCantidadResultados(){
		return cantidadResultados;
	}
		
	public void setCantidadResultados(int unaCantidadResultados){
		cantidadResultados = unaCantidadResultados;
	}
	
	public double getDuracionBusqueda(){
		return duracionBusqueda;
	}
		
	public void setDuracionBusqueda(double unaDuracionBusqueda){
		duracionBusqueda = unaDuracionBusqueda;
	}

}
