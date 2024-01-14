package presentador;


import modelo.old.Categoria;
import modelo.old.Prestamo;
import java.util.List;

public interface VistaPrestamo {
    void lanzar();
    void setPresentador(PresentadorPrestamo presentador) throws Exception;
    Prestamo getPrestamo();
    void setCategorias(List<Categoria> categorias);

}
