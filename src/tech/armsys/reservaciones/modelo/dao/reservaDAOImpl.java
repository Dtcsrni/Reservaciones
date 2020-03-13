package tech.armsys.reservaciones.modelo.dao;

import javafx.scene.control.Alert;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reservaDAOImpl implements reservaDAO{


    public boolean CREAR(Reserva reserva)  throws SQLException {
        int q;
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "SELECT * FROM reserva WHERE id_reserva='"+reserva.getId_Reserva()+"'";
        String sql2= "INSERT INTO reserva (id_reserva,laboratorio,usuario,fecha,espacio_disponible) VALUES ('"+reserva.getId_Reserva()+"','"+reserva.getLaboratorio()+"','"+reserva.getUsuario()+"','"+reserva.getFecha()+"','"+reserva.getEspacios_Disponibles()+"')";
        ResultSet rs = conexion.ConsultaSQL(sql1,0);//se revisa si el usuario ya existe primero
        if(rs.next()){//si ya existe entonces se retorna false
            conexion.DESCONECTAR();
            return false;
        }else{//si no existe entonces si se hace el insert
            q = conexion.ConsultaSQL(sql2, 1);
            if(q==0){//si no se afecta ninguna se retorna false
                conexion.DESCONECTAR();
                return false;}
        }//si se afecta alguna, se retorna true
        conexion.DESCONECTAR();
        return true;
    }

    public Reserva CONSULTAR(Reserva reserva) throws SQLException {//función para hacer consultas
        int q;
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1 = "SELECT * FROM reserva WHERE id_reserva='"+reserva.getId_Reserva()+"'";

        ResultSet rs = conexion.ConsultaSQL(sql1,0);
        if(rs.next()){
            reserva.setId_reserva(rs.getInt("id_reserva"));
            reserva.setLaboratorio(rs.getString("laboratorio"));
            reserva.setUsuario(rs.getString("usuario"));
            reserva.setFecha(rs.getString("fecha"));
            reserva.setEspacios_Disponibles(rs.getInt("espacios_disponibles"));
        }else{
            conexion.DESCONECTAR();
            return null;
        }
        conexion.DESCONECTAR();;
        return reserva;
    }

    public boolean ACTUALIZAR(int reservaBase, Reserva reservaModif) throws SQLException{//función para hacer actualizaciones
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "UPDATE reserva SET id_reserva='"+reservaModif.getId_Reserva()+"',laboratorio='"+reservaModif.getLaboratorio()+"',usuario='"+reservaModif.getUsuario()+"',fecha='"+reservaModif.getFecha()+"',espacios_disponibles='"+reservaModif.getEspacios_Disponibles()+"' WHERE id_reserva='"+reservaBase+"'";
        int rs = conexion.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.DESCONECTAR();
            return false;
        }
        conexion.DESCONECTAR();
        return true;
    }

    public boolean BORRAR(Reserva reserva) throws SQLException{
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "DELETE FROM reserva WHERE id_reserva='"+reserva.getId_Reserva()+"'";
        int rs = conexion.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        conexion.DESCONECTAR();
        return true;
    }


}


