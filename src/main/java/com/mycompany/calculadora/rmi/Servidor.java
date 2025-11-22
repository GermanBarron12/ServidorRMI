package com.mycompany.calculadora.rmi;

import com.mycompany.calculadora.rmi.ImplCalculadora;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;

/**
 * Servidor RMI - Ejecutar en la máquina servidor
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            
            System.setProperty("java.rmi.server.hostname", "192.168.100.144");
            
            System.out.println("Configurando servidor RMI...");
            System.out.println("Hostname configurado: 192.168.100.144");
            
            // Crear instancia del objeto remoto
            ImplCalculadora calculator = new ImplCalculadora();
            
            // Crear registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Registrar el objeto con un nombre
            registry.rebind("CalculatorService", calculator);
            
            // Obtener información del servidor
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            String hostname = InetAddress.getLocalHost().getHostName();
            
            System.out.println("\n-------------------------------");
            System.out.println("     SERVIDOR RMI INICIADO       ");
            System.out.println("---------------------------------");
            System.out.println("  Host:     " + hostname);
            System.out.println("  IP:       " + serverIP);
            System.out.println("  Puerto:   1099");
            System.out.println("  Servicio: CalculatorService");
            System.out.println("  RMI Host: 192.168.100.144");
            System.out.println("---------------------------------");
            System.out.println("Esperando conexiones de clientes...\n");
            
            // Mantener el servidor corriendo
            System.out.println("Presiona Ctrl+C para detener el servidor");
            Thread.sleep(Long.MAX_VALUE);
            
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}