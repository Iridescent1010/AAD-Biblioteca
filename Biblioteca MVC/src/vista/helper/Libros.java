package vista.helper;


import modelo.BusquedaLibro;
import modelo.Libro;
import modelo.dao.*;
import presentador.PresentadorLibro;
import vista.FichaLibro;
import vista.FormMain;
import vista.ListaLibros;
import vista.SeleccionaLibro;

import java.awt.*;

public class Libros {
    static LibroDAO libroDAO = new LibroDAOHibernate();
    static CategoriaDAO categoriaDAO = new CategoriaDAOHibernate();

    public static ListaLibros listaLibros() throws Exception {
        ListaLibros listaLibros=new ListaLibros();
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,listaLibros);
        presentadorLibro.addObserver(FormMain.getInstance());
        listaLibros.setPresentador(presentadorLibro);
        listaLibros.lanzar();
        return listaLibros;
    }

    public static SeleccionaLibro seleccionaLibro(Frame owner, String title, boolean modal, BusquedaLibro busquedaLibro) throws Exception {
        SeleccionaLibro seleccionaLibro=new SeleccionaLibro(owner,title,modal,busquedaLibro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,seleccionaLibro);
        presentadorLibro.addObserver(FormMain.getInstance());
        seleccionaLibro.setPresentador(presentadorLibro);
        seleccionaLibro.lanzar();
        return seleccionaLibro;
    }

    public static FichaLibro fichaLibro(Libro libro) throws Exception {
        FichaLibro fichaLibro=new FichaLibro(libro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,fichaLibro);
        presentadorLibro.addObserver(FormMain.getInstance());
        fichaLibro.setPresentador(presentadorLibro);
        fichaLibro.lanzar();
        return fichaLibro;
    }
}
