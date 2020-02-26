package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionBD {
    //Se declaran variables de conexión y declaración
    public static Connection conexion;
    public static Statement st;
    //Se define la forma de conexión a la base de datos
    private final String server = "jdbc:mysql//localhost:3306/java";
    private final String usr = "root";
    private final String psw = "";


    public conexionBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server,usr,psw);      //Se intenta conectar al servidor usando las credenciales
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //Se crea el manejador de declaraciones sensible a scroll y en concurrencia
        } catch (Exception ex) {
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}