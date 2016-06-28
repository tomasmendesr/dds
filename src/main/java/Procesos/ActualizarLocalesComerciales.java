package Procesos;

import Master.RepositorioPOIs;

public class ActualizarLocalesComerciales extends Tareas{

    //ATRIBUTOS
    private static RepositorioPOIs repositorioPOIs;

    //Constructor
    public ActualizarLocalesComerciales(RepositorioPOIs repositorioPOIs){
        super();

    }

    //Metodos
    public void actualizarLocalesComerciales(String unTexto) {
        String[] campos = unTexto.split(";");
        String nombre = campos[0];
        String palabras = campos[1];
        String[] palabrasABuscar = palabras.split(" ");
        repositorioPOIs.buscarPorNombrePOI(nombre,palabrasABuscar);

    }



}
