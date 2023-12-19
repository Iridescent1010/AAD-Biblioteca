package modelo;
import excepciones.CampoVacioExcepcion;

/**
 * Esta clase (POJO) será una representación de la tabla usuario
 * @author AGE
 * @version 2
 */
public class Usuario extends Entidad{
    private int id;
    private String nombre;
    private String apellidos;

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
    /**
     * realiza una copia de otro objeto usuario en esta instancia
     * @param usuario objeto desde donde copiar la información
     */
    public void clona(Usuario usuario){
        this.id=usuario.getId();
        this.nombre=usuario.getNombre();
        this.apellidos=usuario.getApellidos();
    }
    @Override
    public String toString(){
        return String.format("%d. %s %s",id,nombre,apellidos);
    }

}
