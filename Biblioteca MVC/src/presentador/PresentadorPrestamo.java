package presentador;

import excepciones.CampoVacioExcepcion;
import modelo.dao.CategoriaDAO;
import modelo.dao.PrestamoDAO;
import observer.EventType;
import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PresentadorPrestamo implements Observable {
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
        notifyObservers();
    }

    public void inserta() throws Exception {
        prestamoDAO.insertar(vistaPrestamo.getPrestamo());
        notifyObservers();
    }

    public void modifica() throws Exception {
        prestamoDAO.modificar(vistaPrestamo.getPrestamo());
        notifyObservers();
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

    // Métodos observable:

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers() throws SQLException, IOException, CampoVacioExcepcion {
        for (Observer obs : observers) {
            obs.update(EventType.PrestamoChanged);
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
