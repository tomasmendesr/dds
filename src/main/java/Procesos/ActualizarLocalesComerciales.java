package Procesos;

import Master.RepositorioPOIs;
import java.time.LocalDateTime;


public class ActualizarLocalesComerciales extends Proceso{

    //ATRIBUTOS
    private RepositorioPOIs repositorioPOIs;
    private String texto;

    //CONSTRUCTOR
    public ActualizarLocalesComerciales(RepositorioPOIs unRepositorioPOIs){
        super();
        repositorioPOIs = unRepositorioPOIs;
    }

    //GETTERS Y SETTERS
    public String getTexto(){
        return texto;
    }

    public void setTexto(String unTexto){
        texto = unTexto;
    }

    //METODOS
    public ResultadoProceso realizarProceso() {
        String[] campos = texto.split(";");
        String nombre = campos[0];
        String palabras = campos[1];
        String[] palabrasABuscar = palabras.split(" ");
        return this.modificarLocalesComerciales(nombre,palabrasABuscar);
    }

    public ResultadoProceso modificarLocalesComerciales(String nombre,  String[] unasPalabras) {
        ResultadoProceso resultadoProceso = new ResultadoProceso();
        resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
        Integer localesAfectados = repositorioPOIs.cantidadDeLocalesModificados(nombre,unasPalabras);
        resultadoProceso.setCantElementosAfectados(localesAfectados);
        if(localesAfectados.equals(0)) { resultadoProceso.setResultadoDelProceso(false); }
        else { resultadoProceso.setResultadoDelProceso(true); }
        return resultadoProceso;

    }

}
