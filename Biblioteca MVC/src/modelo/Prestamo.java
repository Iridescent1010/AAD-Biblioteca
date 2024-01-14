package modelo;

import modelo.dao.helper.Entidades;
import modelo.old.UsuarioDTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Esta clase (POJO) será una representación de la tabla prestamos
 * @author AGE
 * @version 2
 */
@Entity
@Table(name = "prestamos", schema = "BIBLIOTECA")
public class Prestamo extends Entidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPrestamo", nullable = false)
    private int idPrestamo;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private UsuarioDTO usuario;
    @ManyToOne
    @JoinColumn(name = "idLibro", referencedColumnName = "id")
    private Libro libro;

    @Basic
    @Column(name = "fechaPrestamo", nullable = true)
    private Timestamp fechaPrestamo = Timestamp.valueOf(LocalDateTime.now());

    // TODO: Eliminar estas dos
    private int libroId;
    private int usuarioId;

    /**
     * Getter para atributo id
     * @return el valor del atributo idPrestamo
     */
    @Override
    public int getId() {
        return idPrestamo;
    }
    /**
     * Getter para atributo idPrestamo
     * @return el valor del atributo idPrestamo
     */
    public int getIdPrestamo() {
        return idPrestamo;
    }
    /**
     * Setter para asignar un codigo nuevo;
     * @param idPrestamo nuevo valor para el atributo idPrestamo
     */
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    /**
     * Getter para atributo idLibro
     * @return el valor del atributo idLibro
     */
    public int getLibroId() {
        return libroId;
    }
    /**
     * Setter para asignar un idLibro nuevo;
     * @param idLibro nuevo valor para el atributo idLibro
     */
    public void setLibroId(int idLibro) {
        this.libroId = idLibro;
    }
    /**
     * Getter para atributo idUsuario
     * @return el valor del atributo idUsuario
     */
    public int getUsuarioId() {
        return usuarioId;
    }
    /**
     * Setter para asignar un idUsuario nuevo;
     * @param idUsuario nuevo valor para el atributo idUsuario
     */
    public void setUsuarioId(int idUsuario) {
        this.usuarioId = idUsuario;
    }
    /**
     * Getter para atributo fechaPrestamo
     * @return el valor del atributo fechaPrestamo
     */
    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo.toLocalDateTime();
    }
    /**
     * Getter para atributo fechaPrestamo
     * @return el valor del atributo fechaPrestamo en formato cadena
     */
    public String getFecha(){
        return getFechaPrestamo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    /**
     * Setter para asignar una fecha de prestamo nueva;
     * @param fechaPrestamo nuevo valor para el atributo fechaPrestamo
     */
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = Timestamp.valueOf(fechaPrestamo);
    }

    public Libro getObjLibro(){
        return Entidades.libro(libroId);
    }

    public Usuario getObjUsuario(){
        return  Entidades.usuario(usuarioId);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo that = (Prestamo) o;
        return Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPrestamo);
    }


    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
