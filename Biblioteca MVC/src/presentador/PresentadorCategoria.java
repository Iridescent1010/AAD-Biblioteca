package presentador;

import excepciones.CampoVacioExcepcion;
import modelo.dao.CategoriaDAO;
import observer.EventType;
import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PresentadorCategoria implements Observable {
    private CategoriaDAO categoriaDAO;
    private VistaCategoria vistaCategoria;

    public PresentadorCategoria(CategoriaDAO categoriaDAO, VistaCategoria vistaCategoria) {
        this.categoriaDAO = categoriaDAO;
        this.vistaCategoria = vistaCategoria;
    }

    public void borra() throws Exception {
        categoriaDAO.borrar(vistaCategoria.getCategoria().getId());
        notifyObservers();
    }

    public void inserta() throws Exception {
        categoriaDAO.inserta(vistaCategoria.getCategoria());
        notifyObservers();
    }

    public void modifica() throws Exception {
        categoriaDAO.modificar(vistaCategoria.getCategoria());
        notifyObservers();
    }

    public void listaAllCategorias() throws Exception {
        VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        vistaCategorias.setCategorias(categoriaDAO.leerAllCategorias());
    }

    // Métodos observable:

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers() throws SQLException, IOException, CampoVacioExcepcion {
        for (Observer obs : observers) {
            obs.update(EventType.CategoriaChanged);
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
