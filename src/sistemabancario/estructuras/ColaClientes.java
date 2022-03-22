/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.estructuras;

import sistemabancario.clasesfundamentales.Cliente;

/* Esta estructura representa a la cola de clientes que esperan para ser
atendidos en una Taquilla de Banesco.*/
public class ColaClientes {

    NodoCliente cabeza;
    int conteo;
    int conteoPrioridad;

    public ColaClientes() {
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

    public void encolar(Cliente cliente, boolean prioridad) {
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

    public NodoCliente desencolar() {
        if (esVacia()) {
            return null;
        } else {
            NodoCliente retornable = cabeza;
            cabeza = cabeza.getSiguiente();
            conteo--;
            if (retornable.getPrioridad() == true) {
                conteoPrioridad--;
            }
            return retornable;
        }
    }

    ;
    
    public NodoCliente desencolarConPrioridad() {
        if (esVacia()) {
            return null;
        }

        if (!hayPrioridad()) {
            return null;
        }
        
        int temporalConteo = conteo;
        int temporalConteo2 = conteoPrioridad;
        
        ColaClientes colaAuxiliar = new ColaClientes();
        NodoCliente retornable = null;
        NodoCliente nodoAuxiliar;
        boolean encontrado = false;

        while (!esVacia() && !encontrado) {
            nodoAuxiliar = vistazo();
            if (nodoAuxiliar.getPrioridad() == true) {
                retornable = nodoAuxiliar;
                desencolar();
                encontrado = true;
            } else {
                colaAuxiliar.encolar(nodoAuxiliar.getCliente(), nodoAuxiliar.getPrioridad());
                desencolar();
            }
        }
        
        if (!esVacia()) {
            while(!esVacia()) {
                nodoAuxiliar = desencolar();
                colaAuxiliar.encolar(nodoAuxiliar.getCliente(), nodoAuxiliar.getPrioridad());
            } 
        }
        
        cabeza = colaAuxiliar.cabeza;
        conteo = temporalConteo;
        conteoPrioridad = temporalConteo2;
        conteoPrioridad--;
        conteo--;
        return retornable;
    }
    
    //Testing
    
    public static void main(String[] args) {
        Cliente cl1 = new Cliente("21412412","Antonio","Hernandez","Gonzalez","Estañol",20);
        Cliente cl2 = new Cliente("215675412","Ulysses","Noremias","Stonecrest","Evar",20);
        Cliente cl3 = new Cliente("289568242","Clarice","Doremia","Pathos","Stonecrest",20);
        Cliente cl4 = new Cliente("2734512","Adrien","Vargas","Lancaster","Pathos",20);
        Cliente cl5 = new Cliente("21111412","Leonhardt","Stoneshard","Baum","Stroncium",20);
        Cliente cl6 = new Cliente("21469992","Bertram","Wolfgang","Prescott","Ritter",20);
        Cliente cl7 = new Cliente("88882","Levi","Ackermann","Wolt","Velazquez",20);
        Cliente cl8 = new Cliente("12471232","Miguel","Angel","Ordaz","Montaner",20);
        
        ColaClientes cola = new ColaClientes();
        cola.encolar(cl1,false);
        cola.encolar(cl2,true);
        cola.encolar(cl3,false);
        cola.encolar(cl4,true);
        cola.encolar(cl5,false);
        cola.encolar(cl6,false);
        cola.encolar(cl7,true);
        cola.encolar(cl8,false);
        
        Cliente clienteConPrioridad1 = cola.desencolarConPrioridad().getCliente();
        Cliente clienteConPrioridad2 = cola.desencolarConPrioridad().getCliente();
        Cliente clienteConPrioridad3 = cola.desencolarConPrioridad().getCliente();
        System.out.println("");
        System.out.println(clienteConPrioridad1);
        System.out.println(clienteConPrioridad2);
        System.out.println(clienteConPrioridad3);
        System.out.println("");
    }

}
