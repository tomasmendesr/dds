package entrega1;

import org.uqbar.geodds.Point;

public abstract class POISinServicio extends POI {

	public POISinServicio(Point unaUbicacion, Comuna unaComuna) {
		super(unaUbicacion, unaComuna);
	}

	public abstract boolean estaDisponible(Tiempo unTiempo);

}
