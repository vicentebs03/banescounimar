/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.auxiliares;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.estructuras.*;
import sistemabancario.clasesfundamentales.*;

public class ManejadorEntrada {

    public ManejadorEntrada() {

    }

    public boolean validarEntero(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarPositivo(String entrada) {
        return (Integer.parseInt(entrada) >= 0);
    }

    public ColaClientes prepararClientes(File clientesPendientes, File clientes) {
        ColaClientes cola = new ColaClientes();
        ListaClientes lista = new ListaClientes();
        boolean pendientesVacio = false;
        boolean clientesVacio = false;
        if (clientesPendientes.exists()) {
            System.out.println("Se ha detectado archivo clientes_pendientes.in. Cargando...");
            //Procesamiento de clientes pendientes.
            if (clientesPendientes.length() == 0) {
                System.out.println("El archivo de clientes pendientes existe, pero está vacio.");
                return null;
            }
            try {
                Scanner scanP = new Scanner(clientesPendientes);
                while (scanP.hasNextLine()) {
                    String[] temporal = scanP.nextLine().split(" ");
                    if (temporal.length != 5) {
                        System.out.println("Entrada de cliente pendiente no se ajusta al formato especificado.");
                        return null;
                    } else {
                        if (!validarEntero(temporal[2])) {
                            System.out.println("Campo correspondiente a la edad del cliente pendiente no es un numero.");
                            return null;
                        } else if (!validarPositivo(temporal[2])) {
                            System.out.println("Edad del cliente pendiente es negativa.");
                            return null;
                        }

                        String[] nombreArreglo = temporal[1].split("_");
                        String[] temporal2 = temporal[3].split("-");
                        int edadEntrada = Integer.parseInt(temporal[2]);
                        Cliente cliente;

                        switch (nombreArreglo.length) {
                            case 4:
                                cliente = new Cliente(temporal[0], nombreArreglo[0], nombreArreglo[1], nombreArreglo[2], nombreArreglo[3], edadEntrada);
                                break;
                            case 3:
                                cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], nombreArreglo[2], edadEntrada);
                                break;
                            case 2:
                                cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], "", edadEntrada);
                                break;
                            case 1:
                                cliente = new Cliente(temporal[0], nombreArreglo[0], "", "", "", edadEntrada);
                                break;
                            default:
                                cliente = null;
                                break;
                        }

                        if (cliente == null) {
                            System.out.println("Error de formato en cliente pendiente.");
                            return null;
                        } else {
                            Cliente clienteBuscado = lista.buscar(cliente.getCedula());
                            if (clienteBuscado != null) {
                                if (!clienteBuscado.getPrimerNombre().equals(cliente.getPrimerNombre()) || !clienteBuscado.getSegundoNombre().equals(cliente.getSegundoNombre()) || !clienteBuscado.getPrimerApellido().equals(cliente.getPrimerApellido()) || !clienteBuscado.getSegundoApellido().equals(cliente.getSegundoApellido()) || clienteBuscado.getEdad() != cliente.getEdad()) {
                                    System.out.println("Cliente pendiente en archivo se repite de una forma no consistente con respecto a su edad o nombres");
                                    return null;
                                }
                            }
                            for (String sol : temporal2) {
                                char solicitud = sol.charAt(0);
                                switch (solicitud) {
                                    case 'A':
                                        cliente.encolarSolicitud(solicitud);
                                        break;
                                    case 'R':
                                        cliente.encolarSolicitud(solicitud);
                                        break;
                                    case 'D':
                                        cliente.encolarSolicitud(solicitud);
                                        break;
                                    case 'P':
                                        cliente.encolarSolicitud(solicitud);
                                        break;
                                    case 'C':
                                        cliente.encolarSolicitud(solicitud);
                                        break;
                                    default:
                                        System.out.println("Operación no soportada en archivo de clientes pendientes.");
                                        return null;
                                }
                            }
                        }

                        if (temporal[4].equals("1") || cliente.getTerceraEdad()==true) {
                            if(temporal[4].equals("1")){
                                cliente.setDiscapacidad(true);
                            }
                            cola.encolar(cliente, true);
                            lista.agregarAlFinal(cliente, true);
                        } else {
                            cola.encolar(cliente, false);
                            lista.agregarAlFinal(cliente, false);
                        }

                    }

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Error: El archivo de clientes pendientes no existe y se intentó leer.");
            }
            System.out.println("Clientes pendientes cargados. Borrando archivo de clientes pendientes...");
            boolean exitoBorrar = clientesPendientes.delete();
            if (exitoBorrar) {
                System.out.println("Archivo de clientes pendientes borrado satisfactoriamente.");
            } else {
                System.out.println("No se ha podido borrar el archivo de clientes pendientes.");
            }
            //Procesamiento de clientes. Ya se han cargado clientes de los pendientes.
            if (!clientes.exists()) {
                System.out.println("El archivo de clientes no existe. Se han cargado solo los clientes pendientes.");
            } else if (clientes.length() == 0) {
                System.out.println("El archivo de clientes existe, pero está vacío. No se han cargado clientes de este archivo.");
            } else {
                System.out.println("Cargando clientes de archivo clientes.in");
                try {
                    Scanner scan = new Scanner(clientes);
                    while (scan.hasNextLine()) {
                        String[] temporal = scan.nextLine().split(" ");
                        if (temporal.length != 5) {
                            System.out.println("Entrada no se ajusta al formato especificado.");
                            return null;
                        } else {
                            if (!validarEntero(temporal[2])) {
                                System.out.println("Campo correspondiente a la edad no es un numero.");
                                return null;
                            } else if (!validarPositivo(temporal[2])) {
                                System.out.println("Edad es negativa.");
                                return null;
                            }

                            String[] nombreArreglo = temporal[1].split("_");
                            String[] temporal2 = temporal[3].split("-");
                            int edadEntrada = Integer.parseInt(temporal[2]);
                            Cliente cliente;

                            switch (nombreArreglo.length) {
                                case 4:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], nombreArreglo[1], nombreArreglo[2], nombreArreglo[3], edadEntrada);
                                    break;
                                case 3:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], nombreArreglo[2], edadEntrada);
                                    break;
                                case 2:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], "", edadEntrada);
                                    break;
                                case 1:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", "", "", edadEntrada);
                                    break;
                                default:
                                    cliente = null;
                                    break;
                            }
                            if (cliente == null) {
                                System.out.println("Error de formato.");
                                return null;
                            } else {
                                Cliente clienteBuscado = lista.buscar(cliente.getCedula());
                                if (clienteBuscado != null) {
                                    if (!clienteBuscado.getPrimerNombre().equals(cliente.getPrimerNombre()) || !clienteBuscado.getSegundoNombre().equals(cliente.getSegundoNombre()) || !clienteBuscado.getPrimerApellido().equals(cliente.getPrimerApellido()) || !clienteBuscado.getSegundoApellido().equals(cliente.getSegundoApellido()) || clienteBuscado.getEdad() != cliente.getEdad()) {
                                        System.out.println("Cliente del archivo de entrada se repite de una forma no consistente con respecto a su edad o nombres");
                                        return null;
                                    }
                                }
                                for (String sol : temporal2) {
                                    char solicitud = sol.charAt(0);
                                    switch (solicitud) {
                                        case 'A':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'R':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'D':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'P':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'C':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        default:
                                            System.out.println("Operación no soportada en archivo.");
                                            return null;
                                    }
                                }
                            }
                            if (temporal[4].equals("1") || cliente.getTerceraEdad()==true) {
                                if(temporal[4].equals("1")){
                                    cliente.setDiscapacidad(true);
                                }
                                cola.encolar(cliente, true);
                                lista.agregarAlFinal(cliente, true);
                            } else {
                                cola.encolar(cliente, false);
                                lista.agregarAlFinal(cliente, false);
                            }
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: Archivo de clientes no existe.");
                    return null;
                }
            }
        } else {
            //Procesamiento de clientes normales cuando no hay clientes pendientes.
            if(!clientes.exists()){
                System.out.println("No hay clientes pendientes, y no se ha detectado el archivo de clientes.");
                return null;
            } else  if (clientes.length() == 0) {
                System.out.println("No hay clientes pendientes. El archivo de clientes existe, pero está vacío.");
                return null;
            } else {
                try {
                    Scanner scan = new Scanner(clientes);
                    while (scan.hasNextLine()) {
                        String[] temporal = scan.nextLine().split(" ");
                        if (temporal.length != 5) {
                            System.out.println("Entrada no se ajusta al formato especificado.");
                            return null;
                        } else {
                            if (!validarEntero(temporal[2])) {
                                System.out.println("Campo correspondiente a la edad no es un numero.");
                                return null;
                            } else if (!validarPositivo(temporal[2])) {
                                System.out.println("Edad es negativa.");
                                return null;
                            }

                            String[] nombreArreglo = temporal[1].split("_");
                            String[] temporal2 = temporal[3].split("-");
                            if(temporal2.length > 5 ) {
                                System.out.println("Cliente tiene más del numero máximo de operaciones permitidas.");
                                return null;
                            }
                            
                            if(!temporal[4].equals("1") && !temporal[4].equals("0")) {
                                System.out.println("Prioridad de cliente no se aferra al formato (1 = prioridad, 2 = no prioridad.)");
                                return null;
                            }
                            int edadEntrada = Integer.parseInt(temporal[2]);
                            Cliente cliente;

                            switch (nombreArreglo.length) {
                                case 4:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], nombreArreglo[1], nombreArreglo[2], nombreArreglo[3], edadEntrada);
                                    break;
                                case 3:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], nombreArreglo[2], edadEntrada);
                                    break;
                                case 2:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", nombreArreglo[1], "", edadEntrada);
                                    break;
                                case 1:
                                    cliente = new Cliente(temporal[0], nombreArreglo[0], "", "", "", edadEntrada);
                                    break;
                                default:
                                    cliente = null;
                                    break;
                            }

                            if (cliente == null) {
                                System.out.println("Error de formato.");
                                return null;
                            } else {
                                Cliente clienteBuscado = lista.buscar(cliente.getCedula());
                                if (clienteBuscado != null) {
                                    if (!clienteBuscado.getPrimerNombre().equals(cliente.getPrimerNombre()) || !clienteBuscado.getSegundoNombre().equals(cliente.getSegundoNombre()) || !clienteBuscado.getPrimerApellido().equals(cliente.getPrimerApellido()) || !clienteBuscado.getSegundoApellido().equals(cliente.getSegundoApellido()) || clienteBuscado.getEdad() != cliente.getEdad()) {
                                        System.out.println("Cliente del archivo de entrada se repite de una forma no consistente con respecto a su edad o nombres");
                                        return null;
                                    }
                                }
                                for (String sol : temporal2) {
                                    char solicitud = sol.charAt(0);
                                    switch (solicitud) {
                                        case 'A':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'R':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'D':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'P':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        case 'C':
                                            cliente.encolarSolicitud(solicitud);
                                            break;
                                        default:
                                            System.out.println("Operación no soportada en archivo.");
                                            return null;
                                    }
                                }
                            }

                            if (temporal[4].equals("1") || cliente.getTerceraEdad()==true) {
                                if(temporal[4].equals("1")){
                                    cliente.setDiscapacidad(true);
                                }
                                cola.encolar(cliente, true);
                                lista.agregarAlFinal(cliente, true);
                            } else {
                                cola.encolar(cliente, false);
                                lista.agregarAlFinal(cliente, false);
                            }
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: Archivo de clientes no existe.");
                    return null;
                }
            }
        }
        return cola;
    }
}
