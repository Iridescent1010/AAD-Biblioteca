package modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "BIBLIOTECA", catalog = "")
public class UsuarioDTO {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
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
}
