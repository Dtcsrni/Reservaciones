package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface usuarioDAO {

    public boolean CREAR(Usuario usuario) throws SQLException;
    public Usuario CONSULTAR(Usuario usuario) throws SQLException;
    public boolean ACTUALIZAR(int usuarioBase, Usuario usuarioModif) throws SQLException;
    public boolean BORRAR(Usuario usuario) throws SQLException;
    public Usuario LOGIN(Usuario usuario) throws SQLException;

}
