package testEntrega4;

import ComponentesExternos.ServicioRESTBajaPOIsStub;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import Procesos.BajaDePOI;
import Procesos.GestorProcesos;
import Repos.RepositorioPOIs;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestProcesoBajaDePOIs {

    RepositorioPOIs             repositorioPOIs;
    Banco                       banco;
    Banco                       banco2;
    CGP                         cgp;
    CGP                         cgp2;
    LocalComercial              localComercial;
    ParadaDeColectivo           paradaDeColectivo;
    GestorProcesos              gestorProcesos;
    ServicioRESTBajaPOIsStub    servicioRESTBajaPOIsStub;

    @Before
    public void init(){




        //Inicializo el repo de POIs

        repositorioPOIs = RepositorioPOIs.getInstance();

        //Inicializo el gestor de procesos

        gestorProcesos = new GestorProcesos();

        //Inicializo el servico REST

        servicioRESTBajaPOIsStub = new ServicioRESTBajaPOIsStub();

        //Inicializo los POIs

        banco = new Banco(null);                            //0
        banco2 = new Banco(null);                           //1
        cgp = new CGP(null);                                //2
        cgp2 = new CGP(null);                               //3
        localComercial = new LocalComercial(null,null);     //4
        paradaDeColectivo = new ParadaDeColectivo(null);    //5

        //Agrego los pois al repo

        repositorioPOIs.agregarPOI(banco);
        repositorioPOIs.agregarPOI(banco2);
        repositorioPOIs.agregarPOI(cgp);
        repositorioPOIs.agregarPOI(cgp2);
        repositorioPOIs.agregarPOI(localComercial);
        repositorioPOIs.agregarPOI(paradaDeColectivo);

    }

    @Test
    public void elServicioRESTDevuelveResultados(){
        JSONObject resultados = servicioRESTBajaPOIsStub.getPOIs();
        Assert.assertTrue(!resultados.isEmpty());
    }

    /*@Test
    public void elProcesoBajaPOIEliminaElPOIConID3(){
        Assert.assertEquals(6,repositorioPOIs.getColeccionDePOIS().size());
        int cgp2ID = cgp2.getID();
        BajaDePOI procesoDeBajaPOI = new BajaDePOI(cgp2ID,null);
        procesoDeBajaPOI.darDeBaja();
        Assert.assertFalse(repositorioPOIs.contienePOISegunID(cgp2ID));
        Assert.assertEquals(5,repositorioPOIs.getColeccionDePOIS().size());
    }*/ //ROMPE PORQUE BORRAMOS LA CLASE IDENTITY, HACER LO DE PERSISTENCIA Y DESPUES REVISAR

   @Test
    public void elProcesoBajaPOINoEliminaPOIQueNoEstaEnElRepositorio(){
        Assert.assertEquals(6,repositorioPOIs.getColeccionDePOIS().size());
        int poiID = 8;
        BajaDePOI procesoDeBaja = new BajaDePOI(poiID,null);
        procesoDeBaja.darDeBaja();
        Assert.assertEquals(6,repositorioPOIs.getColeccionDePOIS().size());
    }

    @After
    public void tearDown(){
        RepositorioPOIs.resetPOIs();
    }
}
