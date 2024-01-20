package modelo.dao.helper;

import modelo.Categoria;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.*;

import java.util.List;

public class Entidades {
    public static Categoria categoria(int id){
        try{
            return new CategoriaDAOHibernate().categoria(id);
        }
        catch (Exception e) {
            return null;
        }
    }
    public static List<Categoria> leerAllCategorias(){
        try{
            return new CategoriaDAOHibernate().leerAllCategorias();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Libro libro(int id){
        try{
            return new LibroDAOHibernate().getLibro(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static List<Libro> leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) {
        try{
            return new LibroDAOHibernate().leerLibrosOR(id,titulo,autor,editorial,categoria);
        }
        catch (Exception e) {
            return null;
        }
    }
    public static List<Libro> leerAllLibros(){
        try{
            return new LibroDAOHibernate().leerAllLibros();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Usuario usuario(int id){
        try {
            return new UsuarioDAOHibernate().getUsuario(id);
        } catch (Exception e)  {
            return null;
        }
    }
    public static List<Usuario> leerAllUsuarios() {
        try {
            return new UsuarioDAOHibernate().leerAllUsuarios();
        } catch (Exception e)  {
            return null;
        }
    }
    public static List<Usuario> leerUsuariosOR(int id,String nombre,String apellidos){
        try {
            return new UsuarioDAOHibernate().leerUsuariosOR(id,nombre,apellidos);
        } catch (Exception e)  {
            return null;
        }
    }

    public static Prestamo prestamo(int id){
        try {
            return new PrestamoDAOHibernate().getPrestamo(id);
        } catch (Exception e)  {
            return null;
        }
    }
    public static List<Prestamo> leerAllPrestamos(){
        try {
            return new PrestamoDAOHibernate().leerAllPrestamos();
        } catch (Exception e)  {
            return null;
        }
    }
}
