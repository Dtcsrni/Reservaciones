package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.modelo.conexion_MySQLBD;
import tech.armsys.reservaciones.modelo.Reserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class reservaDAOImpl implements reservaDAO{


    public boolean CREAR(Reserva reserva)  throws SQLException {
        int q;
        Alertas alerta = new Alertas();
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "SELECT * FROM reserva WHERE nombre_espacio='"+reserva.getNombre_espacio()+"' AND horario='"+reserva.getHorario()+"' AND fecha='"+reserva.getFecha()+"'";
        String sql2= "INSERT INTO reserva (nombre_espacio, horario, fecha, nombre_usuario, lugares_disponibles) VALUES ('"+reserva.getNombre_espacio()+"','"+reserva.getHorario()+"','"+reserva.getFecha()+"','"+reserva.getNombre_usuario()+"','"+reserva.getLugares_Disponibles()+"')";
        ResultSet rs = conexion.consultaSQL(sql1,0);//se revisa si el usuario ya existe primero
        if(rs.next()){//si ya existe entonces se retorna false
            conexion.desconectar();
            return false;
        }else{//si no existe entonces si se hace el insert
            q = conexion.consultaSQL(sql2, 1);
            if(q==0){//si no se afecta ninguna se retorna false
                conexion.desconectar();
                return false;}
        }//si se afecta alguna, se retorna true
        conexion.desconectar();
        return true;
    }

    public List<Reserva> CONSULTAR(Reserva reserva) throws SQLException {//función para hacer consultas
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        String sql1 = "SELECT * FROM reserva WHERE nombre_usuario='"+reserva.getNombre_usuario()+"'";

        conexion.conectar();
        ResultSet rs = conexion.consultaSQL(sql1,0);
        if(rs!=null){
            while(rs.next()){
                reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setNombre_espacio(rs.getString("nombre_espacio"));
                reserva.setHorario(rs.getString("horario"));
                reserva.setFecha(rs.getString("fecha"));
                reserva.setNombre_usuario(rs.getString("nombre_usuario"));
                reserva.setLugares_disponibles(rs.getInt("lugares_disponibles"));

                listaReservas.add(reserva);
            }
        }else{
            conexion.desconectar();
            return null;
        }
        conexion.desconectar();;
        return listaReservas;
    }
    public List<Reserva> CONSULTAR_FECHA(Reserva reserva) throws SQLException {//función para hacer consultas
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        String sql1 = "SELECT * FROM reserva WHERE fecha='"+reserva.getFecha()+"'";

        conexion.conectar();
        ResultSet rs = conexion.consultaSQL(sql1,0);
        if(rs!=null){
            while(rs.next()){
                reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setNombre_espacio(rs.getString("nombre_espacio"));
                reserva.setHorario(rs.getString("horario"));
                reserva.setFecha(rs.getString("fecha"));
                reserva.setNombre_usuario(rs.getString("nombre_usuario"));
                reserva.setLugares_disponibles(rs.getInt("lugares_disponibles"));

                listaReservas.add(reserva);
            }
        }else{
            conexion.desconectar();
            return null;
        }
        conexion.desconectar();;
        return listaReservas;
    }

    public boolean ACTUALIZAR(int reservaBase, Reserva reservaModif) throws SQLException{//función para hacer actualizaciones
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "UPDATE reserva SET id_reserva='"+reservaModif.getId_Reserva()+"',nombre_espacio='"+reservaModif.getNombre_espacio()+"',horario='"+reservaModif.getHorario()+"',fecha='"+reservaModif.getFecha()+"',nombre_usuario='"+reservaModif.getNombre_usuario()+"'lugares_disponibles='"+reservaModif.getLugares_Disponibles()+"' WHERE id_reserva='"+reservaBase+"'";
        int rs = conexion.consultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.desconectar();
            return false;
        }
        conexion.desconectar();
        return true;
    }

    public boolean BORRAR(Reserva reserva) throws SQLException{
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "DELETE FROM reserva WHERE id_reserva='"+reserva.getId_Reserva()+"'";
        int rs = conexion.consultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        conexion.desconectar();
        return true;
    }


}


