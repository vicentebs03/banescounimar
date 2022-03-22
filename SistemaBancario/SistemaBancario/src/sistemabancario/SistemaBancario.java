package sistemabancario;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import sistemabancario.auxiliares.ManejadorEntrada;
import sistemabancario.auxiliares.ManejadorSalidaClientesPendientes;
import sistemabancario.auxiliares.ManejadorSalidaTransacciones;
import sistemabancario.clasesfundamentales.Cliente;
import sistemabancario.clasesfundamentales.Taquilla;
import sistemabancario.estructuras.ColaClientes;
import sistemabancario.estructuras.PilaTransacciones;

public class SistemaBancario {

    public static void main(String[] args) {
        File clientes = new File("./clientes.in");
        File clientesPendientes = new File("./clientes_pendientes.in");
        File reporteTaquilla = new File("./taquilla.log");
        Scanner scan = new Scanner(System.in);
        
        boolean resultado;
        boolean salir = false;
        while (salir == false) {
            menu();
            String op = seleccion(scan);
            switch (op) {
                case "0":
                    salir = true;
                    break;
                case "1":
                    System.out.println("-------------------------------------------");
                    resultado = ejecutar(clientes, clientesPendientes, reporteTaquilla);
                    if (resultado == true) {
                        System.out.println("Simulacion exitosa.");
                    } else {
                        System.out.println("Error fatal de entrada: Imposible realizar simulación.");
                    }
                    System.out.println("-------------------------------------------");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("Sistema de Taquilla Individual (BANESCO-UNIMAR)");
        System.out.println("");
        System.out.println("1. Correr simulación.");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Seleccion: ");
    }

    public static boolean ejecutar(File clientes, File clientesPendientes, File reporteTaquilla) {
        ManejadorEntrada me = new ManejadorEntrada();
        ColaClientes cola = me.prepararClientes(clientesPendientes, clientes);
        if (cola==null) {
            return false;
        }
        Taquilla taquilla = new Taquilla(cola);
        taquilla.atender();
        ColaClientes pendientes = taquilla.getPendientes();
        if (!pendientes.esVacia()) {
            ManejadorSalidaClientesPendientes msp = new ManejadorSalidaClientesPendientes(pendientes);
            msp.guardarClientes(clientesPendientes);
            System.out.println("Clientes pendientes guardados satisfactoriamente.");
        }
        PilaTransacciones transacciones = taquilla.getTransacciones();
        ManejadorSalidaTransacciones mst = new ManejadorSalidaTransacciones(transacciones);
        mst.exportarTransacciones(reporteTaquilla);
        return true;
    }

    public static String seleccion(Scanner scan) {
        boolean exito = false;
        String seleccion = "";
        while (exito == false) {
            seleccion = scan.nextLine();
            if (!seleccion.equals("1") && !seleccion.equals("0")) {
                System.out.println("");
                System.out.println("Seleccion no valida.");
                System.out.println("");
                menu();
            } else {
                exito = true;
            }
        }
        return seleccion;

    }
}
