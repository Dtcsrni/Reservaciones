package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.Animaciones;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.conexion_MySQLBD;
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
//Atributos
    //Definición de campos de texto, etiquetas y botón
    @FXML    private TextField         id_usuario;
    @FXML    private PasswordField     txtPass;
    @FXML    private Button            btnEntrar;
    @FXML    private ProgressIndicator indicador_progreso;
    @FXML    private AnchorPane        anchorPaneLogin;

    //Se declaran los objetos de lógica de negocio
    public static Usuario    usuarioToken = new Usuario(0,"","","","");
    //Se declaran los objetos de acceso a datos
    public static usuarioDAO usDAO;
    //Se declaran las utilerías
    private Animaciones animar;
    private Alertas alerta;
    private conexion_MySQLBD conexion = new conexion_MySQLBD();
    boolean conResult=false;
 //Metodos
    public boolean getIndicador_progreso() {
     return indicador_progreso.isVisible();
 }
    public void setIndicador_progreso(boolean indicador_progreso) {
        this.indicador_progreso.setVisible(indicador_progreso);
    }
    //Se inicializa la ventana
    @Override    public void initialize(URL url, ResourceBundle rb) {

    //Se instancian las utilerías que se van a ocupar
        animar = new Animaciones();
        alerta = new Alertas();
    //Se anima la ventana al inicializarse
        animar.animarDesvanecer(anchorPaneLogin, 1.5f);
        //Se instancia el DAO
        usDAO = new usuarioDAOImpl();
           }
    //Se inicia sesión
    @FXML   private  void iniciar_sesion(ActionEvent evt) throws Exception {

        indicador_progreso.setVisible(true);

        if (id_usuario.getText().equals("") || txtPass.getText().equals("")){
            alerta.mostrarAlerta("error", "credenciales", null,null,null);
            indicador_progreso.setVisible(false);
            id_usuario.clear();
            txtPass.clear();
        }else{
         usuarioToken.setId_usuario(Integer.parseInt(id_usuario.getText()));
         usuarioToken.setContra(txtPass.getText());
         conResult = conexion.conectar();//se conecta a la BD solo para revisar que esté operativa y accesible
            if(conResult==false){
                indicador_progreso.setVisible(false);
            }


        try{
        if (conResult & usDAO.LOGIN(usuarioToken) != null) {
            usDAO.CONSULTAR(usuarioToken);
            System.out.println("Acceso concedido");

            if (usuarioToken.getTipoUsuario().equals("Administrador") ) {
                ventanas.mostrarVentana(evt, null,"admin.fxml", "PANEL DE CONTROL", "admin");
                indicador_progreso.setVisible(false);
                conexion.desconectar();
            }
            if (usuarioToken.getTipoUsuario().equals("Usuario")) {
                ventanas.mostrarVentana(evt,null, "usuario.fxml", "MENU PRINCIPAL", "usr");
                indicador_progreso.setVisible(false);
                conexion.desconectar();
            }
        } else {

            if (conResult) {
                alerta.mostrarAlerta("error", "credenciales", null,null,null);
                id_usuario.clear();
                txtPass.clear();
            }
            conexion.desconectar();
            indicador_progreso.setVisible(false);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE,null,ex);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
}
