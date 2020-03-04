package tech.armsys.reservaciones.controlador;

import tech.armsys.reservaciones.modelo.Usuario;
import tech.armsys.reservaciones.modelo.dao.usuarioDAO;
import tech.armsys.reservaciones.modelo.dao.usuarioDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tech.armsys.reservaciones.modelo.MySQLBD;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginControl implements Initializable {
    //Definición de campos de texto, etiquetas y botón
    @FXML TextField id_usuario;
    @FXML PasswordField txtPass;

    @FXML Button btnEntrar;

    @FXML AnchorPane ap;
    @FXML Label lblError;

    MySQLBD con = new MySQLBD();
    usuarioDAO usDAO = new usuarioDAOimpl();
    Usuario usuario = new Usuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void iniciar_sesion(ActionEvent evt) throws ClassNotFoundException, SQLException, InterruptedException, IOException {

        usuario.setId_usuario(String.valueOf(id_usuario));
        usuario.setContra(String.valueOf(txtPass));

            MySQLBD.CONECTAR();//se conecta a la BD

                if(usDAO.LOGIN(usuario) !=null){

                    System.out.println("Acceso concedido");
                    Thread.sleep(500);
                    FXMLLoader loader = new FXMLLoader();

                    if(usuario.getTipoUsuario().equals(0)) {
                        lblError.isDisabled();
                        URL location = loginControl.class.getResource("admin.fxml");
                        loader.setLocation(location);
                        BorderPane bp = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("SIRELAC | ADMINISTRADOR");
                        Scene scene = new Scene(bp);
                        stage.setScene(scene);
                        stage.initOwner(ap.getScene().getWindow());
                        stage.setMaximized(true);
                        ((Stage)ap.getScene().getWindow()).close();
                        stage.show();
                    }
                    if(usuario.getTipoUsuario().equals(1)) {
                        lblError.isDisabled();
                        URL location = loginControl.class.getResource("usuario.fxml");
                        loader.setLocation(location);
                        BorderPane bp = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("SIRELAC | USUARIO");
                        Scene scene = new Scene(bp);
                        stage.setScene(scene);
                        stage.initOwner(ap.getScene().getWindow());
                        stage.setMaximized(true);
                        ((Stage)ap.getScene().getWindow()).close();
                        stage.show();
                    }
            else{
                System.out.println("Acceso Denegado");
                lblError.isVisible();

            }


    }

}


}
