package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import tech.armsys.reservaciones.modelo.Usuario;

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
    void desconectar_Sesion(ActionEvent evt) throws InterruptedException, IOException {
        URL location = loginControl.class.getResource("/tech/armsys/reservaciones/vista/admin.fxml");
        loader.setLocation(location);
        VBox bp1 = loader.load();
        Stage stage = new Stage();
        stage.setTitle("SIRELAC | ADMINISTRADOR");
        Scene scene = new Scene(bp1);
        stage.initOwner(ap.getScene().getWindow());
        ((Stage) ap.getScene().getWindow()).close();
        progIn.setVisible(false);
        stage.show();
        }
    }
