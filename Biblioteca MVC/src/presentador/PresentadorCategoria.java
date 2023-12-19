package presentador;

import modelo.dao.CategoriaDAO;


public class PresentadorCategoria {
    private CategoriaDAO categoriaDAO;
    private VistaCategoria vistaCategoria;

    public PresentadorCategoria(CategoriaDAO categoriaDAO, VistaCategoria vistaCategoria) {
        this.categoriaDAO = categoriaDAO;
        this.vistaCategoria = vistaCategoria;
    }

    public void borra() throws Exception {
        categoriaDAO.borrar(vistaCategoria.getCategoria().getId());
    }

    public void inserta() throws Exception {
        categoriaDAO.inserta(vistaCategoria.getCategoria());
    }

    public void modifica() throws Exception {
        categoriaDAO.modificar(vistaCategoria.getCategoria());
    }

    public void listaAllCategorias() throws Exception {
        VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        vistaCategorias.setCategorias(categoriaDAO.leerAllCategorias());
    }
}
