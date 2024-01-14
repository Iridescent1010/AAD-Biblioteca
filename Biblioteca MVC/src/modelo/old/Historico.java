package modelo.old;

import modelo.Entidad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase (POJO) será una representación de la tabla historico
 * @author AGE
 * @version 2
 */
public class Historico extends Entidad {
    private int idHistorico;
    private String user;
    private LocalDateTime fecha=LocalDateTime.now();
    private String info;

    /**
     * Getter para atributo id que procede de la herencia
     * @return el valor del atributo idHistorico
     */
    @Override
    public int getId() {
        return idHistorico;
    }
    /**
     * Getter para atributo idHistorico
     * @return el valor del atributo idHistorico
     */
    public int getIdHistorico() {
        return idHistorico;
    }

    /**
     * Setter para asignar un codigo nuevo;
     * @param idHistorico nuevo valor para el atributo id
     */
    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    /**
     * Getter para atributo user
     * @return el valor del atributo user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter para asignar un usuario nuevo;
     * @param user nuevo valor para el atributo user
     */
    public void setUser(String user){
        this.user = user;
    }
    /**
     * Getter para atributo fecha
     * @return el valor del atributo fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    /**
     * Getter para atributo fecha
     * @return el valor del atributo fecha en formato cadena
     */
    public String getFechaCad(){
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    /**
     * Setter para asignar una fecha de prestamo nueva;
     * @param fecha nuevo valor para el atributo fecha
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter para atributo info
     * @return el valor del atributo info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter para asignar un usuario nuevo;
     * @param info nuevo valor para el atributo info
     */
    public void setInfo(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s ", idHistorico, user,getFechaCad(),info);
    }

}
