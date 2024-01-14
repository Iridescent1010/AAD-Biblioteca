package presentador;



import modelo.old.Categoria;
import modelo.old.Libro;
import java.util.List;

public interface VistaLibro {
    void lanzar();
    void setPresentador(PresentadorLibro presentador) throws Exception;
    Libro getLibro();

    void setCategorias(List<Categoria> categorias);
}
