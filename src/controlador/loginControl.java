package controlador;

import dao.usuarioDAO;
import dao.usuarioDAOimpl;
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
import modelo.MySQLBD;

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

    @FXML Label lblFecha;
    @FXML Label  lblHora;

    @FXML Button btnEntrar;

    @FXML AnchorPane ap;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void iniciar_sesion(ActionEvent evt){

        try {

            MySQLBD con = new MySQLBD();
            usuarioDAO usDAO = new usuarioDAOimpl();

            ResultSet rs = usDAO.CONSULTAR("SELECT FROM admon WHERE correo='"+id_usuario.getText().trim()+"' AND contrasena='"+txtPass.getText().trim()+"'");

            if(rs.next()){

                if(usDAO.LOGIN(id_usuario.getText().trim(), txtPass.getText().trim())){

                    System.out.println("Acceso concedido");
                    Thread.sleep(500);
                    FXMLLoader loader = new FXMLLoader();
                    URL location = loginControl.class.getResource("InterfazInicio.fxml");
                    loader.setLocation(location);
                    BorderPane bp = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Menu Principal | SIICUH | Bienvenido");
                    Scene scene = new Scene(bp);
                    stage.setScene(scene);
                    stage.initOwner(ap.getScene().getWindow());
                    stage.setMaximized(true);






                    ((Stage)ap.getScene().getWindow()).close();
                    stage.show();

                }



            }else{
                System.out.println("Acceso Denegado");
                labelResultado.setText("Sus datos son incorrectos. Favor de verificar");
                labelResultado.setTextFill(Color.web("#d42319"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null,ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}


}
