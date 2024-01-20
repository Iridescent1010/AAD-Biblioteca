package vista.helper;


import modelo.Prestamo;
import modelo.dao.*;
import presentador.PresentadorPrestamo;
import vista.FichaPrestamo;
import vista.FormMain;
import vista.ListaPrestamos;

public class Prestamos {
    static PrestamoDAO prestamoDAO=new PrestamoDAOHibernate();
    static CategoriaDAO categoriaDAO=new CategoriaDAOHibernate();
    public static ListaPrestamos listaPrestamos() throws Exception {
        ListaPrestamos listaPrestamos=new ListaPrestamos();
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,listaPrestamos);
        presentadorPrestamo.addObserver(FormMain.getInstance());
        listaPrestamos.setPresentador(presentadorPrestamo);
        listaPrestamos.lanzar();
        return listaPrestamos;
    }

    public static FichaPrestamo fichaPrestamo(Prestamo prestamo) throws Exception {
        FichaPrestamo fichaPrestamo=new FichaPrestamo(prestamo);
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,fichaPrestamo);
        presentadorPrestamo.addObserver(FormMain.getInstance());
        fichaPrestamo.setPresentador(presentadorPrestamo);
        fichaPrestamo.lanzar();
        return fichaPrestamo;

    }
}
