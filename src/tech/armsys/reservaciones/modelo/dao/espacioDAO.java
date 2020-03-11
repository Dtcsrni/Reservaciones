package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;

import java.sql.SQLException;

public interface espacioDAO {
    public boolean CREAR(Espacio usuario) throws SQLException;
    public Espacio CONSULTAR(Espacio espacio) throws SQLException;
    public boolean ACTUALIZAR(Espacio espacioBase, Espacio espacioModif) throws SQLException;
    public boolean BORRAR(Espacio espacio) throws SQLException;
}
