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

public class adminControl_espacios implements Initializable {
    //Definición de campos de texto, etiquetas y botón

    @FXML
    Button btnRegresar;
    @FXML
    Button btnAltaEsp;
    @FXML
    ProgressIndicator progIn;
    @FXML
    TextField txtNombreEsp;
    @FXML
    TextField txtLugares;



    FXMLLoader loader = new FXMLLoader();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void botonRegresar(ActionEvent evt) throws IOException {
            ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    void altaEsp(ActionEvent evt) throws IOException {
        Optional<ButtonType> resultado = alertas.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Espacio", txtNombreEsp.getText(),
                "-Nombre de Espacio: "+txtNombreEsp.getText()+"\n-Lugares: "+txtLugares.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {


        }

    }


}
