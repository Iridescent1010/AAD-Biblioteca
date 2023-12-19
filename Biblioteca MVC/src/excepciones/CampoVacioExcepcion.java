package excepciones;

/**
 * Clase para generar una excepción en el caso de
 * que un campo de una tabla este vacío y requiera
 * que sea distinto de NULL
 * @author AGE
 * @version 2
 */
public class CampoVacioExcepcion extends Exception {
    /**
     * Constructor por defecto para obtener un mensaje más personalizado
     * pase el siguiente parámetro
     * @param campo NOMBRE del campo que requiera que tenga valor distinto de NULL
     */
    public CampoVacioExcepcion(String campo){
        super(String.format("Atención el campo %s no puede ser vacío",campo));

    }

}
