package dao;

import modelo.conexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface usuarioDAO {

    public ResultSet CONSULTAR(String sql) throws SQLException;
    public int ACTUALIZAR(String sql) throws SQLException;
    public void DESCONECTAR();
    public int LOGIN(int id_usr, String pass);

}
