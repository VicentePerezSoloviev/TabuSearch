package buscatabu;

import java.util.ArrayList;

public class Distancias {
    
    ArrayList<ArrayList<Integer>> matriz;
    
    public Distancias (ArrayList<ArrayList<Integer>> matriz){
        this.matriz = matriz;
    }
    
    public int CalculaDistancia (ArrayList <Integer> array) {
        int distanciaAcumulada = 0;
        //columna de ciudad 0 a 99 y filas de columna de ciudad 1 a 100
        
        /*siempre se empieza del 0. Primero debo sumar
        del 0 al primero y al final, del ultimo al 0
        */
        
        for (int i=0; i<99; i++){
            if (i==0) distanciaAcumulada = Distancia (0, array.get(0)); //distancia de 0 a primera ciudad
            else distanciaAcumulada += Distancia (array.get(i-1), array.get(i));
        }
        distanciaAcumulada += Distancia(array.get(98), 0);
        
        return distanciaAcumulada;
    }
    
    private int Distancia (int a, int b) {
        int sol;
        
        if (a>b) {
            sol = matriz.get(a-1).get(b);
        }
        else if (b>a) {
            sol = matriz.get(b-1).get(a);
        }
        else{
            System.out.println("ERROR. Distancia entre misma ciudad");
            sol=0;
        }
        return sol;
    }
}
