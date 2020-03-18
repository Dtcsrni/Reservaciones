package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;
import static tech.armsys.reservaciones.controlador.loginControl.usuarioToken;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class usuarioControl_reservas implements Initializable {
    private Reserva reserva = new Reserva();
    @FXML
    private ComboBox boxReservas;
    private reservaDAO reservaDao = new reservaDAOImpl();
    private List<String> listaFechas = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();
    private ObservableList lista= FXCollections.observableArrayList();
    @FXML
    private Label lblNombreEspacio;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHorario;
    private Alertas alerta = new Alertas();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarReservaciones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario.fxml","MENU PRINCIPAL", "admin");
    }
    @FXML
    private void cargarReservaciones() throws SQLException {
            reserva.setNombre_usuario(usuarioToken.getNombre());
            listaFechas = reservaDao.CONSULTAR_FECHAS(reserva);
            lista.clear();
            boxReservas.getItems().clear();
            lista.addAll(listaFechas);
            boxReservas.getItems().addAll(lista);
    }
    @FXML
    private void fechaSeleccionada() throws SQLException {
        reserva.setFecha(boxReservas.getValue().toString());
        reserva =  reservaDao.CONSULTAR_POR_FECHA(reserva);
        lblNombreEspacio.setVisible(true);
        lblNombreEspacio.setText(reserva.getNombre_espacio());
        lblFecha.setVisible(true);
        lblFecha.setText(reserva.getFecha());
        lblHorario.setVisible(true);
        lblHorario.setText(reserva.getHorario());
    }
    @FXML
    private void botonCancelarReservacion(ActionEvent evt) throws SQLException, IOException {

        boolean result;
        Alertas alerta = new Alertas();
        reserva.setNombre_espacio(reserva.getNombre_espacio());
        reserva.setNombre_usuario(usuarioToken.getNombre());


        Optional<String> resultado = alerta.mostrarAlerta("confirmacion", "baja_reserva", "Confirmación de cancelación de Reserva", reserva.getNombre_espacio(),
                "-Usuario que reserva: "+reserva.getNombre_usuario()+"\n-Espacio: "+reserva.getNombre_espacio()+
                        "\n-Horario: "+reserva.getHorario()+"\n-Fecha: "+reserva.getFecha()+"\n-Lugares disponibles: "+reserva.getLugares_Disponibles()+"\n");
        if (resultado.isPresent()) {
            if(resultado.get().equals(reserva.getNombre_espacio())){

                result = reservaDao.BORRAR(reserva);
                if (result) {
                    alerta.mostrarAlerta("aviso", "baja_reserva", "Eliminación de reserva satisfactoria", reserva.getNombre_espacio(),
                            "-Id de reserva: " + reserva.getId_Reserva() + "\n-Nombre de Espacio: " + reserva.getNombre_espacio());
                    lista.clear();
                    ventanas.mostrarVentana(evt, null, "usuario.fxml","Reservas", "usuario");
                } else {
                    alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");
                    ventanas.mostrarVentana(evt, null, "usuario.fxml","Reservas", "usuario");
                }
            }
            else {
                alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");
                ventanas.mostrarVentana(evt, null, "usuario.fxml","Reservas", "usuario");
            }
        }
        else {
            alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");

        }



    }
}



