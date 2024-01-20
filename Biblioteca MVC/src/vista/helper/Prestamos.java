package vista.helper;


import modelo.Prestamo;
import modelo.dao.*;
import presentador.PresentadorPrestamo;
import vista.FichaPrestamo;
import vista.ListaPrestamos;

public class Prestamos {
    public static ListaPrestamos listaPrestamos() throws Exception {
        PrestamoDAO prestamoDAO=new PrestamoDAOHibernate();
        CategoriaDAO categoriaDAO=new CategoriaDAOHibernate();

        ListaPrestamos listaPrestamos=new ListaPrestamos();
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,listaPrestamos);
        listaPrestamos.setPresentador(presentadorPrestamo);
        listaPrestamos.lanzar();
        return listaPrestamos;
    }

    public static FichaPrestamo fichaPrestamo(Prestamo prestamo) throws Exception {
        PrestamoDAO prestamoDAO=new PrestamoDAOHibernate();
        CategoriaDAO categoriaDAO=new CategoriaDAOHibernate();
        FichaPrestamo fichaPrestamo=new FichaPrestamo(prestamo);
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,fichaPrestamo);
        fichaPrestamo.setPresentador(presentadorPrestamo);
        fichaPrestamo.lanzar();
        return fichaPrestamo;

    }
}
