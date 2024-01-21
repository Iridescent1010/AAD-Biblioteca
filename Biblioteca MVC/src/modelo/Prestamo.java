package modelo;

import modelo.dao.helper.Entidades;
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
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idLibro", referencedColumnName = "id")
    private Libro libro;

    @Basic
    @Column(name = "fechaPrestamo", nullable = true)
    private Timestamp fechaPrestamo = Timestamp.valueOf(LocalDateTime.now());

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
        if (libro == null) return -1;
        return libro.getId();
    }
    /**
     * Setter para asignar un idLibro nuevo;
     * @param idLibro nuevo valor para el atributo idLibro
     */
    public void setLibroId(int idLibro) {
        if (libro != null) libro.setId(idLibro);
    }
    /**
     * Getter para atributo idUsuario
     * @return el valor del atributo idUsuario
     */
    public int getUsuarioId() {
        if (usuario == null) return -1;
        return usuario.getId();
    }
    /**
     * Setter para asignar un idUsuario nuevo;
     * @param idUsuario nuevo valor para el atributo idUsuario
     */
    public void setUsuarioId(int idUsuario) {
        if (usuario != null) usuario.setId(idUsuario);
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

    public String getStandardFormattedDate() {
        return getFechaPrestamo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    /**
     * Setter para asignar una fecha de prestamo nueva;
     * @param fechaPrestamo nuevo valor para el atributo fechaPrestamo
     */
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = Timestamp.valueOf(fechaPrestamo);
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Libro getObjLibro(){
        return getLibro();
    }

    public Usuario getObjUsuario(){
        return  getUsuario();
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        String format = """
                Prestamo %d
                    Usuario %s
                    Libro %s
                    Fecha %s
                """;
        return String.format(format,idPrestamo, usuario.toString(), libro.toString(), fechaPrestamo.toString());
    }

    @Override
    public String getCsvHeader() {
        return "idPrestamo, idLibro, idUsuario, fechaPrestamo";
    }

    @Override
    public String getCsv() {
        return String.format("%d, %d, %d, %s", idPrestamo, getLibroId(), getUsuarioId(), getStandardFormattedDate());
    }
}
