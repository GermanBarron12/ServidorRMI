package com.mycompany.calculadora.rmi;

import com.mycompany.calculadora.rmi.Calculadora;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementación del objeto remoto Calculator
 */
public class ImplCalculadora extends UnicastRemoteObject implements Calculadora {
    
    public ImplCalculadora() throws RemoteException {
        super();
        System.out.println("Calculadora remota creada");
    }
    
    @Override
    public double add(double a, double b) throws RemoteException {
        double result = a + b;
        System.out.println("Operacion: " + a + " + " + b + " = " + result);
        return result;
    }
    
    @Override
    public double subtract(double a, double b) throws RemoteException {
        double result = a - b;
        System.out.println("Operacion: " + a + " - " + b + " = " + result);
        return result;
    }
    
    @Override
    public double multiply(double a, double b) throws RemoteException {
        double result = a * b;
        System.out.println("Operacion: " + a + " * " + b + " = " + result);
        return result;
    }
    
    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Error: Division por cero no permitida");
        }
        double result = a / b;
        System.out.println("Operacion: " + a + " / " + b + " = " + result);
        return result;
    }
    
    @Override
    public String getMessage() throws RemoteException {
        return "Conexion exitosa con el servidor RMI";
    }
}