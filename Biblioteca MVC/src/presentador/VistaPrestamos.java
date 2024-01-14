package presentador;

import modelo.Prestamo;

import java.util.List;

public interface VistaPrestamos extends VistaPrestamo{
    void setPrestamos(List<Prestamo> listaPrestamos);
}
