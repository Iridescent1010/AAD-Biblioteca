package modelo.dao;

import modelo.old.Libro;

import java.util.List;

public class LibroDAOHibernate implements LibroDAO{
    @Override
    public boolean insertar(Libro libro) throws Exception {
        return false;
    }

    @Override
    public boolean modificar(Libro libro) throws Exception {
        return false;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        return false;
    }

    @Override
    public List<Libro> leerAllLibros() throws Exception {
        return null;
    }

    @Override
    public List<Libro> leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        return null;
    }

    @Override
    public Libro getLibro(int id) throws Exception {
        return null;
    }
}
