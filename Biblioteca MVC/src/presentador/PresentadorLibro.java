package presentador;


import excepciones.CampoVacioExcepcion;
import jdk.swing.interop.SwingInterOpUtils;
import modelo.dao.CategoriaDAO;
import modelo.dao.LibroDAO;
import observer.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PresentadorLibro implements Observable {
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
        notifyObservers();
    }

    public void inserta() throws Exception {
        libroDAO.insertar(vistaLibro.getLibro());
        notifyObservers();
    }

    public void modifica() throws Exception {
        libroDAO.modificar(vistaLibro.getLibro());
        notifyObservers();
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

    // Métodos observable:

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers() throws SQLException, IOException, CampoVacioExcepcion {
        for (Observer obs : observers) {
            obs.update(EventType.LibroChanged);
        }
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }
}
