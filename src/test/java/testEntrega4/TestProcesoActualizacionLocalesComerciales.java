package testEntrega4;

import Master.Identity;
import Master.RepositorioPOIs;
import Master.Terminal;
import ObserversTerminal.ReportePorFecha;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import POIsExt.Rubro;
import org.junit.Before;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class TestProcesoActualizacionLocalesComerciales {


    private Comuna comuna8;
    private LocalComercial libreriaEscolar;
    private LocalComercial kioskoDeDiarios;
    private Polygon zonaComuna8;


    @Before
    public void init() {
    	
    	Identity.initializeIdentity();
    	
    	// Comuna 8
        comuna8 = new Comuna(8);
        zonaComuna8 = new Polygon();
        zonaComuna8.add(new Point(-34.6744, -58.5025));
        zonaComuna8.add(new Point(-34.6578, -58.4787));
        zonaComuna8.add(new Point(-34.6648, -58.4697));
        zonaComuna8.add(new Point(-34.6621, -58.4240));
        zonaComuna8.add(new Point(-34.7048, -58.4612));
        comuna8.setZona(zonaComuna8);

          // Libreria Escolar -- Av Argentina 4802
        Rubro rubroLibreriaEscolar = new Rubro(500.0);
        libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
        libreriaEscolar.setComuna(comuna8);
        libreriaEscolar.addTag("libreria");

        // Kiosko de Diarios -- Albariño 3702
        Rubro rubroKioskoDeDiarios = new Rubro(200.0);
        kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), rubroKioskoDeDiarios);
        kioskoDeDiarios.setComuna(comuna8);
        kioskoDeDiarios.addTag("caramelos");
        kioskoDeDiarios.setNombre("Kiosko de Carlitos");

        //Agrega POIs al repositorioPOIs
        RepositorioPOIs.getInstance().agregarPOI(libreriaEscolar);
        RepositorioPOIs.getInstance().agregarPOI(kioskoDeDiarios);

    }

}



