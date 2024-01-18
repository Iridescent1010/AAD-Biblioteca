package vista.helper;

import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOHibernate;
import modelo.dao.CategoriaDAOImpl;
import presentador.PresentadorCategoria;
import vista.FichaCategoria;
import vista.ListaCategorias;

public class Categorias {
    public static ListaCategorias listaCategorias() throws Exception {
        CategoriaDAO categoriaDAO=new CategoriaDAOHibernate();
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,listaCategorias);
        listaCategorias.setPresentador(presentadorCategoria);
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        CategoriaDAO categoriaDAO=new CategoriaDAOHibernate();
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,fichaCategoria);
        fichaCategoria.setPresentador(presentadorCategoria);
        fichaCategoria.lanzar();
        return fichaCategoria;

    }
}
