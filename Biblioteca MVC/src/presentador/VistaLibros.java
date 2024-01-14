package presentador;

import modelo.old.Libro;

import java.util.List;

public interface VistaLibros extends VistaLibro{
    void setLibros(List<Libro> listaLibros);
}
