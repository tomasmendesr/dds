package Procesos;

import Master.POI;
import Master.RepositorioPOIs;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ActualizarLocalesComerciales extends Proceso{

    //ATRIBUTOS
    private RepositorioPOIs repositorioPOIs;
    private String texto;

    //CONSTRUCTOR
    public ActualizarLocalesComerciales(RepositorioPOIs unRepositorioPOIs, String unaRuta) throws IOException {
        super();
        repositorioPOIs = unRepositorioPOIs;
        this.leerArchivo(unaRuta);
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
        return this.buscarLocalesComercialesAModificar(nombre,palabrasABuscar);
    }

    public ResultadoProceso buscarLocalesComercialesAModificar(String nombre,  String[] unasPalabras) {
        ResultadoProceso resultadoProceso = new ResultadoProceso();
        resultadoProceso.setMomentoDeEjecucion(LocalDateTime.now());
        List<POI> localesAModificar = new ArrayList<POI>();
        localesAModificar = repositorioPOIs.devolverLocalesComercialesQueCumplenRequisitos(nombre,unasPalabras);
        this.modificarLocalesComerciales(localesAModificar,unasPalabras);
        Integer localesAfectados = localesAModificar.size();
        resultadoProceso.setCantElementosAfectados(localesAfectados);
        if(localesAfectados.equals(0)) { this.falle(); }
        else { resultadoProceso.setResultadoDelProceso(true); }
        return resultadoProceso;

    }

    public void modificarLocalesComerciales(List<POI> localesAModificar, String [] unasPalabras){
        repositorioPOIs.eliminarListaDePOIs(localesAModificar);
        localesAModificar.stream().forEach(localComercial->localComercial.actualizarPalabrasClaves(unasPalabras));
        repositorioPOIs.agregarListaDePOIs(localesAModificar);
    }


    public void leerArchivo(String unaRuta) throws IOException {
        try {
            FileInputStream fstream = new FileInputStream(unaRuta);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String linea;

            while ((linea = buffer.readLine()) != null) { System.out.println(linea); }
            this.setTexto(linea);
            entrada.close();
        }
        catch (Exception e) { System.err.println("Ocurrio un error: " + e.getMessage()); }
    }

}
