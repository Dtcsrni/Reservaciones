package tech.armsys.reservaciones.modelo;


import tech.armsys.reservaciones.controlador.utilitarias.Alertas;

import java.sql.*;

public class conexion_MySQLBD {
    //Se declaran variables de conexión y declaración
    private Connection conexion;
    private Statement st;
    //Se define la forma de conexión a la base de datos
    private String server = "jdbc:mysql://localhost:3306/reservacion";
    private String usr = "root";
    private String psw = "";


    public boolean conectar() {
        Alertas alerta = new Alertas();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(server,usr,psw);
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            alerta.mostrarAlerta("error", "BD", null,null,"La clase de la BD no se ha encontrado");
        } catch (SQLException e) {
            e.printStackTrace();
            alerta.mostrarAlerta("error", "BD", null,null,"La base de datos no se ha inicializado o no existe");
        }
        if(conexion!=null){
            return true;
        }
        else{
            return false;
        }
    }


    public <T> T consultaSQL(String SQL, int tipo) throws SQLException {
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

    public void desconectar() throws SQLException {//función de desconexión
            conexion.close();
    }

    }



