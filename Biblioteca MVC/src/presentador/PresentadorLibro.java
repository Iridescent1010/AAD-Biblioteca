package presentador;

import jdk.swing.interop.SwingInterOpUtils;
import modelo.dao.CategoriaDAO;
import modelo.dao.LibroDAO;
public class PresentadorLibro {
    private LibroDAO libroDAO;
    private CategoriaDAO categoriaDAO;
    private VistaLibro vistaLibro;

    public PresentadorLibro(LibroDAO libroDAO, CategoriaDAO categoriaDAO,VistaLibro vistaLibro) {
        this.libroDAO = libroDAO;
        this.categoriaDAO = categoriaDAO;
        this.vistaLibro = vistaLibro;
    }
    public void borra() throws Exception {
        libroDAO.borrar(vistaLibro.getLibro().getId());
    }

    public void inserta() throws Exception {
        libroDAO.insertar(vistaLibro.getLibro());
    }

    public void modifica() throws Exception {
        libroDAO.modificar(vistaLibro.getLibro());
    }

    public void listaAllLibros() throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        vistaLibros.setLibros(libroDAO.leerAllLibros());
    }
    public void listaAllCategorias(){
        try {
            vistaLibro.setCategorias(categoriaDAO.leerAllCategorias());
        } catch (Exception e){
            vistaLibro.setCategorias(null);
        }
    }
    public void leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        vistaLibros.setLibros(libroDAO.leerLibrosOR(id,titulo,autor,editorial,categoria));
    }
}
