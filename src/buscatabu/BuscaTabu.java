package buscatabu;

import java.io.IOException;
import java.util.ArrayList;

public class BuscaTabu {

    public static void main(String[] args) throws IOException{
        LecturaArchivo leer = new LecturaArchivo ();
       
        ArrayList<ArrayList<Integer>> matriz = leer.LeerArchivoDistancias(args[0]); //leer primer string -> matriz
        Distancias dist = new Distancias (matriz);
        ArrayList<Integer> SolIni = null;
        
        if (args.length == 1) {
            SolIni = leer.generarAleatorio();
        }
        else if (args.length == 2) {
            SolIni = leer.LeerArchivoSolucionInicial(args[1]); //comandos
        }
        
        Algoritmo algoritmo = algoritmo = new Algoritmo (matriz, SolIni);
        algoritmo.BuscaTabu();
        
    }
    
}
