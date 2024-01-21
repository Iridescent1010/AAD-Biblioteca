package modelo;

import modelo.Entidad;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Esta clase (POJO) será una representación de la tabla historico
 * @author AGE
 * @version 2
 */
@Entity
@Table(name = "historico", schema = "BIBLIOTECA")
public class Historico extends Entidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idHistorico", nullable = false)
    private int idHistorico;
    @Basic
    @Column(name = "user", nullable = true, length = -1)
    private String user;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Timestamp fecha = Timestamp.valueOf(LocalDateTime.now());
    @Basic
    @Column(name = "info", nullable = true, length = -1)
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
        return fecha.toLocalDateTime();
    }
    /**
     * Getter para atributo fecha
     * @return el valor del atributo fecha en formato cadena
     */
    public String getFechaCad(){
        return getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    /**
     * Setter para asignar una fecha de prestamo nueva;
     * @param fecha nuevo valor para el atributo fecha
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = Timestamp.valueOf(fecha);
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

    @Override
    public String getCsv() {
        return toString();
    }

    @Override
    public String getCsvHeader() {
        return "idHistorico, user, fecha, info";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historico that = (Historico) o;
        return idHistorico == that.idHistorico && Objects.equals(user, that.user) && Objects.equals(fecha, that.fecha) && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, user, fecha, info);
    }
}
