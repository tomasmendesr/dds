package testEntrega6;

import Model.POI;
import Model.ResultadoBusqueda;
import Model.Terminal;
import POIs.ParadaDeColectivo;
import Repos.RepositorioBusquedas;
import Repos.RepositorioPOIs;
import org.junit.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 28/09/16.
 */
public class TestMongo {

    /*@Test
    public void test(){
        List<POI> list = new ArrayList<POI>();
        list.add(new ParadaDeColectivo(new Point(1,2)));
        list.add(new ParadaDeColectivo(new Point(2,3)));
        ResultadoBusqueda resultado = new ResultadoBusqueda("asd",list,2.0, new Terminal("asd", RepositorioPOIs.getInstance()));

        RepoBusquedas.getInstance().guardarBusqueda(resultado);

        Assert.assertEquals(1,1);
    }*/

    /*@Test
    public void test2(){
        List<ResultadoBusqueda> resultado = RepoBusquedas.getInstance().getAllBusquedas();
        Assert.assertTrue(!resultado.isEmpty());
    }*/

}
