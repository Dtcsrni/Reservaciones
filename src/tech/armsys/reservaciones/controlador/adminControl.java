package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static tech.armsys.reservaciones.controlador.loginControl.usuario;

public class adminControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML
    private Label lblNombre;

    @FXML
    private AnchorPane anchorPaneAdmin;

    FXMLLoader loader = new FXMLLoader();
    Animaciones animar = new Animaciones();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lblNombre.setText(usuario.getNombre());
         animar.animarDesvanecer(anchorPaneAdmin,1.0f);
           }

    @FXML
    void desconectar_Sesion(ActionEvent evt) throws Exception {
        Alertas alerta = new Alertas();
        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "logout", null, null, null);
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            Ventanas.mostrarVentana(evt, null, "login.fxml","login", "admin");
            }
        }
    @FXML
    void admin_control_espacios(ActionEvent evt) throws IOException {
        Ventanas.mostrarVentana(evt, null, "admin_control_espacios.fxml","Control de Espacios", "admin");
        }
    @FXML
    void admin_control_usuarios(ActionEvent evt) throws IOException {
        Ventanas.mostrarVentana(evt, null, "admin_control_usuarios.fxml","Control de Usuarios", "admin");
    }
    }
