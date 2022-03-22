/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.auxiliares;

import java.io.File;
import java.io.FileNotFoundException;
import sistemabancario.estructuras.ColaClientes;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.estructuras.PilaTransacciones;

public class ManejadorSalidaTransacciones {

    PilaTransacciones transacciones;
    LocalDate fecha;

    public ManejadorSalidaTransacciones(PilaTransacciones trans) {
        transacciones = trans;
        fecha = null;
    }

    public boolean exportarTransacciones(File archivoTaquilla) {
        if (archivoTaquilla.exists()) {
            try {
                System.out.println("Detectado archivo de taquilla.");
                Scanner scanFecha = new Scanner(archivoTaquilla);
                fecha = LocalDate.parse(scanFecha.nextLine());
                scanFecha.close();
                String nuevoArchivoNombre = "./taquilla " + fecha.getDayOfMonth() + "-" + fecha.getMonthValue() + "-" + fecha.getYear() + ".log";
                File nuevoArchivo = new File(nuevoArchivoNombre);
                archivoTaquilla.renameTo(nuevoArchivo);
                System.out.println("Archivo de taquilla existente renombrado satisfactoriamente.");
                fecha = fecha.plusDays(1);
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo de taquilla existente no encontrado.");
                return false;
            }
        } else {
            fecha = LocalDate.now();
        }
        try {
            FileWriter fw = new FileWriter(archivoTaquilla);
            fw.write(fecha.toString());
            fw.write(System.getProperty("line.separator"));
            while (!transacciones.esVacia()) {
                fw.write(transacciones.desapilar().getTransaccion().toString());
                if (!transacciones.esVacia()) {
                    fw.write(System.getProperty("line.separator"));
                }
            }
            fw.close();

        } catch (IOException ex) {
            System.out.println("Error al operar sobre el archivo de taquilla.");
        }
        System.out.println("Archivo de taquilla creado satisfactoriamente.");
        return true;
    }
}
