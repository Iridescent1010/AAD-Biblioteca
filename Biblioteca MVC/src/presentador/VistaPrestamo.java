package presentador;


import modelo.Categoria;
import modelo.Prestamo;
import java.util.List;

public interface VistaPrestamo {
    void lanzar();
    void setPresentador(PresentadorPrestamo presentador) throws Exception;
    Prestamo getPrestamo();
    void setCategorias(List<Categoria> categorias);

}
