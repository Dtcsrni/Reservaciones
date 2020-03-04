package tech.armsys.reservaciones.modelo;


import javafx.scene.control.Alert;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLBD {
    //Se declaran variables de conexión y declaración
    private static Connection conexion;
    private static Statement st;
    //Se define la forma de conexión a la base de datos
    private static final String server = "jdbc:mysql//localhost:3306/java";
    private static final String usr = "root";
    private static final String psw = "";


    public static void CONECTAR() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server,usr,psw);
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }


    public static <T> T ConsultaSQL(String SQL, int tipo) throws SQLException{
        T retorno = null;
        switch (tipo) {
            case 0://tipo 0 es de tipo return Result Set
                retorno = (T) st.executeQuery(SQL);
            break;
            case 1://tipo 1 es de tipo return cantidad de registros afectados
                retorno = (T) Integer.valueOf(st.executeUpdate(SQL));
                break;
            default:
                break;
        }
        return retorno;
    }

    public static void DESCONECTAR() throws SQLException {//función de desconexión
            conexion.close();
    }

    }



