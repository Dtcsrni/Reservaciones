package dao;

import javafx.scene.control.Alert;
import modelo.MySQLBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDAOimpl implements usuarioDAO{


    public ResultSet CONSULTAR(String sql) throws SQLException {//función para hacer consultas

        return MySQLBD.ConsultaSQL(sql);
    }

    public int ACTUALIZAR(String sql) throws SQLException{//función para hacer actualizaciones
        return st.executeUpdate(sql);
    }
    public void DESCONECTAR(){//función de desconexión
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean LOGIN(String id_usr, String pass){

        try {
            ResultSet rs = CONSULTAR("SELECT * FROM usuarios WHERE id_usuario='"+id_usr+"' AND contra='"+pass+"'");
            if(rs.next()){
                modelo.Usuario usr = modelo.Usuario.getInstanceUser(
                        rs.getString("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("tipo"),
                        rs.getString("contra") );

                return true;
            }

        } catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Mensaje");
            a.showAndWait();
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;


    }


    }


