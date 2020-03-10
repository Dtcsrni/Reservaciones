package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Laboratorio;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class laboratorioDAOImpl {

    public boolean CREAR(Laboratorio laboratorio)  throws SQLException {
        int q;
        int min = 00000000000;
        double max = 99999999999D;
        laboratorio.setId_lab((Math.random() * ((max - min) + 1))+min);
        String sql1= "SELECT * FROM laboratorios WHERE id_lab='"+laboratorio.getId_Lab()+"'";
        String sql2= "INSERT INTO laboratorios (id_lab,nombre_lab,no_maquinas,operativas, disponible) VALUES ('"+laboratorio.getId_Lab()+"','"+laboratorio.getNombre_lab()+"','"+laboratorio.getNo_maquinas()+"','"+laboratorio.getOperativas()+"','"+laboratorio.isDisponible()+"')";
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

    public Usuario CONSULTAR(Usuario usuario) throws SQLException {//función para hacer consultas
        int q;
        String sql1 = "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";

        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            usuario.setId_usuario(rs.getString("id_usuario"));
            usuario.setNombre(rs.getString("nombre_usuario"));
            usuario.setTipoUsuario(rs.getInt("tipo"));
            usuario.setContra(rs.getString("contra"));
        }else{
            return null;
        }
        return usuario;
    }

    public boolean ACTUALIZAR(Usuario usuarioBase, Usuario usuarioModif) throws SQLException{//función para hacer actualizaciones
        String sql1= "UPDATE usuarios SET id_usuario='"+usuarioModif.getId_Usuario()+"',nombre_usuario='"+usuarioModif.getNombre()+"',tipo='"+usuarioModif.getTipoUsuario()+"',contra='"+usuarioModif.getContra()+"' WHERE id_usuario='"+usuarioBase.getId_Usuario()+"'";

        int rs = MySQLBD.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        return true;
    }

    public boolean BORRAR(Usuario usuario) throws SQLException{
        String sql1= "DELETE FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";
        int rs = MySQLBD.ConsultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        return true;
    }


    public Usuario LOGIN(Usuario usuario) throws SQLException {

        String sql1 = "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"' AND contra='"+usuario.getContra()+"'";

        ResultSet rs = MySQLBD.ConsultaSQL(sql1,0);
        if(rs.next()){
            Usuario usr = usuario.getInstanceUser(
                    rs.getString("id_usuario"),
                    rs.getString("nombre_usuario"),
                    rs.getInt("tipo"),
                    rs.getString("contra") );

            return usr;
        }

        return null;
    }

}
