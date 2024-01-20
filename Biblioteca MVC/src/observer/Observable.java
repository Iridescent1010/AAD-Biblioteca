package observer;

import excepciones.CampoVacioExcepcion;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Objeto observado. Lo implementan los presentadores
 */
public interface Observable {
    void notifyObservers() throws SQLException, IOException, CampoVacioExcepcion;
    void addObserver(Observer obs);
    void removeObserver(Observer obs);

}
