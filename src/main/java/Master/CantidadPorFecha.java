package Master;

public class CantidadPorFecha {
	
	String fecha;
	int cantidadResultados;
	
	// CONSTRUCTOR
	
	public CantidadPorFecha(String unaFecha,int unaCantidadResultados){
		this.setCantidadResultados(unaCantidadResultados);
		this.setFecha(unaFecha);
	}
	
	// GETTERS Y SETTERS
	 
	public String getFecha(){
		return fecha;
	}
			
	public void setFecha(String unaFecha){
		fecha = unaFecha;
	}

	public void setCantidadResultados(int unaCantidadResultados){
		cantidadResultados = this.getCantidadResutados() + unaCantidadResultados;
	}
		
	public int getCantidadResutados(){
		return cantidadResultados;
	}
}
