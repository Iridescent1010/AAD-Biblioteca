package modelo.dao;

import modelo.old.Prestamo;

import java.util.List;

public class PrestamoDAOHibernate implements PrestamoDAO{
    @Override
    public boolean insertar(Prestamo prestamo) throws Exception {
        return false;
    }

    @Override
    public boolean modificar(Prestamo prestamo) throws Exception {
        return false;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        return false;
    }

    @Override
    public List<Prestamo> leerAllPrestamos() throws Exception {
        return null;
    }

    @Override
    public Prestamo getPrestamo(int id) throws Exception {
        return null;
    }
}
