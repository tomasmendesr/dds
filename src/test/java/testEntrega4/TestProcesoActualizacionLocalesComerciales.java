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
import Procesos.ActualizarLocalesComerciales;
import Procesos.ResultadoProceso;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class TestProcesoActualizacionLocalesComerciales {


    private Comuna comuna8;
    private LocalComercial libreriaEscolar;
    private LocalComercial kioskoDeDiarios;
    private LocalComercial tiendaDeRopa;
    private ParadaDeColectivo paradaDel47;
    private CGP cgp;
    private Banco banco;
    private Polygon zonaComuna8;
    //private ActualizarLocales actualizarLocales;
    private ActualizarLocalesComerciales local;
    String texto;
    private ResultadoProceso resultadoProceso;


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

        // Parada del 47 -- Corvalan 3691
        paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
        paradaDel47.setDireccion("Corvalan 3691");
        paradaDel47.setNombre("Parada del 47");

        // CGP que provee Asesoramiento Legal -- Av Escalada 3100
        cgp = new CGP(new Point(-34.6672, -58.4669));
        cgp.setDireccion("Av Escalada 3100");
        cgp.setNombre("Asterisco");
        cgp.setComuna(comuna8);
        cgp.addTag("asesoramiento");
        cgp.addTag("47"); //podria ser que el CGP estuviese cerca de la parada y lo taggean

        // Banco -- Av Riestra 5002
        banco = new Banco(new Point(-34.6719, -58.4695));
        banco.addTag("deposito");
        banco.setNombre("Banco Nacion");
        banco.setComuna(comuna8);

        // Libreria Escolar -- Av Argentina 4802
        Rubro rubroLibreriaEscolar = new Rubro(500.0);
        libreriaEscolar = new LocalComercial(new Point(-34.6720, -58.4678), rubroLibreriaEscolar);
        libreriaEscolar.setComuna(comuna8);
        libreriaEscolar.addTag("libreria");
        libreriaEscolar.setNombre("Asterisco");
        libreriaEscolar.addPalabraClave("escuela");
        libreriaEscolar.addPalabraClave("lapices");

        // Kiosko de Diarios -- Albariño 3702
        Rubro rubroKioskoDeDiarios = new Rubro(200.0);
        kioskoDeDiarios = new LocalComercial(new Point(-34.6717, -58.4673), rubroKioskoDeDiarios);
        kioskoDeDiarios.setComuna(comuna8);
        kioskoDeDiarios.addTag("caramelos");
        kioskoDeDiarios.setNombre("Kiosko de Carlitos");
        kioskoDeDiarios.addPalabraClave("diarios");
        kioskoDeDiarios.addPalabraClave("revistas");
        kioskoDeDiarios.addPalabraClave("libros");

        // Farmacia -- Corrientes 3702
        Rubro rubroTiendasDeRopa = new Rubro(200.0);
        tiendaDeRopa = new LocalComercial(new Point(-34.6717, -58.4673), rubroTiendasDeRopa);
        tiendaDeRopa.setComuna(comuna8);
        tiendaDeRopa.addTag("pantalones");
        tiendaDeRopa.setNombre("Asterico");
        tiendaDeRopa.addPalabraClave("remeras");
        tiendaDeRopa.addPalabraClave("polleras");
        tiendaDeRopa.addPalabraClave("ropa");


        //Agrega POIs al repositorioPOIs
        RepositorioPOIs.getInstance().agregarPOI(paradaDel47);
        RepositorioPOIs.getInstance().agregarPOI(cgp);
        RepositorioPOIs.getInstance().agregarPOI(banco);
        RepositorioPOIs.getInstance().agregarPOI(libreriaEscolar);
        RepositorioPOIs.getInstance().agregarPOI(kioskoDeDiarios);

        //Texto
        texto = "Asterisco;escuela ropa uniformes buzos";

        //Local comercial
        local = new ActualizarLocalesComerciales(RepositorioPOIs.getInstance());
        local.setTexto(texto);
    }

   @Test
    public void laCantidadDeLocalesModificadosEs1(){
        ResultadoProceso resultadoProceso = local.realizarAccion();
        Assert.assertEquals(1, resultadoProceso.getCantElementosAfectados());
    }

    @Test
    public void laCantidadDeLocalesModificadosEs2(){
        RepositorioPOIs.getInstance().agregarPOI(tiendaDeRopa);
        ResultadoProceso resultadoProceso = local.realizarAccion();
        Assert.assertEquals(2, resultadoProceso.getCantElementosAfectados());
    }

    @Test
    public void laCantidadDeLocalesModificadosEs0(){
        RepositorioPOIs.getInstance().quitarPOI(libreriaEscolar);
        ResultadoProceso resultadoProceso = local.realizarAccion();
        Assert.assertEquals(0, resultadoProceso.getCantElementosAfectados());
    }

    @Test
    public void laCantidadDePalabrasClaveDeLibreriaEscolarAntesDeActualizarLosLocalesComercialesEs2(){
        int cantidadPalabrasClave = libreriaEscolar.cantidadDePalabrasClave();
        Assert.assertEquals(2, cantidadPalabrasClave);
    }

    @Test
    public void laCantidadDePalabrasClaveDeLibreriaEscolarDespuesDeActualizarLosLocalesComercialesEs4(){
        ResultadoProceso resultadoProceso = local.realizarAccion();
        int cantidadPalabrasClave = libreriaEscolar.cantidadDePalabrasClave();
        Assert.assertEquals(4, cantidadPalabrasClave);
    }

    @Test
    public void laCantidadDePalabrasClaveDeKioskoDeDiariosDespuesDeActualizarLosLocalesComercialesEs3(){
        ResultadoProceso resultadoProceso = local.realizarAccion();
        int cantidadPalabrasClave = kioskoDeDiarios.cantidadDePalabrasClave();
        Assert.assertEquals(3, cantidadPalabrasClave);
    }

    @After
	public void tearDown(){
		RepositorioPOIs.resetPOIs();
	}

}



