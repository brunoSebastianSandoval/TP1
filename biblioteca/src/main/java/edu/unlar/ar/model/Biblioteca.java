package edu.unlar.ar.model;
import java.util.*;

public class Biblioteca {
    private List<Libro> catalogo;                 
    private Map<String, Estudiante> registro;      
    private Set<Prestamo> prestamosActivos;        

    public Biblioteca() {
        this.catalogo = new ArrayList<>();
        this.registro = new HashMap<>();
        this.prestamosActivos = new HashSet<>();
    }

    public void registrarEstudiante(Estudiante e) {
        registro.put(e.getLegajo(), e); // El HashMap usa el legajo como clave.
    }

    public void registrarLibro(Libro l) {
        catalogo.add(l);
    }

    // Multa
    // Calcula 1% por día, máximo 30 días calculables 
    public double calcularMulta(int diasRetraso, double valorLibro) {
        if (diasRetraso <= 0 || diasRetraso > 30) {
            return 0; // Caso base o límite de la pila [cite: 43, 44]
        }
        return (valorLibro * 0.01) + calcularMulta(diasRetraso - 1, valorLibro);
    }

    public void mostrarLibros() {
        for (Libro l : catalogo) {
            System.out.println(l.toString()); // Usa el toString del modelo [cite: 48]
        }
    }
    // Requisito 2.4: Búsqueda de libros en el catálogo
    public Libro buscarLibro(String isbn) {
        for (Libro l : catalogo) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null; // Si no se encuentra el ISBN [cite: 30]
    }

    // Requisito 2.4 y 3.4: Lógica de préstamo
    public void realizarPrestamo(Libro libro, Estudiante estudiante) {
        // Validar que el libro no sea null y esté disponible 
        if (libro != null && libro.isDisponible()) {
            libro.setDisponible(false); // Cambia estado 
            prestamosActivos.add(new Prestamo(libro, estudiante)); // HashSet 
            System.out.println("Préstamo registrado con éxito."); 
        } else {
            System.out.println("Error: El libro no está disponible."); 
        }
    }
}