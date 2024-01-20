package vista.helper;

import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOHibernate;
import modelo.dao.CategoriaDAOImpl;
import presentador.PresentadorCategoria;
import vista.FichaCategoria;
import vista.FormMain;
import vista.ListaCategorias;

public class Categorias {
    static CategoriaDAO categoriaDAO = new CategoriaDAOHibernate();

    public static ListaCategorias listaCategorias() throws Exception {
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,listaCategorias);
        presentadorCategoria.addObserver(FormMain.getInstance());
        listaCategorias.setPresentador(presentadorCategoria);
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,fichaCategoria);
        fichaCategoria.setPresentador(presentadorCategoria);
        presentadorCategoria.addObserver(FormMain.getInstance());
        fichaCategoria.lanzar();
        return fichaCategoria;

    }
}
