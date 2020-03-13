package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.modelo.MySQLBD;
import tech.armsys.reservaciones.modelo.Usuario;
import tech.armsys.reservaciones.modelo.dao.usuarioDAO;
import tech.armsys.reservaciones.modelo.dao.usuarioDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class loginControl implements Initializable{
    //Definición de campos de texto, etiquetas y botón
    @FXML
    private TextField id_usuario;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnEntrar;
    @FXML
    private ProgressIndicator progIn;
    public static Usuario usuario = new Usuario();
    public static usuarioDAO usDAO;
    @FXML
    private AnchorPane anchorPaneLogin;
    Animaciones animar = new Animaciones();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animar.animarDesvanecer(anchorPaneLogin, 1.5f);
           }

    public boolean getProgIn() {
        return progIn.isVisible();
    }
    public void setProgIn(boolean progIn) {
        this.progIn.setVisible(progIn);
    }

    @FXML
    void iniciar_sesion(ActionEvent evt) throws Exception {
        Alertas alerta = new Alertas();
        progIn.setVisible(true);
        MySQLBD conexion = new MySQLBD();
        usuario.setId_usuario(Integer.parseInt(id_usuario.getText()));
        usuario.setContra(txtPass.getText());
        usDAO = new usuarioDAOImpl();

        boolean conResult = conexion.CONECTAR();//se conecta a la BD
            if(conResult==false){
                progIn.setVisible(false);
            }

        try{
        if (conResult && usDAO.LOGIN(usuario) != null) {
            usDAO.CONSULTAR(usuario);
            System.out.println("Acceso concedido");

            if (usuario.getTipoUsuario().equals("Administrador") ) {
                Ventanas.mostrarVentana(evt, null,"admin.fxml", "PANEL DE CONTROL", "admin");
                progIn.setVisible(false);
            }
            if (usuario.getTipoUsuario().equals("Usuario")) {
                Ventanas.mostrarVentana(evt,null, "usuario.fxml", "MENU PRINCIPAL", "usr");
                progIn.setVisible(false);
                conexion.DESCONECTAR();
            }
        } else {

            if (conResult) {
                alerta.mostrarAlerta("error", "credenciales", null,null,null);
                id_usuario.clear();
                txtPass.clear();
            }
            progIn.setVisible(false);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE,null,ex);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
