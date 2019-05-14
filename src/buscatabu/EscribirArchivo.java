package buscatabu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscribirArchivo {
    private final String ArchivoEscritura;
    BufferedWriter out = null;
    int f;
    boolean q;

    public EscribirArchivo() throws FileNotFoundException, IOException {
        this.ArchivoEscritura = "traza_buscaTabu";
        f=1;
        q = false;
    }
    
    public void escribirTrazaInicial (ArrayList<Integer> a, int coste) throws IOException {
        File fichero = new File("traza_vicente.txt");   //lo reiniciamos
        fichero.delete();
        try {   
            out = new BufferedWriter(new FileWriter("traza_vicente.txt", true));   
            out.write("RECORRIDO INICIAL"); 
            out.newLine();
            out.write("\tRECORRIDO: "); 
            for (int i=0; i<a.size(); i++) {
                out.write(a.get(i).toString()+ " "); 
            }
            out.newLine();
            out.write("\tCOSTE (km): " + coste); 
            out.newLine();
            out.newLine();
        } catch (IOException e) {   
            // error processing code   
        } finally {   
            if (out != null) {   
                out.close();   
            }   
        } 
    }
    
    public void escribirTraza (boolean flag, Integer b[], int iteracion, ArrayList<Integer> a, int coste, int it, ListaTabu l) throws IOException {
        try {   
            out = new BufferedWriter(new FileWriter("traza_vicente.txt", true));   
            out.write("ITERACION: " + iteracion); 
            out.newLine();
            
            if (b[0] > b[1]) out.write("\tINTERCAMBIO: (" + b[0] + ", " + b[1] + ")"); 
            else out.write("\tINTERCAMBIO: (" + b[1] + ", " + b[0] + ")"); 
            
            out.newLine();
            out.write("\tRECORRIDO: "); 
            
            for (int i=0; i<a.size(); i++) {
                out.write(a.get(i).toString()+ " "); 
            }
            
            out.newLine();
            out.write("\tCOSTE (km): " + coste); 
            out.newLine();
            
            if (flag) {
                it ++;
                this.f = this.f + 1;
            }
            if (it>100) it--;
            out.write("\tITERACIONES SIN MEJORA: " + it); 
            out.newLine();
            out.write("\tLISTA TABU:");
            ArrayList be = new ArrayList <Integer[]> (l.reto());
            Integer r[];
            
            for (int i=0; i<l.tamano(); i++){
                r = (Integer[]) be.get(i);
                out.newLine();
                out.write("\t" + r[0].toString() + " " + r[1].toString());
            }
            
            out.newLine();
            out.newLine();
            
            if (q){
                out.write("***************"); out.newLine();
                out.write("REINICIO: " + (f-1)); out.newLine();
                out.write("***************");
                out.newLine();
                out.newLine();
                q=false;
            }
            
            if ((it+1)==100){
                q = true;
            }
            
            
        } catch (IOException e) {   
            // error processing code   
        } finally {   
            if (out != null) {   
                out.close();   
            }
        }
    }
    
    public void escribirTrazaFinal (int iteracion, ArrayList<Integer> a, int coste) throws IOException {
        try {   
            out = new BufferedWriter(new FileWriter("traza_vicente.txt", true));
            out.newLine();
            out.write("MEJOR SOLUCION: "); 
            out.newLine();
            out.write("\tRECORRIDO: "); 
            for (int i=0; i<a.size(); i++) {
                out.write(a.get(i).toString()+ " "); 
            }
            out.newLine();
            out.write("\tCOSTE (km): " + coste); 
            out.newLine();
            out.write("\tITERACION: " + iteracion);
            out.newLine();
        } catch (IOException e) {   
            // error processing code   
        } finally {   
            if (out != null) {   
                out.close();   
            }   
        } 
    }
}
