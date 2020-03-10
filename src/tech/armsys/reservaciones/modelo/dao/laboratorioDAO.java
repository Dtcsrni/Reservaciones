package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Laboratorio;
import tech.armsys.reservaciones.modelo.Usuario;

import java.sql.SQLException;

public interface laboratorioDAO {
    public boolean CREAR(Laboratorio usuario) throws SQLException;
    public Laboratorio CONSULTAR(Laboratorio laboratorio) throws SQLException;
    public boolean ACTUALIZAR(Laboratorio laboratorioBase, Laboratorio laboratorioModif) throws SQLException;
    public boolean BORRAR(Laboratorio laboratorio) throws SQLException;
}
