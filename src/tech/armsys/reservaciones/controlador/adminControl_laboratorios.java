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

public class adminControl_laboratorios implements Initializable {
    //Definición de campos de texto, etiquetas y botón

    @FXML
    Button btnRegresar;
    @FXML
    Button btnAltaLaboratorio;
    @FXML
    ProgressIndicator progIn;
    @FXML
    TextField txtNombreLab;
    @FXML
    TextField txtNoMaquinas;
    @FXML
    TextField txtMaquinasOperativas;


    FXMLLoader loader = new FXMLLoader();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void botonRegresar(ActionEvent evt) throws IOException {
            ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    void altaLaboratorio(ActionEvent evt) throws IOException {
        Optional<ButtonType> resultado = alertas.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de laboratorio", txtNombreLab.getText(),
                "-Nombre de laboratorio: "+txtNombreLab.getText()+"\n-No.Maquinas: "+txtNoMaquinas.getText()+"\n-Maquinas operativas: "+txtMaquinasOperativas.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {


        }

    }


}
