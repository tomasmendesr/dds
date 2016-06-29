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
    private List<LocalComercial> localesComerciales;
    private String texto;

    //CONSTRUCTOR
    public ActualizarLocalesComerciales(){
        super();
        localesComerciales = new ArrayList<LocalComercial>();

    }

    //GETTERS Y SETTERS
    public List<LocalComercial> getTodasLasTerminales() {
        return localesComerciales;
    }

    public void setTodasLasTerminales(List<LocalComercial> localesComerciales) {
        this.localesComerciales = localesComerciales;
    }

    public String getTexto(){
        return texto;
    }

    public void setTexto(String unTexto){
        texto = unTexto;
    }

    //METODOS
    public void agregarLocalComercial(LocalComercial unLocal) {
        localesComerciales.add(unLocal);
    }

    public ResultadoProceso realizarAccion() {
        String[] campos = texto.split(";");
        String nombre = campos[0];
        String palabras = campos[1];
        String[] palabrasABuscar = palabras.split(" ");
        return this.buscarPorNombreLocalComercial(nombre,palabrasABuscar);

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
