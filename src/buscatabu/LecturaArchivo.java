package buscatabu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LecturaArchivo {
    
    private ArrayList <ArrayList <Integer>> matriz;
    private final ArrayList <Integer> solucionInicial = new ArrayList <> ();
    private String ArchivoDistancias;
    private String ArchivoAleatorioInicial;

    
    public ArrayList<ArrayList<Integer>> LeerArchivoDistancias (String archivoDistancias) throws FileNotFoundException, IOException {
        this.ArchivoDistancias = archivoDistancias;
        FileReader f = new FileReader (this.ArchivoDistancias);
        BufferedReader b = new BufferedReader (f);
        
        StringTokenizer s;
        String linea, dist;
        //int fila=0;
        
        this.matriz = new ArrayList <> ();
        ArrayList <Integer> aux;
        
        /*Se esta guardando el fichero por filas. 
        Para identificar las ciudades. Las columnas 0-99 y filas de 1 a 100 
        matriz.get(98).get(98) -> distancia ciudad 99-100 -> esquina */
        
        while ((linea = b.readLine()) != null) {
            aux = new ArrayList <> ();      //array auxiliar que se añade a la matriz
            s = new StringTokenizer (linea);
            while (s.hasMoreTokens()) {         //recorro la linea e inserto en la fila de la matriz
                dist = s.nextToken();           //cojo todos los valores de la linea
                aux.add(Integer.valueOf(dist));
            }
            matriz.add(aux);        //añado el array a la matriz
            //fila ++;
        }
        return this.matriz;
    }
    
    public ArrayList<Integer> LeerArchivoSolucionInicial (String archivoAleatorio) throws FileNotFoundException, IOException {
        this.ArchivoAleatorioInicial = archivoAleatorio;
        FileReader f = new FileReader (this.ArchivoAleatorioInicial);
        BufferedReader b = new BufferedReader (f);
        
        String linea;
        int x;
        while ((linea = b.readLine()) != null) {        //leo todos los valores
            x = (int) (1 + Math.floor(Double.valueOf(linea) * 99));
            //if (this.solucionInicial.contains(x)) {         //en caso de valor repetido
                while (this.solucionInicial.contains(x)){    
                    /*no puede ser 0, ya que se sale y se llega ahi, asi que no puede pasar por ahi
                    mas veces */
                    x ++;
                    if(x==100) x=1;
                }
            //}
            //en caso de que no esté insertado todavia
            this.solucionInicial.add(x);
        }
        
        return this.solucionInicial;
    }
    
    public ArrayList <Integer> generarAleatorio () {
        int x=0;
        ArrayList <Integer> array = new ArrayList <Integer> (99);
        
        while (array.size()<99){
            x = (int) (1 + 99*Math.random()); // Esto da valores entre 1.0 y 100.0 excluido el 100.0
            if (!array.contains(x)){
                array.add(x);
            }
        }
        
        return array;
    }
}
