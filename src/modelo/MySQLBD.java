package modelo;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLBD {
    //Se declaran variables de conexión y declaración
    public static Connection conexion;
    private static Statement st;
    //Se define la forma de conexión a la base de datos
    private final String server = "jdbc:mysql//localhost:3306/java";
    private final String usr = "root";
    private final String psw = "";


    public MySQLBD(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server,usr,psw);
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (Exception ex) {
            Logger.getLogger(MySQLBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static <T> T ConsultaSQL(String SQL, int tipo) throws SQLException{
        //Case 1 es consulta, case 2 es actualización,

        switch (tipo) {
            case 0:
            return (T) st.executeQuery(SQL);
            break;
            case 1:
            return (T) Integer.valueOf(st.executeUpdate(SQL));
            break;

        }


    }



    }



}