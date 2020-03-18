package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.conexion_MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class usuarioDAOImpl implements usuarioDAO{


    public boolean CREAR(Usuario usuario)  throws SQLException {
        int q;
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
            String sql1= "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";
            String sql2= "INSERT INTO usuarios (id_usuario,nombre_usuario,tipo,contra, grupo) VALUES ('"+usuario.getId_Usuario()+"','"+usuario.getNombre()+"','"+usuario.getTipoUsuario()+"','"+usuario.getContra()+"','"+usuario.getGrupo()+"')";
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

    public Usuario CONSULTAR(Usuario usuario) throws SQLException {//función para hacer consultas
        int q;
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1 = "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";

            ResultSet rs = conexion.consultaSQL(sql1,0);
            if(rs.next()){
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo"));
                usuario.setContra(rs.getString("contra"));
                usuario.setGrupo(rs.getString("grupo"));
            }else{
                conexion.desconectar();
                return null;
            }
            conexion.desconectar();;
        return usuario;
    }

    public boolean ACTUALIZAR(long usuarioBase, Usuario usuarioModif) throws SQLException{//función para hacer actualizaciones
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "UPDATE usuarios SET id_usuario='"+usuarioModif.getId_Usuario()+"',nombre_usuario='"+usuarioModif.getNombre()+"',tipo='"+usuarioModif.getTipoUsuario()+"',contra='"+usuarioModif.getContra()+"',grupo='"+usuarioModif.getGrupo()+"' WHERE id_usuario='"+usuarioBase+"'";
            int rs = conexion.consultaSQL(sql1,1);
            if(rs>0){
            }else{
                conexion.desconectar();
                return false;
            }
            conexion.desconectar();
                return true;
    }

    public boolean BORRAR(Usuario usuario) throws SQLException{
        conexion_MySQLBD conexion = new conexion_MySQLBD();
        conexion.conectar();
        String sql1= "DELETE FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"'";
        int rs = conexion.consultaSQL(sql1,1);
        if(rs>0){
        }else{
            return false;
        }
        conexion.desconectar();
        return true;
    }


    public Usuario LOGIN(Usuario usuario) throws SQLException {
            conexion_MySQLBD conexion = new conexion_MySQLBD();
            conexion.conectar();

            String sql1 = "SELECT * FROM usuarios WHERE id_usuario='"+usuario.getId_Usuario()+"' AND contra='"+usuario.getContra()+"'";
            ResultSet rs = conexion.consultaSQL(sql1,0);
            if(rs.next()){
                Usuario usr = usuario.getInstanceUser(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("tipo"),
                        rs.getString("contra") ,
                        rs.getString("grupo"));
                return usr;
            }
            conexion.desconectar();
                return null;


    }

    }


