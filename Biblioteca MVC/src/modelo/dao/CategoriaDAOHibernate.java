package modelo.dao;

import modelo.old.Categoria;

import java.util.List;

public class CategoriaDAOHibernate implements CategoriaDAO {
    @Override
    public boolean inserta(Categoria categoria) throws Exception {

        return false;
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        return false;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        return false;
    }

    @Override
    public Categoria categoria(int id) throws Exception {
        return null;
    }

    @Override
    public List<Categoria> leerAllCategorias() throws Exception {
        return null;
    }
}
