package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class espacioDAOImpl implements espacioDAO {

    public boolean CREAR(Espacio espacio)  throws SQLException {
        int q;

        String sql1= "SELECT * FROM espacios WHERE nombre_espacio = '"+espacio.getNombre_espacio()+"'";
        String sql2= "INSERT INTO espacios (nombre_espacio,tipo_espacio,lugares) VALUES ('"+espacio.getNombre_espacio()+"','"+espacio.getTipo_Espacio()+"','"+espacio.getLugares()+"')";
        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            return false;
        }else{
            q = MySQLBD.ConsultaSQL(sql2, 1);
            if(q==0)
                return false;
        }

        return true;
    }

    public Espacio CONSULTAR(Espacio espacio) throws SQLException {//función para hacer consultas
        int q;
        String sql1 = "SELECT * FROM espacios WHERE id_espacio ='"+espacio.getId_Espacio()+"' OR nombre_espacio = '"+espacio.getNombre_espacio()+"'";

        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            espacio.setId_espacio(rs.getInt("id_espacio"));
            espacio.setNombre_espacio(rs.getString("nombre_espacio"));
            espacio.setTipo_Espacio(rs.getString("tipo_espacio"));
            espacio.setLugares(rs.getInt("lugares"));
        }else{
            return null;
        }
        return espacio;
    }

    public boolean ACTUALIZAR(Espacio espacioBase, Espacio espacioModif) throws SQLException{//función para hacer actualizaciones
        String sql1= "UPDATE espacios SET id_espacio='"+null+"',nombre_espacio='"+espacioModif.getNombre_espacio()+"',tipo_espacio='"+espacioModif.getTipo_Espacio()+"',lugares='"+espacioModif.getLugares()+"' WHERE id_espacio='"+espacioBase.getId_Espacio()+"'";

        int rs = MySQLBD.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        return true;
    }

    public boolean BORRAR(Espacio espacio) throws SQLException{
        String sql1= "DELETE FROM espacios WHERE id_espacio='"+espacio.getId_Espacio()+"'";
        int rs = MySQLBD.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        return true;
    }



}
