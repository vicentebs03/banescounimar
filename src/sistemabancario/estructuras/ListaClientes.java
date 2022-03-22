/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemabancario.estructuras;

import sistemabancario.clasesfundamentales.Cliente;

/* Una lista de clientes para propositos de revision de entrada. */
public class ListaClientes {
    NodoCliente cabeza;
    int conteo;
    int conteoPrioridad;

    public ListaClientes() {
        cabeza = null;
        conteo = 0;
        conteoPrioridad = 0;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public int cantidadNodos() {
        return conteo;
    }

    public int cantidadNodosPrioridad() {
        return conteoPrioridad;
    }

    public NodoCliente vistazo() {
        return cabeza;
    }

    public boolean hayPrioridad() {
        return conteoPrioridad > 0;
    }

    public void vaciar() {
        cabeza = null;
        conteo = 0;
        conteoPrioridad = 0;
    }
    
    public void agregarAlFinal(Cliente cliente, boolean prioridad) {
        NodoCliente nodo = new NodoCliente(cliente, prioridad);
        if (esVacia()) {
            cabeza = nodo;
            conteo++;
        } else {
            NodoCliente auxiliar = cabeza;
            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            auxiliar.setSiguiente(nodo);
            conteo++;
            if (prioridad == true) {
                conteoPrioridad++;
            }
        }
    }
    
    public Cliente buscar(String cedula){
        if(esVacia()) {
            return null;
        }       
        NodoCliente auxiliar;
        auxiliar = cabeza;
        while(auxiliar != null) {
            if(auxiliar.getCliente().getCedula().equals(cedula)){
                return auxiliar.getCliente();
            }
            auxiliar = auxiliar.getSiguiente();
        }       
        return null;
    }
}
