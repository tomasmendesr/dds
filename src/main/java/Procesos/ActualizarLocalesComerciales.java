package Procesos;

import Master.RepositorioPOIs;
import POIs.LocalComercial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActualizarLocalesComerciales extends Tareas{

    //ATRIBUTOS
    private List<LocalComercial> localesComerciales;

    //Constructor
    public ActualizarLocalesComerciales(RepositorioPOIs repositorioPOIs){
        super();
        localesComerciales = new ArrayList<LocalComercial>();

    }

    //Metodos
    public void actualizarLocalesComerciales(String unTexto) {
        String[] campos = unTexto.split(";");
        String nombre = campos[0];
        String palabras = campos[1];
        String[] palabrasABuscar = palabras.split(" ");
        this.buscarPorNombreLocalComercial(nombre,palabrasABuscar);

    }

    public ResultadoProceso buscarPorNombreLocalComercial(String nombre,  String[] unasPalabras) {
        ResultadoProceso resultadoProceso = new ResultadoProceso();
        resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
        List<LocalComercial> localesModificados = new ArrayList<LocalComercial>();
        localesModificados = (List<LocalComercial>) localesComerciales.stream().filter(localComercial -> (localComercial.tieneElNombreBuscado(nombre,unasPalabras) == 1));
        int localesAfectados = localesModificados.size();
        resultadoProceso.setCantElementosAfectados(localesAfectados);
        if(localesAfectados == 0) { resultadoProceso.setResultadoDelProceso(false); }
        else { resultadoProceso.setResultadoDelProceso(true); }
        return resultadoProceso;

    }

}
