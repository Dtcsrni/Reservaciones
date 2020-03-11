package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static tech.armsys.reservaciones.controlador.loginControl.usuario;

public class adminControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML TextField id_usuario;

    @FXML Button btnDesconectarse;

    @FXML Label lblNombre;

    FXMLLoader loader = new FXMLLoader();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lblNombre.setText(usuario.getNombre());
           }

    @FXML
    void desconectar_Sesion(ActionEvent evt) throws IOException {
        Optional<ButtonType> resultado = alertas.mostrarAlerta("confirmacion", "logout", null, null, null);
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            ventanas.mostrarVentana(evt, null, "login.fxml","login", "admin");
            }
        }
    @FXML
    void admin_control_espacios(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin_control_espacios.fxml","Control de Espacios", "admin");
        }
    }
