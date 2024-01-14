package presentador;


import modelo.old.Categoria;


public interface VistaCategoria {
    void lanzar();
    void setPresentador(PresentadorCategoria presentador) throws Exception;
    Categoria getCategoria();



}
