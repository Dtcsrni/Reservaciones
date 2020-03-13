package tech.armsys.reservaciones.modelo.dao;

import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.Reserva;

import java.sql.SQLException;
import java.util.List;

public interface reservaDAO {
    public boolean CREAR(Reserva reserva) throws SQLException;
    public Reserva CONSULTAR(Reserva espacio) throws SQLException;
    public boolean ACTUALIZAR(int reservaBase, Reserva reservaModif) throws SQLException;
    public boolean BORRAR(Reserva espacio) throws SQLException;
}