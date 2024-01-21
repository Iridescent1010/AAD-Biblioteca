package modelo;

public class BusquedaLibro {
    public TipoBusqueda tipo;
    public int id;
    public String titulo;
    public String autor;
    public String editorial;
    public int categoria;
    public int idSel=0;

    // Para debug:
    @Override
    public String toString() {
        return "BusquedaLibro{" +
                "tipo=" + tipo +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", categoria=" + categoria +
                ", idSel=" + idSel +
                '}';
    }
}
