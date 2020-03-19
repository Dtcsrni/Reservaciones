package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.conexion_MySQLBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface espacioDAO {
    //Se instancian los objetos de conexión
    public boolean CREAR(Espacio usuario) throws SQLException;
    public Espacio CONSULTAR(Espacio espacio) throws SQLException;
    public List<String> CONSULTAR_NOMBRES() throws SQLException;
    public List<Espacio> CONSULTAR() throws SQLException;
    public boolean ACTUALIZAR(String espacioBase, Espacio espacioModif) throws SQLException;
    public boolean BORRAR(Espacio espacio) throws SQLException;
}
