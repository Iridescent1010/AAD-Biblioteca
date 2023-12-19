package vista.helper;


import modelo.BusquedaLibro;
import modelo.Libro;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOImpl;
import modelo.dao.LibroDAO;
import modelo.dao.LibroDAOImpl;
import presentador.PresentadorLibro;
import vista.FichaLibro;
import vista.ListaLibros;
import vista.SeleccionaLibro;

import java.awt.*;

public class Libros {
    public static ListaLibros listaLibros() throws Exception {
        LibroDAO libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        ListaLibros listaLibros=new ListaLibros();
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,listaLibros);
        listaLibros.setPresentador(presentadorLibro);
        listaLibros.lanzar();
        return listaLibros;
    }

    public static SeleccionaLibro seleccionaLibro(Frame owner, String title, boolean modal, BusquedaLibro busquedaLibro) throws Exception {
        LibroDAO libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        SeleccionaLibro seleccionaLibro=new SeleccionaLibro(owner,title,modal,busquedaLibro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,seleccionaLibro);
        seleccionaLibro.setPresentador(presentadorLibro);
        seleccionaLibro.lanzar();
        return seleccionaLibro;
    }

    public static FichaLibro fichaLibro(Libro libro) throws Exception {
        LibroDAO libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        FichaLibro fichaLibro=new FichaLibro(libro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,fichaLibro);
        fichaLibro.setPresentador(presentadorLibro);
        fichaLibro.lanzar();
        return fichaLibro;

    }
}
