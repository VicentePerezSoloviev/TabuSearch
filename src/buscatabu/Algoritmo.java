package buscatabu;

import java.io.IOException;
import java.util.ArrayList;


public class Algoritmo {
    private final int condicionParada = 10000;
    private final int N = 100;
    
    private final ListaTabu listaTabu;
    private ArrayList <Integer> MejorSolucion;
    private ArrayList <Integer> PosibleSolucion;
    private ArrayList <Integer> MejorSolucionCandidata;
    
    private int costeActual;
    private int posibleCoste;
    private int costeCandidato;
    private int iteracionMejor;
    
    private final Distancias distan;
    private final EscribirArchivo escribir;
    
    public Algoritmo(ArrayList<ArrayList<Integer>> matriz, ArrayList<Integer> SolIni) throws IOException {
        this.MejorSolucion = SolIni;
        this.MejorSolucionCandidata = new ArrayList <Integer>();
        this.distan = new Distancias (matriz);
        this.escribir = new EscribirArchivo ();
        this.listaTabu = new ListaTabu (this.N);
    }
    
    private int mayor (int a, int b){
        if (a>b) return a;
        else return b;
    }
    
    public void BuscaTabu () throws IOException {
        ListaTabu imprimir;
        int numeroIteracionesSinMejora = 0;
        
        this.costeActual = this.distan.CalculaDistancia(MejorSolucion);
        this.costeCandidato = this.costeActual;
        int costeIni = costeActual;
        
        this.MejorSolucionCandidata = new ArrayList <Integer> (MejorSolucion);
        this.PosibleSolucion = new ArrayList <Integer> (MejorSolucionCandidata);
        
        Integer cambio[] = new Integer[2];
        
        boolean flag = false;
        
        ArrayList <Integer> copia = new ArrayList <Integer> (MejorSolucionCandidata);
        escribir.escribirTrazaInicial(MejorSolucion, costeIni);
        for (int k=0; k<condicionParada; k++) {
            
            /*Este bucle es para buscar la mejor solucion de la iteracion*/
            for (int i=0; i<this.MejorSolucion.size(); i++) {
                for (int j=i+1; j<this.MejorSolucion.size(); j++) {
                    
                    if (i==0 && j==1) costeCandidato = costeIni;
                    if (!this.listaTabu.existe(i, j)) {     //si no existe permutacion en lista Tabu
                        swap(i,j);
                        posibleCoste = this.distan.CalculaDistancia(PosibleSolucion);
                        
                        if (posibleCoste < costeCandidato){ //es mejor que la colucion hasta el momento
                            this.costeCandidato = posibleCoste;
                            this.MejorSolucionCandidata = new ArrayList <Integer> (PosibleSolucion);
                            cambio[0] = i;
                            cambio[1] = j;
                        }
                        else if (posibleCoste == costeCandidato){
                            if (mayor(cambio[0],cambio[1]) > mayor(i,j)){
                                this.costeCandidato = posibleCoste;
                                this.MejorSolucionCandidata = new ArrayList <Integer> (PosibleSolucion);
                                cambio[0] = i;
                                cambio[1] = j;
                            }
                        }
                        
                    }
                    this.PosibleSolucion = new ArrayList <Integer> (copia); //deshago el cambio para seguir explorando
                }
            }
            
            if (costeCandidato < costeActual){
                this.MejorSolucion = new ArrayList <Integer> (MejorSolucionCandidata);
                this.costeActual = costeCandidato;
                this.iteracionMejor=(k+1);
                this.listaTabu.insertar(cambio);
                imprimir = new ListaTabu (this.listaTabu);
                numeroIteracionesSinMejora = 0;
                copia = new ArrayList <Integer> (MejorSolucionCandidata);
                this.PosibleSolucion = new ArrayList <Integer> (copia);
            }
            else {
                if (numeroIteracionesSinMejora == 99) {
                    this.listaTabu.insertar(cambio);
                    imprimir = new ListaTabu (this.listaTabu);
                    this.listaTabu.vaciar();
                    numeroIteracionesSinMejora = 0;
                    flag = true;
                    copia = new ArrayList <Integer> (MejorSolucion);
                    this.PosibleSolucion = new ArrayList <Integer> (copia);
                    
                }
                else{
                    this.listaTabu.insertar(cambio);
                    imprimir = new ListaTabu (this.listaTabu);
                    numeroIteracionesSinMejora ++;
                    copia = new ArrayList <Integer> (MejorSolucionCandidata);
                    this.PosibleSolucion = new ArrayList <Integer> (copia);
                }
            }
            /*Los siguientes ajustes son para imprimir correctamente*/
            
            //System.out.println("Iteracion" + k);
            if (flag) numeroIteracionesSinMejora=100;
            escribir.escribirTraza(flag, cambio, k+1, MejorSolucionCandidata, costeCandidato, numeroIteracionesSinMejora, imprimir);
            if (flag) {
                this.MejorSolucionCandidata = new ArrayList (copia);
                numeroIteracionesSinMejora =0;
            }
            flag = false;
            
            cambio = new Integer[2];    //reseteamos
        }
        
        escribir.escribirTrazaFinal(this.iteracionMejor, MejorSolucion, costeActual);
    }
    
    //hace el swap en PosibleSolucion
    public void swap (int posicion1, int posicion2) {
        int aux = this.PosibleSolucion.get(posicion1);
        this.PosibleSolucion.set(posicion1, this.PosibleSolucion.get(posicion2));
        this.PosibleSolucion.set(posicion2, aux);
    }
    
}
