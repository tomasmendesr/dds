package testEntrega5;

import javax.persistence.*;
import Master.POI;
import POIs.ParadaDeColectivo;
import POIsExt.Comuna;
import org.junit.*;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class TestPOI {

    ParadaDeColectivo paradaDel47;
    private Comuna comuna8;
    private Polygon	zonaComuna8;

    @Before
    public void init(){

    // Comuna 8
    comuna8 = new Comuna(8);
    zonaComuna8 = new Polygon();
    zonaComuna8.add(new Point(-34.6744,-58.5025));
    zonaComuna8.add(new Point(-34.6578,-58.4787));
    zonaComuna8.add(new Point(-34.6648,-58.4697));
    zonaComuna8.add(new Point(-34.6621,-58.4240));
    zonaComuna8.add(new Point(-34.7048,-58.4612));
    comuna8.setZona(zonaComuna8);

     // Parada del 47 -- Corvalan 3691
    paradaDel47 = new ParadaDeColectivo(new Point(-34.6715, -58.4676));
    paradaDel47.setDireccion("Corvalan 3691");

    }

/*
    @Test
    public void TestGuardoPOI(){

        paradaDel47.setDireccion("Corvalan 3691");

    }

*/


}


