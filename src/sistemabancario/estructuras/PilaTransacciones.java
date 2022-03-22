/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemabancario.estructuras;

import sistemabancario.clasesfundamentales.Cliente;
import sistemabancario.clasesfundamentales.TiempoActual;
import sistemabancario.clasesfundamentales.Transaccion;

/* Esta clase representa a una pila de transacciones. Según el enunciado, se
debe mantener una lista de todas las transacciones realizadas, las cuales se
ordenan de ultimo a primero. Esto se puede lograr almacenando todas las tran
sacciones en una pila.

Se diferencia en una cola en que se inserta y remueve siempre por la cabeza.*/
public class PilaTransacciones {
    NodoTransaccion tope;
    int conteo;
    
    public PilaTransacciones(){
        tope = null;
        conteo = 0;
    }
    
    public boolean esVacia() {
        return tope == null;
    }
    
    public int getNumeroNodos(){
        return conteo;
    }
    
    public void vaciar(){
        tope = null;
        conteo = 0;
    }
    
    public NodoTransaccion vistazo(){
        return tope;
    }
    
    public void apilar(Transaccion transaccion) {
        NodoTransaccion nodo = new NodoTransaccion(transaccion);
        if (tope==null) {
            tope = nodo;
            conteo++;
        } else {
            NodoTransaccion nodoAux = tope;
            nodo.setSiguiente(nodoAux);
            tope = nodo;
            conteo++;
        }
    }
    
    public NodoTransaccion desapilar(){
        if(esVacia()){
            return null;
        } else {
            NodoTransaccion retornable = tope;
            tope = tope.getSiguiente();
            conteo--;
            return retornable;
        }
    }
    
    //Pruebas
    
    public static void main(String[] args) {
        TiempoActual tiempo = new TiempoActual(8);
        Cliente cl1 = new Cliente("21412412","Antonio","Hernandez","Gonzalez","Estañol",20);
        Cliente cl2 = new Cliente("215675412","Ulysses","Noremias","Stonecrest","Evar",20);
        Cliente cl3 = new Cliente("289568242","Clarice","Doremia","Pathos","Stonecrest",20);
        Cliente cl4 = new Cliente("2734512","Adrien","Vargas","Lancaster","Pathos",20);
        
        Transaccion tr1 = new Transaccion(cl1,tiempo,'A');
        tiempo.incrementarTiempo(5);
        Transaccion tr2 = new Transaccion(cl1,tiempo,'C');
        tiempo.incrementarTiempo(1,30);
        Transaccion tr3 = new Transaccion(cl2,tiempo,'R');
        tiempo.incrementarTiempo(4);
        Transaccion tr4 = new Transaccion(cl3,tiempo,'D');
        tiempo.incrementarTiempo(3);
        Transaccion tr5 = new Transaccion(cl4,tiempo,'P');
        tiempo.incrementarTiempo(2);
        
        PilaTransacciones pila = new PilaTransacciones();
        
        pila.apilar(tr1);
        pila.apilar(tr2);
        pila.apilar(tr3);
        pila.apilar(tr4);
        pila.apilar(tr5);
        
        System.out.println("Orden de entrada:"+"\n");
        
        System.out.println(tr1+"\n");
        System.out.println(tr2+"\n");
        System.out.println(tr3+"\n");
        System.out.println(tr4+"\n");
        System.out.println(tr5+"\n");
        
        System.out.println("Orden de salida:"+"\n");
        
        while(!pila.esVacia()){
            System.out.println(pila.desapilar().getTransaccion());
            System.out.println("");
        }
    }
}
