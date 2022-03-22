/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.clasesfundamentales;

import sistemabancario.estructuras.ColaClientes;
import sistemabancario.estructuras.PilaTransacciones;

/* Esta clase representa a la unica taquilla disponible según el enunciado.

Cada taquilla tiene un espacio para atender a clientes, una cola de clientes a los que atienda,
una pila de transacciones realizadas, y lleva el conteo del horario global.*/
public class Taquilla {

    Cliente atendiendo;
    ColaClientes cola;
    ColaClientes pendientes;
    PilaTransacciones transaccionesEfectuadas;
    TiempoActual hora;
    boolean jornadaTerminada;

    public Taquilla(ColaClientes clientes) {
        atendiendo = null;
        cola = clientes;
        hora = new TiempoActual(8);
        transaccionesEfectuadas = new PilaTransacciones();
        jornadaTerminada = false;
        pendientes = new ColaClientes();
    }

    public boolean hayClientesEnCola() {
        return !cola.esVacia();
    }
    public boolean haTerminadoDia() {
        return jornadaTerminada;
    }
    public void terminarJornada() {
        jornadaTerminada = true;
    }
    public void rotarCliente() {
        atendiendo = cola.desencolar().getCliente();
    }
    
    public void rotarConPrioridad(){
        atendiendo = cola.desencolarConPrioridad().getCliente();
    }
    
    public boolean hayPendientes(){
        return !pendientes.esVacia();
    }
    
    public ColaClientes getPendientes() {
        return pendientes;
    }
    
    public PilaTransacciones getTransacciones(){
        return transaccionesEfectuadas;
    }
    public int duracionSolicitudMinutos(char solic) {
        int minutos;
        switch (solic) {
            case 'A':
                minutos = 5;
                break;
            case 'R':
                minutos = 4;
                break;
            case 'D':
                minutos = 3;
                break;
            case 'P':
                minutos = 2;
                break;
            case 'C':
                minutos = 1;
                break;
            case '0':
                minutos = 0;
                break;
            default:
                minutos = 0;
                break;
        }
        return minutos;
    }
    public int duracionSolicitudSegundos(char solic) {
        int segundos;

        switch (solic) {

            case 'C':
                segundos = 30;
                break;
            default:
                segundos = 0;
                break;
        }
        return segundos;
    }

    public void atender(){
        boolean hayTiempo = true;
        int conteoPrioridad = 0;
        boolean prioridad;
        while(!haTerminadoDia() && hayClientesEnCola()) {
            if (conteoPrioridad == 4) {
                if(cola.hayPrioridad()) {
                    prioridad = true;
                    rotarConPrioridad();
                    conteoPrioridad = 0;
                    System.out.println("Se han atendido a 4 clientes no prioritarios. Atendiendo a cliente prioritario con cedula " + atendiendo.cedula +" ("+atendiendo.getNombreCompleto()+")");
                } else {
                    prioridad = cola.vistazo().getPrioridad();
                    rotarCliente();
                    conteoPrioridad = 0;
                }
            } else {
                prioridad = cola.vistazo().getPrioridad();
                rotarCliente();
            }
            
            if (prioridad == false) {
                conteoPrioridad++;
            }
            
            while(atendiendo.haySolicitudesPendientes() && hayTiempo) {
                hayTiempo = atenderSolicitudCliente();
            }
            
            if(!hayTiempo) {
                pendientes.encolar(atendiendo, prioridad);
                if(hayClientesEnCola()){
                    encolarPendientes();
                }
            }
        }
    }
    
    public void encolarPendientes(){
        boolean prioridad;
        if(hayClientesEnCola()) {
            while(!cola.esVacia()){
                prioridad = cola.vistazo().getPrioridad();
                pendientes.encolar(cola.desencolar().getCliente(), prioridad);
            }
        }
    }
    
    public boolean atenderSolicitudCliente() {
        char operacion = atendiendo.verSolicitud();
        int minutos = duracionSolicitudMinutos(operacion);
        int segundos = duracionSolicitudSegundos(operacion);
        
        Transaccion tran = new Transaccion(atendiendo, hora, operacion);
        boolean exito = hora.incrementarTiempo(minutos,segundos);
        if (!exito) {
            terminarJornada();
            return false;
        } else {
            boolean prioridadCliente = atendiendo.getDiscapacidad() || atendiendo.getTerceraEdad();
            atendiendo.procesarSolicitud();
            tran.setHoraFinal(hora);
            tran.setPrioridad(prioridadCliente);
            if(prioridadCliente == true) {
                if (atendiendo.getTerceraEdad() == true){
                    tran.setDescripcionPrioridad("Tercera edad");
                } else {
                    tran.setDescripcionPrioridad("Discapacitado/Problemas prioritarios");
                }
            }
            transaccionesEfectuadas.apilar(tran);
            return true;
        }

    }
     //Testing
    public static void main(String[] args) {
        
    }

}
