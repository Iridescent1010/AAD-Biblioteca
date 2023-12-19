package modelo;

import modelo.dao.helper.Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase (POJO) será una representación de la tabla prestamos
 * @author AGE
 * @version 2
 */
public class Prestamo extends Entidad {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private LocalDateTime fechaPrestamo=LocalDateTime.now();
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
    public int getIdLibro() {
        return idLibro;
    }
    /**
     * Setter para asignar un idLibro nuevo;
     * @param idLibro nuevo valor para el atributo idLibro
     */
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    /**
     * Getter para atributo idUsuario
     * @return el valor del atributo idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
     * Setter para asignar un idUsuario nuevo;
     * @param idUsuario nuevo valor para el atributo idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Getter para atributo fechaPrestamo
     * @return el valor del atributo fechaPrestamo
     */
    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }
    /**
     * Getter para atributo fechaPrestamo
     * @return el valor del atributo fechaPrestamo en formato cadena
     */
    public String getFecha(){
        return fechaPrestamo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    /**
     * Setter para asignar una fecha de prestamo nueva;
     * @param fechaPrestamo nuevo valor para el atributo fechaPrestamo
     */
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Libro getObjLibro(){
        return Entidades.libro(idLibro);
    }

    public Usuario getObjUsuario(){
        return  Entidades.usuario(idUsuario);
    }
}
