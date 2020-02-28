package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface usuarioDAO {

    public ResultSet CONSULTAR(String sql) throws SQLException;
    public int ACTUALIZAR(String sql) throws SQLException;
    public void DESCONECTAR();
    public boolean LOGIN(String id_usr, String pass);

}
