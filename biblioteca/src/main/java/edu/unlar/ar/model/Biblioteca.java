package edu.unlar.ar.model;
import java.util.*;
import edu.unlar.ar.exception.LibroNoDisponible;
import edu.unlar.ar.exception.LimitePrestamosExcedido;

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
            System.out.println(l.toString()); // Usa el toString del modelo 
        }
    }

    // Búsqueda de libros por titulo
    public List<Libro> buscarLibroTitulo (String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogo) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                resultados.add(l);
            }
        }
        return resultados;
    }

    // Lógica de préstamo
    public void realizarPrestamo(Libro libro, Estudiante estudiante) throws LibroNoDisponible, LimitePrestamosExcedido {
        // Validar que el libro no sea null y esté disponible 
        if (libro == null || !libro.isDisponible()) {
            throw new LibroNoDisponible("El libro no está disponible para préstamo.");
        }

        // Validación de límite de préstamos por estudiante
        long count = prestamosActivos.stream()
            .filter(p -> p.getEstudiante().equals(estudiante))
            .count();

        if (count >= 3) {
            throw new LimitePrestamosExcedido("El estudiante ya alcanzo el limite de 3 prestamos");
        }

        // si todo esta bien
        libro.setDisponible(false);
        prestamosActivos.add(new Prestamo(libro, estudiante));
        System.out.println("Prestamo registrado con exito.");
    }

    // registro de devolucion
    public void registrarDevolucion(Libro libro) {
        libro.setDisponible(true);
        System.out.println("libro devuelto y disponible."); 
    }

}