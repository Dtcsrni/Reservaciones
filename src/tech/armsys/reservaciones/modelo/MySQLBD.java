package tech.armsys.reservaciones.modelo;


import javafx.scene.control.Alert;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class MySQLBD {
    //Se declaran variables de conexión y declaración
    private static Connection conexion;
    private static Statement st;
    //Se define la forma de conexión a la base de datos
    private static final String server = "jdbc:mysql://localhost:3306/reservacion";
    private static final String usr = "root";
    private static final String psw = "";


    public static boolean CONECTAR() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        Alert error1 = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fallo en la base de datos");
        error.setHeaderText("El driver no ha cargado correctamente");
        error1.setTitle("Fallo en la base de datos");
        error1.setHeaderText("No se ha encontrado un servidor al cual conectarse");
        error1.setContentText("Por favor inicialice el servidor");
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(server,usr,psw);
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            error.showAndWait().ifPresent((btnType) -> {
            });
        } catch (SQLException e) {
            e.printStackTrace();
            error1.showAndWait().ifPresent((btnType) -> {
            });
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



