package entrega1;

public abstract class Usuario {

	//CONSTRUCTOR
	
	public Usuario(Mapa unMapa){
		this.setMapa(unMapa);
	}
	
	//ATRIBUTOS 
	
	private Mapa	mapa;
	
	//GETERS Y SETERS
	
	public Mapa getMapa() {
		return mapa;
	}
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	//METODOS
	
	public abstract void agregarPOI(POI unPOI);
	
	public abstract void quitarPOI(POI unPOI);
	
	public abstract void modificarPOI(POI unPOI);
	
	public void consultarPOI(POI unPOI /*, Consulta unaConsulta*/){};
	
}
