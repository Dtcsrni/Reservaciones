package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    public boolean getProgIn() {
        return progIn.isVisible();
    }
    public void setProgIn(boolean progIn) {
        this.progIn.setVisible(progIn);
    }

    @FXML
    void iniciar_sesion(ActionEvent evt) {
        progIn.setVisible(true);
        MySQLBD con = new MySQLBD();
        usuario.setId_usuario(id_usuario.getText());
        usuario.setContra(txtPass.getText());
        usuarioDAO usDAO = new usuarioDAOimpl();

        boolean conResult = MySQLBD.CONECTAR();//se conecta a la BD
            if(conResult==false){
                progIn.setVisible(false);
            }

        try{
        if (conResult && usDAO.LOGIN(usuario) != null) {
            usDAO.CONSULTAR(usuario);
            System.out.println("Acceso concedido");


            if (usuario.getTipoUsuario() == 0) {
                ventanas.mostrarVentana(evt, null,"admin.fxml", "PANEL DE CONTROL", "admin");
                progIn.setVisible(false);
                MySQLBD.DESCONECTAR();
            }
            if (usuario.getTipoUsuario() == 1) {
                ventanas.mostrarVentana(evt,null, "usuario.fxml", "MENU PRINCIPAL", "usr");
                progIn.setVisible(false);
                MySQLBD.DESCONECTAR();
            }
        } else {

            if (conResult) {
                alertas.mostrarAlerta("error", "credenciales", null,null,null);
                id_usuario.clear();
                txtPass.clear();
            }
            MySQLBD.DESCONECTAR();
            progIn.setVisible(false);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE,null,ex);
        }

        }

    }
