package Procesos;

import Master.RepositorioPOIs;
import POIs.LocalComercial;
import org.mockito.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActualizarLocalesComerciales extends Tareas{

    //ATRIBUTOS
    private RepositorioPOIs repositorioPOIs;
    private String texto;

    //CONSTRUCTOR
    public ActualizarLocalesComerciales(){ super(); }

    //GETTERS Y SETTERS
    public String getTexto(){
        return texto;
    }

    public void setTexto(String unTexto){
        texto = unTexto;
    }

    //METODOS
    public ResultadoProceso realizarAccion() {
        String[] campos = texto.split(";");
        String nombre = campos[0];
        String palabras = campos[1];
        String[] palabrasABuscar = palabras.split(" ");
        return this.modificarLocalesComerciales(nombre,palabrasABuscar);

    }

    public ResultadoProceso modificarLocalesComerciales(String nombre,  String[] unasPalabras) {
        ResultadoProceso resultadoProceso = new ResultadoProceso();
        resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
        int localesAfectados = repositorioPOIs.cantidadDeLocalesModificados(nombre,unasPalabras);
        resultadoProceso.setCantElementosAfectados(localesAfectados);
        if(localesAfectados == 0) { resultadoProceso.setResultadoDelProceso(false); }
        else { resultadoProceso.setResultadoDelProceso(true); }
        return resultadoProceso;

    }

}
