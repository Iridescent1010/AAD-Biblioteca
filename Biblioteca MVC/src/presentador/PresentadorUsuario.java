package presentador;

import excepciones.CampoVacioExcepcion;
import modelo.dao.UsuarioDAO;
import observer.EventType;
import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PresentadorUsuario implements Observable {
    private UsuarioDAO usuarioDAO;
    private VistaUsuario vistaUsuario;

    public PresentadorUsuario(UsuarioDAO usuarioDAO, VistaUsuario vistaUsuario) {
        this.usuarioDAO = usuarioDAO;
        this.vistaUsuario = vistaUsuario;
    }

    public void borra() throws Exception {
        usuarioDAO.borrar(vistaUsuario.getUsuario().getId());
        notifyObservers();
    }

    public void inserta() throws Exception {
        usuarioDAO.insertar(vistaUsuario.getUsuario());
        notifyObservers();
    }

    public void modifica() throws Exception {
        usuarioDAO.modificar(vistaUsuario.getUsuario());
        notifyObservers();
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.leerAllUsuarios());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.leerUsuariosOR(id,nombre,apellidos));
    }

    // Métodos observable:

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers() throws SQLException, IOException, CampoVacioExcepcion {
        for (Observer obs : observers) {
            obs.update(EventType.UsuarioChanged);
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
