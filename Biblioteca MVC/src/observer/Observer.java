package observer;

import excepciones.CampoVacioExcepcion;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Objeto observador, será implementado por las ventanas Lista o bien por FormMain
 */
public interface Observer {

    void update(EventType event) throws SQLException, IOException, CampoVacioExcepcion;
}
