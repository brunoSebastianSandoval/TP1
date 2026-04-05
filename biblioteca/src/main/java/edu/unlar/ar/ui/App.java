package edu.unlar.ar.ui;

import edu.unlar.ar.model.*;

public class App {
    public static void main(String[] args) {
        // Instancia de la clase principal
        Biblioteca control = new Biblioteca();

        // Carga de libros
        control.registrarLibro(new Libro("123", "El Quijote", "Cervantes", 1605));
        control.registrarLibro(new Libro("456", "Java 21", "Deitel", 2024));
        control.registrarLibro(new Libro("789", "El Principito", "Saint-Exupéry", 1943));
        control.registrarLibro(new Libro("101", "Fisica II", "Resnick", 2010));
        control.registrarLibro(new Libro("202", "Calculo", "Stewart", 2015));

        // Carga de estudiantes (Ejemplo para el TP)
        control.registrarEstudiante(new Estudiante("A001", "Bruno", "Sistemas","bruno@gmail.com"));
        control.registrarEstudiante(new Estudiante("A002", "Jemima", "Farmacia", "jemima@gmail.com"));

        // Prueba de funcionamiento
        control.mostrarLibros();
        Libro libroParaPrestar = control.buscarLibro("123");
       control.realizarPrestamo(libroParaPrestar, new Estudiante("A001", "Bruno", "Sistemas", "bruno@mail.com"));
       control.mostrarLibros();
    }
       
}