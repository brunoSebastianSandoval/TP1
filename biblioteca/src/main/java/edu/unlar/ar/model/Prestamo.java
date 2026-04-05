package edu.unlar.ar.model;
import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private Libro libro;
    private Estudiante estudiante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; 

    public Prestamo(Libro libro, Estudiante estudiante) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null; // Se asigna al devolver
    }

    //  equals y hashCode para el HashSet 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(libro, prestamo.libro) && 
               Objects.equals(estudiante, prestamo.estudiante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, estudiante);
    }

    @Override
    public String toString() { 
        return "Prestamo: " + libro.getTitulo() + " a " + estudiante.getNombre();
    }
}