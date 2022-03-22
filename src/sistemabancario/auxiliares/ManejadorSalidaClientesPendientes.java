/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemabancario.auxiliares;

import java.io.File;
import sistemabancario.estructuras.ColaClientes;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManejadorSalidaClientesPendientes {
    ColaClientes pendientes;
    
    public ManejadorSalidaClientesPendientes(ColaClientes colaPendientes){
        pendientes = colaPendientes;
    }
    
    public boolean guardarClientes(File archivo){
        try {
            FileWriter fw = new FileWriter(archivo);
            StringBuilder sb;
            String datosCliente;
            boolean prioridadCliente;
            while (!pendientes.esVacia()){
                sb = new StringBuilder();
                prioridadCliente = pendientes.vistazo().getPrioridad();
                sb.append(pendientes.desencolar().getCliente().toString());
                if(prioridadCliente == true) {
                    sb.append(" ");
                    sb.append('1');
                } else {
                    sb.append(" ");
                    sb.append('0');
                }
                datosCliente = sb.toString();
                fw.write(datosCliente);
                if(!pendientes.esVacia()){
                    fw.write(System.getProperty("line.separator"));
                }
            }
            fw.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error al operar sobre 'clientes_pendientes.in'");
            return false;
        }
    }
}
