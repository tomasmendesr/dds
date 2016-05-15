package entrega1;

public class Usuario {
	
	// Constructor
	public Usuario (Mapa unMapa){
		this.setMapa(unMapa);
	}
	
	// Atributos
	private Mapa mapa;

	// Getters y Setters
	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	
}
