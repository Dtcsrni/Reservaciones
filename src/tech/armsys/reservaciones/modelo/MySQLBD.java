package tech.armsys.reservaciones.modelo;


import javafx.scene.control.Alert;
import tech.armsys.reservaciones.controlador.alertas;
import tech.armsys.reservaciones.controlador.loginControl;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class MySQLBD {
    //Se declaran variables de conexión y declaración
    private static Connection conexion;
    private static Statement st;
    //Se define la forma de conexión a la base de datos
    private static  String server = "jdbc:mysql://localhost:3306/reservacion";
    private static  String usr = "root";
    private static  String psw = "";


    public static boolean CONECTAR() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(server,usr,psw);
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            alertas.mostrarAlerta("error", "BD", null,null,"La clase de la BD no se ha encontrado");
        } catch (SQLException e) {
            e.printStackTrace();
            alertas.mostrarAlerta("error", "BD", null,null,"La base de datos no se ha inicializado o no existe");
        }
        if(conexion!=null){
            return true;
        }
        else
            return false;
    }


    public static <T> T ConsultaSQL(String SQL, int tipo) {
        T retorno = null;
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static void DESCONECTAR() throws SQLException {//función de desconexión
            conexion.close();
    }

    }



