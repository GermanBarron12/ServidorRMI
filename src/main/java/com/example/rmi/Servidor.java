package com.example.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;

/**
 * Servidor RMI - Ejecutar en la máquina servidor
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear instancia del objeto remoto
            ImplCalculadora calculadora = new ImplCalculadora();
            
            // Crear registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Registrar el objeto con un nombre
            registry.rebind("CalculatorService", calculadora);
            
            // Obtener información del servidor
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            String hostname = InetAddress.getLocalHost().getHostName();
            
            System.out.println("\n--------------------------------");
            System.out.println("    SERVIDOR RMI INICIADO         ");
            System.out.println("----------------------------------");
            System.out.println("  Host:     " + hostname);
            System.out.println("  IP:       " + serverIP);
            System.out.println("  Puerto:   1099");
            System.out.println("  Servicio: CalculatorService");
            System.out.println("----------------------------------");
            System.out.println("Esperando conexiones de clientes...\n");
            
        } catch (Exception e) {
            System.err.println("Error en el servidor: ");
            e.printStackTrace();
        }
    }
}