package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.Animaciones;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static tech.armsys.reservaciones.controlador.loginControl.usuarioToken;

public class usuarioControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML
    private Label lblNombre;

    @FXML
    private AnchorPane anchorPaneAdmin;


    FXMLLoader loader = new FXMLLoader();
    Animaciones animar = new Animaciones();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblNombre.setText(usuarioToken.getNombre());
        animar.animarDesvanecer(anchorPaneAdmin,1.0f);
    }

    @FXML
    void desconectar_Sesion(ActionEvent evt) throws Exception {
        Alertas alerta = new Alertas();
        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "logout", null, null, null);
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            ventanas.mostrarVentana(evt, null, "login.fxml","login", "usuario");
        }
    }

    @FXML
    void usuario_control_reservaciones(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario_reservar.fxml","Reservas", "usuario");
    }
    @FXML
    void usuario_control_reservas(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario_reservaciones.fxml","Reservas", "usuario");
    }
}
