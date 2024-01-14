package vista.helper;

import modelo.old.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOImpl;
import presentador.PresentadorCategoria;
import vista.FichaCategoria;
import vista.ListaCategorias;

public class Categorias {
    public static ListaCategorias listaCategorias() throws Exception {
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,listaCategorias);
        listaCategorias.setPresentador(presentadorCategoria);
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,fichaCategoria);
        fichaCategoria.setPresentador(presentadorCategoria);
        fichaCategoria.lanzar();
        return fichaCategoria;

    }
}
