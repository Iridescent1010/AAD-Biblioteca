package presentador;

import modelo.Categoria;

import java.util.List;

public interface VistaCategorias extends VistaCategoria{
    void setCategorias(List<Categoria> listaCategorias);
}
