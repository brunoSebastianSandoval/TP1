package edu.unlar.ar.model;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> listaLibros;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Prestamo> listaPrestamos;

    public Biblioteca() {
        this.listaLibros = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
        this.listaPrestamos = new ArrayList<>();
    }

    // Agrega un libro al inventario
    public void registrarLibro(Libro libro) {
        listaLibros.add(libro);
    }

    // Agrega un estudiante al sistema
    public void registrarEstudiante(Estudiante est) {
        listaEstudiantes.add(est);
    }

    // Busca un libro por su ISBN (Retorna null si no existe)
    public Libro buscarLibro(String isbn) {
        for (Libro l : listaLibros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    // Muestra todos los libros y su disponibilidad
    public void mostrarLibros() {
        System.out.println("\n--- Listado de Libros ---");
        for (Libro l : listaLibros) {
            String estado = l.isDisponible() ? "[Disponible]" : "[En prestamo]";
            System.out.println(estado + " " + l.getTitulo() + " (ISBN: " + l.getIsbn() + ")");
        }
    }
    // Registra un prestamo vinculando libro y estudiante
public void realizarPrestamo(Libro libro, Estudiante estudiante) {
    if (libro != null && libro.isDisponible()) {
        // Cambiamos el estado del objeto libro
        libro.setDisponible(false);
        
        // Creamos la instancia de Prestamo y la guardamos
        Prestamo nuevoPrestamo = new Prestamo(libro, estudiante);
        listaPrestamos.add(nuevoPrestamo);
        
        System.out.println("Prestamo exitoso: " + libro.getTitulo() + " a " + estudiante.getNombre());
    } else {
        System.out.println("No se pudo realizar el prestamo. El libro no esta disponible o no existe.");
    }
}
}