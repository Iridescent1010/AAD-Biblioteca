package modelo;
import excepciones.CampoVacioExcepcion;
import modelo.old.PrestamosDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Esta clase (POJO) será una representación de la tabla usuario
 * @author AGE
 * @version 2
 */
@Entity
@Table(name = "usuario", schema = "BIBLIOTECA")
public class Usuario extends Entidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @Column(name = "apellidos", nullable = true, length = -1)
    private String apellidos;
    @OneToMany(mappedBy = "usuario")
    private Collection<PrestamosDTO> prestamos;

    /**
     * Getter para atributo id
     * @return el valor del atributo id
     */
    @Override
    public int getId() {
        return id;
    }
    /**
     * Setter para asignar un codigo nuevo;
     * @param id nuevo valor para el atributo id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter para atributo nombre
     * @return el valor del atributo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter para asignar un nombre nuevo;
     * @param nombre nuevo valor para el atributo id
     */
    public void setNombre(String nombre) throws CampoVacioExcepcion {
        if (nombre.trim().equals(""))
            throw new CampoVacioExcepcion("NOMBRE");
        else this.nombre = nombre;
    }
    /**
     * Getter para atributo apellidos
     * @return el valor del atributo apellidos
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Setter para asignar un apellidos nuevo;
     * @param apellidos nuevo valor para el atributo apellidos
     */
    public void setApellidos(String apellidos) throws CampoVacioExcepcion {
        if (apellidos.trim().equals(""))
            throw new CampoVacioExcepcion("APELLIDOS");
        else this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario that = (Usuario) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos);
    }

    public Collection<PrestamosDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Collection<PrestamosDTO> prestamos) {
        this.prestamos = prestamos;
    }
    @Override
    public String toString(){
        return String.format("%d. %s %s",id,nombre,apellidos);
    }

}
