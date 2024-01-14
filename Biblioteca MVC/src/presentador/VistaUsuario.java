package presentador;
import modelo.old.Usuario;

public interface VistaUsuario {
    void lanzar();
    void setPresentador(PresentadorUsuario presentador) throws Exception;
    Usuario getUsuario();

}
