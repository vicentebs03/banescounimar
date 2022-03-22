/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemabancario.estructuras;

import sistemabancario.clasesfundamentales.Cliente;

/* Estructura que representa a un cliente en la cola del banco Banesco. 
Cada cliente tiene cierta prioridad, la cual será tomada en cuanta cada 4
clientes atendidos en taquilla.

Como no se diferencian niveles de prioridad en la cola, lo implementamos como un booleano.
Es decir, un cliente puede ser de prioridad, o no.*/

public class NodoCliente {
    Cliente cliente;
    NodoCliente siguiente;
    boolean prioridad;
    
    //Métodos constructores.
        
        //Nodo sin cliente apuntando a nulo
        
        public NodoCliente(){
            cliente = null;
            siguiente = null;
            prioridad = false;
        }
        
        //Nodo sin clienteapuntando a otro nodo.
        
        public NodoCliente(NodoCliente sig, boolean prioridad){
            this.cliente = null;
            this.siguiente = sig;
            this.prioridad = prioridad;
        }
        
        //Nodo con cliente apuntando nulo.
        
        public NodoCliente(Cliente cli, boolean prioridad){
            this.cliente = cli;
            this.siguiente = null;
            this.prioridad = prioridad;
        }
        
        //Nodo con cliente apuntando a otro nodo.
        
        public NodoCliente(Cliente cli, NodoCliente sig, boolean prioridad){
            this.cliente = cli;
            this.siguiente = sig;
            this.prioridad = prioridad;
        }
        
    //Métodos accesores (getters)
        
        public Cliente getCliente(){
            return this.cliente;
        }
        
        public NodoCliente getSiguiente(){
            return this.siguiente;
        }
        
        public boolean getPrioridad() {
            return this.prioridad;
        }
        
    //Métodos mutadores (setters)
        
        public void setCliente(Cliente cli){
            this.cliente = cli;
        }
        
        public void setSiguiente(NodoCliente sig){
            this.siguiente = sig;
        }
        
        public void setPrioridad(boolean prioridad){
            this.prioridad = prioridad;
        }
}
