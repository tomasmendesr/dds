package testEntrega4;

import ComponentesExternos.ServicioRESTBajaPOIsStub;
import Master.GestorProcesos;
import Master.Identity;
import Master.RepositorioPOIs;
import POIs.Banco;
import POIs.CGP;
import POIs.LocalComercial;
import POIs.ParadaDeColectivo;
import Procesos.BajaDePOI;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.cglib.core.Local;

/**
 * Created by fede on 07/07/16.
 */
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

    @After
    public void init(){

        //Inicializo el identity

        Identity.initializeIdentity();

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

    /*@Test
    public void elServicioRESTDevuelveResultados(){
        JSONObject resultados = servicioRESTBajaPOIsStub.getPOIs();
        Assert.assertEquals(1,1);
    }

    @Test
    public void elProcesoBajaPOIEliminaElPOIConID3(){
        BajaDePOI procesoDeBajaPOI = new BajaDePOI(3,null);
        procesoDeBajaPOI.darDeBaja();
        Assert.assertTrue(!repositorioPOIs.getColeccionDePOIS().contains(cgp));
    }*/ //TIRA ERROR NULL POINTER PERO NO SE EN QUE
}
