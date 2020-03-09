package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML TextField id_usuario;

    @FXML Button btnDesconectarse;

    @FXML AnchorPane ap;
    @FXML ProgressIndicator progIn;

    FXMLLoader loader = new FXMLLoader();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
           }

    @FXML
    void desconectar_Sesion(ActionEvent evt) throws IOException {
        Optional<ButtonType> resultado = alertas.mostrarAlerta("confirmacion", "logout", null, null, null);
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            ventanas.mostrarVentana(evt, null, "login.fxml","login", "admin");
            }
        }
    }
