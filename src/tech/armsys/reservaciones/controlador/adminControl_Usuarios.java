package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.Animaciones;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Usuario;
import tech.armsys.reservaciones.modelo.dao.usuarioDAO;
import tech.armsys.reservaciones.modelo.dao.usuarioDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminControl_Usuarios implements Initializable {

    @FXML
    private AnchorPane anchorPaneUsuarios;

    @FXML
    private TabPane tabPaneUsuarios;

    @FXML
    private TextField txtNombreUsuario1;

    @FXML
    private ChoiceBox<?> tipo_usuario;

    @FXML
    private TextField txtMatriculaUsuario1;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtGrupo;

    @FXML
    private Tab tabPaneModif;

    @FXML
    private Button btnModif;

    @FXML
    private TextField txtNombreUsuario2;

    @FXML
    private TextField txtMatriculaUsuario2;

    @FXML
    private ChoiceBox<?> tipo_usuario2;

    @FXML
    private PasswordField txtpass2;

    @FXML
    private TextField txtGrupo2;

    @FXML
    private TextField txtIdUsuarioBuscar;

    @FXML
    private Button btnBuscar1;

    @FXML
    private Tab tabPaneElim;

    @FXML
    private Button btnBaja;

    @FXML
    private TextField txtNombreUsuario3;

    @FXML
    private TextField txtMatriculaUsuario3;

    @FXML
    private TextField txtTipoUsuario3;

    @FXML
    private TextField txtGrupo3;

    @FXML
    private TextField txtUsuarioBuscar3;

    @FXML
    private Button btnModif11;

    private ObservableList lista= FXCollections.observableArrayList();

    private Alertas alerta = new Alertas();
    private Usuario usuario = new Usuario();
    private Animaciones animar = new Animaciones();
    private usuarioDAO usDAO= new usuarioDAOImpl();
    private boolean result;
    private List<String> tipoUsuario = new ArrayList<>();
    private long idUsuarioBase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animar.animarDesvanecer(anchorPaneUsuarios, 1.0f);
    }

    @FXML
    private void buscarUsuario(ActionEvent actionEvent) throws SQLException {

        txtMatriculaUsuario2.setDisable(true);
        txtNombreUsuario2.setDisable(true);
        tipo_usuario2.setDisable(true);
        txtpass2.setDisable(true);
        txtGrupo2.setDisable(true);
        btnModif.setDisable(true);
        tipo_usuario2.setDisable(true);

        if(tabPaneModif.isSelected()) {

            usuario.setId_usuario(Integer.parseInt(txtIdUsuarioBuscar.getText()));
            usuario = usDAO.CONSULTAR(usuario);

            if(usuario==null){
                alerta.mostrarAlerta("error","busquedafallida",null,null,null);
            }
            else {
                idUsuarioBase = usuario.getId_Usuario();
                txtMatriculaUsuario2.setDisable(false);
                txtNombreUsuario2.setDisable(false);
                tipo_usuario2.setDisable(false);
                txtpass2.setDisable(false);
                txtGrupo2.setDisable(false);
                btnModif.setDisable(false);

                txtMatriculaUsuario2.setText(String.valueOf(usuario.getId_Usuario()));
                txtNombreUsuario2.setText(usuario.getNombre());
                txtpass2.setText(usuario.getContra());
                txtGrupo2.setText(usuario.getGrupo());
                tipoUsuario.clear();

                lista.clear();
                cargarTiposUsuario(tipoUsuario);
                lista.addAll(tipoUsuario);
                tipo_usuario2.getItems().clear();
                tipo_usuario2.getItems().addAll(lista);
                tipo_usuario2.getSelectionModel().select(lista.indexOf(usuario.getTipoUsuario()));
                animar.animarDesvanecer(txtMatriculaUsuario2);
                animar.animarDesvanecer(txtNombreUsuario2);
                animar.animarDesvanecer(tipo_usuario2);
                animar.animarDesvanecer(txtpass2);
                animar.animarDesvanecer(txtGrupo2);
            }
        }


    }
    @FXML
    private void buscarUsuario2(ActionEvent actionEvent) throws SQLException {

        txtMatriculaUsuario3.setDisable(true);
        txtNombreUsuario3.setDisable(true);
        txtTipoUsuario3.setDisable(true);
        txtGrupo3.setDisable(true);
        btnBaja.setDisable(true);

        if(tabPaneElim.isSelected()) {
            usuario.setId_usuario(Integer.parseInt(txtUsuarioBuscar3.getText()));
            usuario = usDAO.CONSULTAR(usuario);
            txtMatriculaUsuario3.setDisable(false);
            txtNombreUsuario3.setDisable(false);
            txtTipoUsuario3.setDisable(false);
            txtGrupo3.setDisable(false);
            btnBaja.setDisable(false);
            txtNombreUsuario3.setText(usuario.getNombre());
            txtMatriculaUsuario3.setText(Long.toString(usuario.getId_Usuario()));
            txtTipoUsuario3.setText(usuario.getTipoUsuario());
            txtGrupo3.setText(usuario.getGrupo());
            lista.clear();
            animar.animarDesvanecer(txtNombreUsuario3);
            animar.animarDesvanecer(txtMatriculaUsuario3);
            animar.animarDesvanecer(txtGrupo3);
            animar.animarDesvanecer(txtTipoUsuario3);
        }


    }
    /*
    @FXML
    private void elimChoiceSeleccionado(ActionEvent actionEvent) throws SQLException {
        btnBaja.setDisable(true);
        if(tabPaneElim.isSelected()) {
            espacio.setNombre_espacio((String)tipo_espacio3.getValue());
            espacio = espDAO.CONSULTAR(espacio);
            txtNombreEspacio3.setText(espacio.getNombre_espacio());
            txtLugares3.setText(Integer.toString(espacio.getLugares()));
            txtTipo_espacio3.setText(espacio.getTipo_Espacio());
            txtNombreEspacio3.setDisable(true);
            txtLugares3.setDisable(true);
            txtLugares3.setDisable(true);
            btnBaja.setDisable(false);
            animar.animarDesvanecer(txtNombreEspacio3);
            animar.animarDesvanecer(txtTipo_espacio3);
            animar.animarDesvanecer(txtLugares3);
        }
    }*/
    private void cargarTiposUsuario(List lista){
        lista.clear();
        lista.add("Administrador");
        lista.add("Usuario");
    }

    @FXML
    private void cargarContenido(){//carga las carreras en los choicebox
        txtMatriculaUsuario1.clear();
        txtMatriculaUsuario1.clear();
        txtNombreUsuario1.clear();
        txtPass.clear();
        txtGrupo.clear();
        tipo_usuario.getItems().clear();
        lista.clear();
        cargarTiposUsuario(tipoUsuario);
        lista.addAll(tipoUsuario);
        tipo_usuario.getItems().addAll(lista);
    }
    @FXML
    private void cargarContenidoModif() throws SQLException {
        txtMatriculaUsuario2.setDisable(true);
        txtNombreUsuario2.setDisable(true);
        tipo_usuario2.setDisable(true);
        txtpass2.setDisable(true);
        txtGrupo2.setDisable(true);
        btnModif.setDisable(true);

        tipo_usuario2.getItems().clear();
        txtMatriculaUsuario2.clear();
        txtNombreUsuario2.clear();
        txtpass2.clear();
        txtGrupo2.clear();

        lista.clear();
        tipo_usuario2.getItems().clear();
        cargarTiposUsuario(tipoUsuario);
        lista.addAll(tipoUsuario);
        tipo_usuario2.getItems().addAll(lista);


    }
    @FXML
    private void cargarContenidoElim() throws SQLException {
        lista.clear();
        txtNombreUsuario3.clear();
        txtGrupo3.clear();
        txtMatriculaUsuario3.clear();
        txtTipoUsuario3.clear();
    }


    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    private void botonLimpiar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin_control_usuarios.fxml","Control de Usuarios", "admin");
    }
    @FXML
    private void altaUsuario(ActionEvent evt) throws Exception {

        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Usuario", txtNombreUsuario1.getText(),
                "-Id de Usuario: "+txtMatriculaUsuario1.getText()+"\n-Nombre de usuario: "+txtNombreUsuario1.getText()+
                        "\n-Tipo de usuario: "+tipo_usuario.getValue()+"\n-Contraseña: "+txtPass.getText()+"\n-Grupo: "+txtGrupo.getText()+"\n");
        if (resultado.isPresent() & resultado.get() == ButtonType.YES) {
            usuario.setId_usuario(Integer.parseInt(txtMatriculaUsuario1.getText()));
            usuario.setNombre(txtNombreUsuario1.getText());
            usuario.setTipoUsuario((String)tipo_usuario.getValue());
            usuario.setContra(txtPass.getText());
            usuario.setGrupo(txtGrupo.getText());

            result = usDAO.CREAR(usuario);
            if(result){
                usuario = usDAO.CONSULTAR(usuario);
                alerta.mostrarAlerta("aviso", "alta","Alta de usuario satisfactoria", usuario.getNombre(),
                        "-Id de Usuario: "+usuario.getId_Usuario()+"\n-Nombre de Usuario: "+usuario.getNombre()+"\n-Tipo de usuario: "+usuario.getTipoUsuario()+"\n-Contraseña: "
                                +usuario.getContra()+"\n-Grupo: "+usuario.getGrupo()+"\n");
                tipoUsuario.clear();
                lista.clear();
                txtMatriculaUsuario1.clear();
                txtNombreUsuario1.clear();
                txtPass.clear();
                txtGrupo.clear();
                tipo_usuario.getItems().clear();
            }
            else{
                alerta.mostrarAlerta("error", "alta_existente","Error", "Error al intentar realizar registro","No se ha podido realizar el registro, por favor intente nuevamente");
            }
        }

    }
    @FXML
    private void modifUsuario(ActionEvent evt) throws Exception {

        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "modificacion", "Confirmación de modificación de Usuario", String.valueOf(idUsuarioBase),
                "-Matricula de Usuario: "+txtMatriculaUsuario2.getText()+"\n-Nombre de usuario: "+txtNombreUsuario2.getText()+"\n-Tipo de usuario: "+tipo_usuario2.getValue()+"\n-Contraseña de usuario: "+txtpass2.getText()
                        +"\n-Grupo de usuario: "+txtGrupo2.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            usuario.setId_usuario(Integer.parseInt(txtMatriculaUsuario2.getText()));
            usuario.setNombre(txtNombreUsuario2.getText());
            usuario.setTipoUsuario((String)tipo_usuario2.getValue());
            usuario.setContra(txtpass2.getText());
            usuario.setGrupo(txtGrupo2.getText());
            result = usDAO.ACTUALIZAR(idUsuarioBase, usuario);
            if(result){
                usuario = usDAO.CONSULTAR(usuario);
                alerta.mostrarAlerta("aviso", "modificacion","Modificación de usuario satisfactoria", usuario.getTipoUsuario(),
                        "-Matricula de Usuario: "+txtMatriculaUsuario2.getText()+"\n-Nombre de usuario: "+txtNombreUsuario2.getText()+"\n-Tipo de usuario: "+tipo_usuario2.getValue()+"\n-Contraseña de usuario: "+txtpass2.getText()
                                +"\n-Grupo de usuario: "+txtGrupo2.getText()+"\n");
                tabPaneUsuarios.getSelectionModel().select(0);
                lista.clear();
                txtIdUsuarioBuscar.clear();
                txtMatriculaUsuario2.clear();
                txtNombreUsuario2.clear();
                tipo_usuario2.getItems().clear();
                txtpass2.clear();
                txtGrupo2.clear();
            }
            else{
                alerta.mostrarAlerta("error", "alta_existente","Error", "Error al intentar realizar registro","No se ha podido realizar el registro, por favor intente nuevamente");
            }
        }
        txtMatriculaUsuario2.setDisable(true);
        txtNombreUsuario2.setDisable(true);
        tipo_usuario2.setDisable(true);
        txtpass2.setDisable(true);
        txtGrupo2.setDisable(true);
        btnModif.setDisable(true);

    }

    @FXML
    private void elimUsuario() throws Exception {
        Optional<String> resultado = alerta.mostrarAlerta("confirmacion", "eliminacion", "Confirmación de eliminación de Usuario", txtNombreUsuario3.getText(),
                "-Matricula de Usuario: "+txtMatriculaUsuario3.getText()+"\n-Nombre de usuario: "+txtNombreUsuario3.getText()+"\n-Tipo de usuario: "+txtTipoUsuario3.getText()
                        +"\n-Grupo de usuario: "+txtGrupo3.getText()+"\n");
        if (resultado.isPresent()) {
            System.out.println(resultado.get());
            if(resultado.get().equals(txtNombreUsuario3.getText())){
                usuario.setNombre(txtNombreUsuario3.getText());
                usuario.setTipoUsuario(txtTipoUsuario3.getText());
                result = usDAO.BORRAR(usuario);
                if (result) {
                    alerta.mostrarAlerta("aviso", "eliminacion", "Eliminación de usuario satisfactoria", usuario.getTipoUsuario(),
                            "-Id de Espacio: " + usuario.getId_Usuario() + "\n-Nombre de Espacio: " + usuario.getNombre() + "\n-Tipo de espacio: " + usuario.getTipoUsuario() + "\n-Lugares: " + usuario.getGrupo() + "\n");
                    tabPaneUsuarios.getSelectionModel().select(0);
                    lista.clear();
                    txtMatriculaUsuario3.clear();
                    txtNombreUsuario3.clear();
                    txtTipoUsuario3.clear();
                    txtGrupo3.clear();
                } else {
                    alerta.mostrarAlerta("error", "alta_existente", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");
                    tabPaneUsuarios.getSelectionModel().select(0);
                }
            }
            else {
                alerta.mostrarAlerta("error", "eliminacionfallida", null,null,null);
                tabPaneUsuarios.getSelectionModel().select(0);
            }
        }
        else {
            alerta.mostrarAlerta("error", "eliminacionfallida", null,null,null);
            tabPaneUsuarios.getSelectionModel().select(0);
        }
        btnBaja.setDisable(true);

    }



}
