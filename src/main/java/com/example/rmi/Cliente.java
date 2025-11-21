package com.example.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Cliente RMI - Ejecutar en la máquina cliente
 */
public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Solicitar IP del servidor
            System.out.println("-----------------------------------");
            System.out.println("       CLIENTE RMI - CALCULADORA   ");
            System.out.println("-----------------------------------");
            System.out.print("\nIngrese la IP del servidor (Enter para localhost): ");
            String serverIP = scanner.nextLine().trim();
            
            if (serverIP.isEmpty()) {
                serverIP = "localhost";
            }
            
            // Conectar al servidor
            System.out.println("\n⟳ Conectando al servidor " + serverIP + "...");
            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            Calculadora calculator = (Calculadora) registry.lookup("CalculatorService");
            
            // Verificar conexión
            String message = calculator.getMessage();
            System.out.println(" " + message);
            System.out.println("\n----------------------------------");
            System.out.println("    CALCULADORA REMOTA CONECTADA");
            System.out.println("----------------------------------\n");
            
            // Menú interactivo
            boolean running = true;
            while (running) {
                System.out.println("|-----------------------------|");
                System.out.println("|      MENÚ DE OPERACIONES    |");
                System.out.println("|-----------------------------|");
                System.out.println("|  1. Sumar                   |");
                System.out.println("|  2. Restar                  |");
                System.out.println("|  3. Multiplicar             |");
                System.out.println("|  4. Dividir                 |");
                System.out.println("|  5. Salir                   |");
                System.out.println("|-----------------------------|");
                System.out.print("\n→ Seleccione una opcion: ");
                
                int option = scanner.nextInt();
                
                if (option == 5) {
                    running = false;
                    System.out.println("\n Desconectando...");
                    System.out.println("¡Hasta luego!\n");
                    break;
                }
                
                if (option < 1 || option > 4) {
                    System.out.println("\n Opcion invalida. Intente nuevamente.\n");
                    continue;
                }
                
                System.out.print("Primer numero: ");
                double num1 = scanner.nextDouble();
                
                System.out.print("Segundo numero: ");
                double num2 = scanner.nextDouble();
                
                double result = 0;
                String operation = "";
                
                try {
                    switch (option) {
                        case 1:
                            result = calculator.add(num1, num2);
                            operation = num1 + " + " + num2;
                            break;
                        case 2:
                            result = calculator.subtract(num1, num2);
                            operation = num1 + " - " + num2;
                            break;
                        case 3:
                            result = calculator.multiply(num1, num2);
                            operation = num1 + " × " + num2;
                            break;
                        case 4:
                            result = calculator.divide(num1, num2);
                            operation = num1 + " ÷ " + num2;
                            break;
                    }
                    
                    System.out.println("\n|-------------------------");
                    System.out.println("| Resultado: " + operation + " = " + result);
                    System.out.println("|-------------------------\n");
                    
                } catch (Exception e) {
                    System.err.println("\n Error: \n");
                }
            }
            
        } catch (Exception e) {
            System.err.println("\n Error al conectar con el servidor:");
            System.err.println("  " );
            System.err.println("\nVerifique que:");
            System.err.println("  • El servidor está ejecutándose");
            System.err.println("  • La IP es correcta");
            System.err.println("  • El firewall permite la conexión en el puerto 1099\n");
        } finally {
            scanner.close();
        }
    }
}