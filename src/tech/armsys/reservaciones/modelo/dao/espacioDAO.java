package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface espacioDAO {
    public boolean CREAR(Espacio usuario) throws SQLException;
    public Espacio CONSULTAR(Espacio espacio) throws SQLException;
    public List CONSULTAR() throws SQLException;
    public boolean ACTUALIZAR(String espacioBase, Espacio espacioModif) throws SQLException;
    public boolean BORRAR(Espacio espacio) throws SQLException;
}
