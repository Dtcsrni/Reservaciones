package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;
import tech.armsys.reservaciones.modelo.dao.usuarioDAO;
import tech.armsys.reservaciones.modelo.dao.usuarioDAOimpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class loginControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML TextField id_usuario;
    @FXML PasswordField txtPass;

    @FXML Button btnEntrar;

    @FXML AnchorPane ap;
    @FXML ProgressIndicator progIn;
    public static Usuario usuario = new Usuario();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
           }

    @FXML
    void iniciar_sesion(ActionEvent evt) throws InterruptedException {
        progIn.setVisible(true);
        MySQLBD con = new MySQLBD();

        usuario.setId_usuario(id_usuario.getText());
        usuario.setContra(txtPass.getText());
        usuarioDAO usDAO = new usuarioDAOimpl();

        boolean conResult = MySQLBD.CONECTAR();//se conecta a la BD

        try{

        if (conResult && usDAO.LOGIN(usuario) != null) {
            usDAO.CONSULTAR(usuario);
            System.out.println("Acceso concedido");
            FXMLLoader loader = new FXMLLoader();

            if (usuario.getTipoUsuario() == 0) {
                URL location = loginControl.class.getResource("/tech/armsys/reservaciones/vista/admin.fxml");
                loader.setLocation(location);
                VBox bp = loader.load();
                Stage stage = new Stage();
                stage.setTitle("SIRELAC | ADMINISTRADOR");
                Scene scene = new Scene(bp);
                stage.setScene(scene);
                stage.initOwner(ap.getScene().getWindow());
                ((Stage) ap.getScene().getWindow()).close();
                progIn.setVisible(false);
                stage.show();
                MySQLBD.DESCONECTAR();
            }
            if (usuario.getTipoUsuario() == 1) {
                URL location = loginControl.class.getResource("/tech/armsys/reservaciones/vista/usuario.fxml");
                loader.setLocation(location);
                VBox bp = loader.load();
                Stage stage = new Stage();
                stage.setTitle("SIRELAC | USUARIO");
                Scene scene = new Scene(bp);
                stage.setScene(scene);
                stage.initOwner(ap.getScene().getWindow());
                ((Stage) ap.getScene().getWindow()).close();
                progIn.setVisible(false);
                stage.show();
                MySQLBD.DESCONECTAR();
            }
        } else {

            if (conResult) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Credenciales incorrectas");
                error.setHeaderText("Credenciales incorrectas");
                error.setContentText("Por favor intente de nuevo");
                error.showAndWait().ifPresent((btnType) -> {
                });


                }
            MySQLBD.DESCONECTAR();
            progIn.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE,null,ex);
        } catch (IOException ex) {
            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE,null,ex);
        }

        }
    }
