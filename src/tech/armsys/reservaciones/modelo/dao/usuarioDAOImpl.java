package tech.armsys.reservaciones.modelo.dao;

import javafx.scene.control.Alert;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDAOImpl implements usuarioDAO{


    public boolean CREAR(Usuario usuario)  throws SQLException {
        int q;
            String sql1= "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";
            String sql2= "INSERT INTO usuarios (id_usuario,nombre_usuario,tipo,contra) VALUES ('"+usuario.getId_Usuario()+"','"+usuario.getNombre()+"','"+usuario.getTipoUsuario()+"','"+usuario.getContra()+"','"+usuario.getGrupo()+"')";
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
                usuario.setGrupo(rs.getString("grupo"));
            }else{
                return null;
            }
        return usuario;
    }

    public boolean ACTUALIZAR(Usuario usuarioBase, Usuario usuarioModif) throws SQLException{//función para hacer actualizaciones
        String sql1= "UPDATE usuarios SET id_usuario='"+usuarioModif.getId_Usuario()+"',nombre_usuario='"+usuarioModif.getNombre()+"',tipo='"+usuarioModif.getTipoUsuario()+"',contra='"+usuarioModif.getContra()+"',grupo='"+usuarioModif.getGrupo()+"' WHERE id_usuario='"+usuarioBase.getId_Usuario()+"'";

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
                        rs.getString("contra") ,
                        rs.getString("grupo"));

                return usr;
            }

        return null;
    }

    }


