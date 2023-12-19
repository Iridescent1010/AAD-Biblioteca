package presentador;

import modelo.dao.CategoriaDAO;
import modelo.dao.PrestamoDAO;
public class PresentadorPrestamo {
    private PrestamoDAO prestamoDAO;
    private CategoriaDAO categoriaDAO;
    private VistaPrestamo vistaPrestamo;

    public PresentadorPrestamo(PrestamoDAO prestamoDAO, CategoriaDAO categoriaDAO, VistaPrestamo vistaPrestamo) {
        this.prestamoDAO = prestamoDAO;
        this.categoriaDAO = categoriaDAO;
        this.vistaPrestamo = vistaPrestamo;
    }

    public void borra() throws Exception {
        prestamoDAO.borrar(vistaPrestamo.getPrestamo().getId());
    }

    public void inserta() throws Exception {
        prestamoDAO.insertar(vistaPrestamo.getPrestamo());
    }

    public void modifica() throws Exception {
        prestamoDAO.modificar(vistaPrestamo.getPrestamo());
    }

    public void listaAllPrestamos() throws Exception {
        VistaPrestamos vistaPrestamos = (VistaPrestamos) vistaPrestamo;
        vistaPrestamos.setPrestamos(prestamoDAO.leerAllPrestamos());
    }

    public void listaAllCategorias() {
        try {
            vistaPrestamo.setCategorias(categoriaDAO.leerAllCategorias());
        } catch (Exception e){
            vistaPrestamo.setCategorias(null);
        }
    }
}
