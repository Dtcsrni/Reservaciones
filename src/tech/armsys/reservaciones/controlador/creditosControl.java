package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class creditosControl implements Initializable {



    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "login.fxml","ACCESO", "admin");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
