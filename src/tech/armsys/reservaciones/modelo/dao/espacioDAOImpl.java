package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.conexion_MySQLBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class espacioDAOImpl implements espacioDAO {

    public boolean CREAR(Espacio espacio)  throws SQLException {
        int q;
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "SELECT * FROM espacios WHERE nombre_espacio = '"+espacio.getNombre_espacio()+"'";
        String sql2= "INSERT INTO espacios (nombre_espacio,tipo_espacio,lugares) VALUES ('"+espacio.getNombre_espacio()+"','"+espacio.getTipo_Espacio()+"','"+espacio.getLugares()+"')";
        ResultSet rs = conexion.consultaSQL(sql1,0);
        if(rs.next()){
            conexion.desconectar();
            return false;
        }else{
            q = conexion.consultaSQL(sql2, 1);
            if(q==0){
                conexion.desconectar();
                return false;}
        }
        conexion.desconectar();
        System.out.println("Se ha agregado un espacio exitosamente");
        return true;
    }

    public Espacio CONSULTAR(Espacio espacio) throws SQLException {//función para hacer consultas
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1 = "SELECT * FROM espacios WHERE nombre_espacio = '"+espacio.getNombre_espacio()+"'";
        ResultSet rs = conexion.consultaSQL(sql1,0);
        if(rs.next()){
            espacio.setId_espacio(rs.getInt("id_espacio"));
            espacio.setNombre_espacio(rs.getString("nombre_espacio"));
            espacio.setTipo_Espacio(rs.getString("tipo_espacio"));
            espacio.setLugares(rs.getInt("lugares"));
        }else{
            conexion.desconectar();
            return null;
        }
        conexion.desconectar();
        return espacio;
    }

    public List<Espacio> CONSULTAR() throws SQLException {
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        List<Espacio> listaEspacios = new ArrayList<>();;
        Espacio espacio;
        String sql1 = "SELECT * FROM espacios";
        ResultSet rs = conexion.consultaSQL(sql1,0);
        if(rs!=null){
            while(rs.next()){
                espacio = new Espacio();
                espacio.setId_espacio(rs.getInt("id_espacio"));
                espacio.setNombre_espacio(rs.getString("nombre_espacio"));
                espacio.setTipo_Espacio(rs.getString("tipo_espacio"));
                espacio.setLugares(rs.getInt("lugares"));

                listaEspacios.add(espacio);
            }
        }else{
            conexion.desconectar();
            return null;
        }
        conexion.desconectar();
        return listaEspacios;
    }

    public boolean ACTUALIZAR(String espacioBase, Espacio espacioModif) throws SQLException{//función para hacer actualizaciones
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "UPDATE espacios SET nombre_espacio='"+espacioModif.getNombre_espacio()+"',tipo_espacio='"+espacioModif.getTipo_Espacio()+"',lugares='"+espacioModif.getLugares()+"' WHERE nombre_espacio='"+espacioBase+"'";
        int rs = conexion.consultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.desconectar();
            return false;
        }
        conexion.desconectar();
        return true;
    }

    public boolean BORRAR(Espacio espacio) throws SQLException{
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "DELETE FROM espacios WHERE nombre_espacio='"+espacio.getNombre_espacio()+"'";
        int rs = conexion.consultaSQL(sql1,1);
        if(rs>0){
        }else{
            conexion.desconectar();
            return false;
        }
        conexion.desconectar();
        return true;
    }



}
