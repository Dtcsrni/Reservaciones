package dao;

import modelo.conexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modelo.conexionBD.conexion;
import static modelo.conexionBD.st;

public class usuarioDAOimplements implements usuarioDAO{


    public ResultSet CONSULTAR(String sql) throws SQLException {//función para hacer consultas
        return st.executeQuery(sql);
    }

    public int ACTUALIZAR(String sql) throws SQLException{//función para hacer actualizaciones
        return st.executeUpdate(sql);
    }
    public void DESCONECTAR(){//función de desconexión
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int LOGIN(int id_usr, String pass){

    }

}
