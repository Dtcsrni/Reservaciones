package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class espacioDAOImpl implements espacioDAO {

    public boolean CREAR(Espacio espacio)  throws SQLException {
        int q;
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "SELECT * FROM espacios WHERE nombre_espacio = '"+espacio.getNombre_espacio()+"'";
        String sql2= "INSERT INTO espacios (nombre_espacio,tipo_espacio,lugares) VALUES ('"+espacio.getNombre_espacio()+"','"+espacio.getTipo_Espacio()+"','"+espacio.getLugares()+"')";
        ResultSet rs = conexion.ConsultaSQL(sql1,0);
        if(rs.next()){
            conexion.DESCONECTAR();
            return false;
        }else{
            q = conexion.ConsultaSQL(sql2, 1);
            if(q==0){
                conexion.DESCONECTAR();
                return false;}
        }
        conexion.DESCONECTAR();
        System.out.println("Se ha agregado un espacio exitosamente");
        return true;
    }

    public Espacio CONSULTAR(Espacio espacio) throws SQLException {//función para hacer consultas
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1 = "SELECT * FROM espacios WHERE nombre_espacio = '"+espacio.getNombre_espacio()+"'";
        ResultSet rs = conexion.ConsultaSQL(sql1,0);
        if(rs.next()){
            espacio.setId_espacio(rs.getInt("id_espacio"));
            espacio.setNombre_espacio(rs.getString("nombre_espacio"));
            espacio.setTipo_Espacio(rs.getString("tipo_espacio"));
            espacio.setLugares(rs.getInt("lugares"));
        }else{
            conexion.DESCONECTAR();
            return null;
        }
        conexion.DESCONECTAR();
        return espacio;
    }

    public List<Espacio> CONSULTAR() throws SQLException {
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        Espacio espacio = new Espacio();
        List<Espacio> listaEspacios = new ArrayList<>();;
        String sql1 = "SELECT * FROM espacios";
        ResultSet rs = conexion.ConsultaSQL(sql1,0);
        if(rs!=null){
            while(rs.next()){
                espacio.setId_espacio(rs.getInt("id_espacio"));
                espacio.setNombre_espacio(rs.getString("nombre_espacio"));
                espacio.setTipo_Espacio(rs.getString("tipo_espacio"));
                espacio.setLugares(rs.getInt("lugares"));
                listaEspacios.add(espacio);
            }
        }else{
            conexion.DESCONECTAR();
            return null;
        }
        conexion.DESCONECTAR();
        return listaEspacios;
    }

    public boolean ACTUALIZAR(Espacio espacioBase, Espacio espacioModif) throws SQLException{//función para hacer actualizaciones
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "UPDATE espacios SET id_espacio='"+null+"',nombre_espacio='"+espacioModif.getNombre_espacio()+"',tipo_espacio='"+espacioModif.getTipo_Espacio()+"',lugares='"+espacioModif.getLugares()+"' WHERE id_espacio='"+espacioBase.getId_Espacio()+"'";
        int rs = conexion.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.DESCONECTAR();
            return false;
        }
        conexion.DESCONECTAR();
        return true;
    }

    public boolean BORRAR(Espacio espacio) throws SQLException{
        MySQLBD conexion = new MySQLBD();
        conexion.CONECTAR();
        String sql1= "DELETE FROM espacios WHERE id_espacio='"+espacio.getId_Espacio()+"'";
        int rs = conexion.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.DESCONECTAR();
            return false;
        }
        conexion.DESCONECTAR();
        return true;
    }



}
