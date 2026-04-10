package edu.unlar.ar.ui;

import edu.unlar.ar.exception.LibroNoDisponible;
import edu.unlar.ar.exception.LimitePrestamosExcedido;
import edu.unlar.ar.model.*;

public class App {
    public static void main(String[] args) {
        Biblioteca control = new Biblioteca();

        // Registro de datos  
        control.registrarLibro(new Libro("978-0134670942", "Clean Code", "Robert C. Martin", 2008));
        control.registrarLibro(new Libro("978-0321356680", "Effective Java", "Joshua Bloch", 2018));
        control.registrarLibro(new Libro("978-8448198442", "Cálculo de una variable", "James Stewart", 2015));
        control.registrarLibro(new Libro("978-9500606034", "Física Universitaria", "Sears-Zemansky", 2013));
        control.registrarLibro(new Libro("978-8415552371", "Estructuras de Datos en Java", "Mark Allen Weiss", 2013));

        Estudiante bruno = new Estudiante("Eisi1256", "Bruno Sebastian", "Ingeneria en Sistemas de informacion", "bruno@unlar.edu.ar");
        Estudiante isma = new Estudiante("EISI5613", "Ismael Alejandro","Ingeneria en Sistemas de informacion", "isma@unlar.edu.ar");
        Estudiante tomas = new Estudiante("EISI3223","Tomas Fuentes","Ingeneria en Sistemas de informacion","tomas@unlar.edu.ar");

        // Préstamo con captura de errores 
        System.out.println("--- Prueba de Préstamos y Excepciones ---");
        try {
            // Buscamos el libro (usamos .get(0) porque buscarLibroTitulo devuelve una lista)
            Libro libro = control.buscarLibroTitulo("Calculo").get(0); 
            
            System.out.println("Intentando primer préstamo...");
            control.realizarPrestamo(libro, bruno); 
            
            // Probamos prestar el mismo libro de nuevo para que salte la excepcion 
            System.out.println("Intentando prestar el mismo libro otra vez...");
            control.realizarPrestamo(libro, isma);
            // Prueba con Tomas para verificar el límite de préstamos
    System.out.println("\n--- Prueba de Límite con Tomas ---");
    try {
        Libro l1 = control.buscarLibroTitulo("Clean Code").get(0);
        Libro l2 = control.buscarLibroTitulo("Effective Java").get(0);
        Libro l3 = control.buscarLibroTitulo("Física").get(0);
        Libro l4 = control.buscarLibroTitulo("Estructuras").get(0);

        // Tomas pide 3 libros (esto debería andar bien)
        control.realizarPrestamo(l1, tomas);
        control.realizarPrestamo(l2, tomas);
        control.realizarPrestamo(l3, tomas);

        //debería disparar la excepción
        System.out.println("Intentando pedir un cuarto libro para Tomas...");
        control.realizarPrestamo(l4, tomas);

    } catch (LimitePrestamosExcedido e) {
        System.out.println("ALERTA: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    } 

        } catch (LibroNoDisponible e) {
            System.out.println("ALERTA: " + e.getMessage()); 
        } catch (LimitePrestamosExcedido e) {
            System.out.println("ALERTA: " + e.getMessage()); 
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // Demostración de Multa Recursiva  
        System.out.println("\n--- Prueba de Multa ---");
        double multaTotal = control.calcularMulta(15, 5000.0); // 15 días, libro de $5000 
        System.out.println("La multa por 15 dias de retraso es: $" + multaTotal); 
    }
}