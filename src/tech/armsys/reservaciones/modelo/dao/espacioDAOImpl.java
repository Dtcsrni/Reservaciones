package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class espacioDAOImpl {

    public boolean CREAR(Espacio espacio)  throws SQLException {
        int q;

        String sql1= "SELECT * FROM espacios WHERE id_espacio='"+espacio.getId_Espacio()+"'";
        String sql2= "INSERT INTO espacios (id_espacio,nombre_espacio,lugares) VALUES ('"+null+"','"+espacio.getNombre_espacio()+"','"+espacio.getLugares()+"')";
        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            return false;
        }else{
            q = MySQLBD.ConsultaSQL(sql2, 0);
            if(q==0)
                return false;
        }

        return true;
    }

    public Espacio CONSULTAR(Espacio espacio) throws SQLException {//función para hacer consultas
        int q;
        String sql1 = "SELECT * FROM espacios WHERE id_usuario='"+espacio.getId_Espacio()+"'";

        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            espacio.setId_espacio(rs.getDouble("id_espacio"));
            espacio.setNombre_espacio(rs.getString("nombre_espacio"));
            espacio.setLugares(rs.getInt("lugares"));
        }else{
            return null;
        }
        return espacio;
    }

    public boolean ACTUALIZAR(Espacio espacioBase, Espacio espacioModif) throws SQLException{//función para hacer actualizaciones
        String sql1= "UPDATE espacios SET id_espacio='"+null+"',nombre_espacio='"+espacioModif.getNombre_espacio()+"',lugares='"+espacioModif.getLugares()+"' WHERE id_espacio='"+espacioBase.getId_Espacio()+"'";

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
