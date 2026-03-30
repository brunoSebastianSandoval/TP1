package edu.unlar.ar.model;
import java.time.LocalDate;

public class Prestamo {
    private Libro libro;           
    private Estudiante estudiante; 
    private LocalDate fecha;

public Prestamo(Libro libro, Estudiante estudiante) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fecha = LocalDate.now(); 
    }

public Libro getLibro() {
    return libro;
}

public void setLibro(Libro libro) {
    this.libro = libro;
}

public Estudiante getEstudiante() {
    return estudiante;
}

public void setEstudiante(Estudiante estudiante) {
    this.estudiante = estudiante;
}

public LocalDate getFecha() {
    return fecha;
}

public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
}


}