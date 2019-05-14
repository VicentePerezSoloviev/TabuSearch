package buscatabu;

import java.util.ArrayList;

public class ListaTabu {
    private final int N;
    private ArrayList <Integer[]> permutacion;
    
    public ListaTabu (int N) {
        this.N = N;
        this.permutacion = new ArrayList <Integer[]> (N);
    }
    
    public ListaTabu (ListaTabu l) {
        this.permutacion = l.reto();
        this.N = l.tamano();
    }
    
    public void insertar (Integer[] a) {
        if(!existe(a)){
            if (this.permutacion.size() == N) {       //borro el primer elemento (el mas viejo) para hacer sitio
                permutacion.remove(0);
            }
            if (a.length == 2 && a[0]>a[1]) {
                permutacion.add(a);
            } 
            else if (a.length == 2 && a[0]<a[1]) {
                Integer b[]= new Integer [2];
                b[0]= a[1];
                b[1] = a[0];
                permutacion.add(b);
            }
            else System.out.println("ERROR. ListaTabu.java. Intenta insertar permutacion diferencte de dos elementos || a[1]>a[0]");
        }
        else {
            System.out.println("Ya existe");
        }
    }
    
    public void eliminar (Integer[] a) {
        if (this.permutacion.contains(a)){
            this.permutacion.remove(a);
        }
    }
    
    public boolean existe (Integer[] a) {
        boolean flag = false;
        for (int i=0; i<permutacion.size(); i++) {
            if (permutacion.get(i)[0] == a[0] && permutacion.get(i)[1] == a[1]){
                flag = true;
            }
        }
        return flag;
    }
    
    public boolean existe (int a, int b) {
        boolean flag = false;
        for (int i=0; i<this.permutacion.size(); i++){
            if (this.permutacion.get(i)[0] == a && this.permutacion.get(i)[1] == b){
                flag = true;
            }
            else if (this.permutacion.get(i)[0] == b && this.permutacion.get(i)[1] == a){
                flag = true;
            }
        }
        return flag;
    }
    
    public void vaciar () {
        this.permutacion = new ArrayList <Integer[]> (N);
    }

    
    public void ToString() {
        for (int i=0; i<this.permutacion.size(); i++){
            System.out.println(this.permutacion.get(i)[0] + " " + this.permutacion.get(i)[1]);
        }
    }
    
    public int tamano(){
        return this.permutacion.size();
    }
    
    public ArrayList <Integer[]> reto (){
        return this.permutacion;
    }
}

