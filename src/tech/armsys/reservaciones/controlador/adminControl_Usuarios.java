package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.Usuario;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;
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
    private TabPane tabPaneEspacios;

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
    private String nombreUsuariobase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animar.animarDesvanecer(anchorPaneUsuarios, 1.0f);
    }

    @FXML
    private void buscarUsuario(ActionEvent actionEvent) throws SQLException {
        /*
        txtNombreEspacio2.setDisable(true);
        txtLugares2.setDisable(true);
        tipo_espacio_seleccionado.setDisable(true);
        btnModif.setDisable(true);
        if(tabPaneModif.isSelected()) {
            espacio.setNombre_espacio((String)tipo_espacio2.getValue());
            nombreEspacioBase = espacio.getNombre_espacio();
            espacio = espDAO.CONSULTAR(espacio);
            txtNombreEspacio2.setDisable(false);
            txtLugares2.setDisable(false);
            tipo_espacio_seleccionado.setDisable(false);
            btnModif.setDisable(false);
            txtNombreEspacio2.setText(espacio.getNombre_espacio());
            txtLugares2.setText(Integer.toString(espacio.getLugares()));
            tipo_espacio_seleccionado.getItems().clear();
            tipoEspacios.clear();
            lista.clear();
            cargarTiposEspacio(tipoEspacios);
            lista.addAll(tipoEspacios);
            tipo_espacio_seleccionado.getItems().addAll(lista);
            tipo_espacio_seleccionado.getSelectionModel().select(lista.indexOf(espacio.getTipo_Espacio()));
            animar.animarDesvanecer(txtNombreEspacio2);
            animar.animarDesvanecer(tipo_espacio_seleccionado);
            animar.animarDesvanecer(txtLugares2);
        }
        */

    }
    @FXML
    private void buscarUsuario2(ActionEvent actionEvent) throws SQLException {
        /*
        txtNombreEspacio2.setDisable(true);
        txtLugares2.setDisable(true);
        tipo_espacio_seleccionado.setDisable(true);
        btnModif.setDisable(true);
        if(tabPaneModif.isSelected()) {
            espacio.setNombre_espacio((String)tipo_espacio2.getValue());
            nombreEspacioBase = espacio.getNombre_espacio();
            espacio = espDAO.CONSULTAR(espacio);
            txtNombreEspacio2.setDisable(false);
            txtLugares2.setDisable(false);
            tipo_espacio_seleccionado.setDisable(false);
            btnModif.setDisable(false);
            txtNombreEspacio2.setText(espacio.getNombre_espacio());
            txtLugares2.setText(Integer.toString(espacio.getLugares()));
            tipo_espacio_seleccionado.getItems().clear();
            tipoEspacios.clear();
            lista.clear();
            cargarTiposEspacio(tipoEspacios);
            lista.addAll(tipoEspacios);
            tipo_espacio_seleccionado.getItems().addAll(lista);
            tipo_espacio_seleccionado.getSelectionModel().select(lista.indexOf(espacio.getTipo_Espacio()));
            animar.animarDesvanecer(txtNombreEspacio2);
            animar.animarDesvanecer(tipo_espacio_seleccionado);
            animar.animarDesvanecer(txtLugares2);
        }
        */

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
        lista.add("Administrador");
        lista.add("Usuario");
    }

    @FXML
    private void cargarContenido(){//carga las carreras en los choicebox
        txtMatriculaUsuario1.clear();
        lista.clear();
        txtMatriculaUsuario1.clear();
        txtNombreUsuario1.clear();
        tipo_usuario.getItems().clear();
        txtPass.clear();
        txtGrupo.clear();
        cargarTiposUsuario(tipoUsuario);
        lista.addAll(tipoUsuario);
        tipo_usuario.getItems().addAll(lista);
    }
    @FXML
    private void cargarContenidoModif() throws SQLException {
        /*
        lista.clear();
        listaEspacios.clear();
        txtNombreEspacio2.clear();
        txtLugares2.clear();
        tipo_espacio2.getItems().clear();
        tipo_espacio_seleccionado.getItems().clear();
        listaEspacios= espDAO.CONSULTAR();
        lista.addAll(listaEspacios);
        tipo_espacio2.getItems().addAll(lista);
        */

    }
    @FXML
    private void cargarContenidoElim() throws SQLException {/*
        lista.clear();
        listaus.clear();
        txtNombreEspacio3.clear();
        txtLugares3.clear();
        txtTipo_espacio3.clear();
        tipo_espacio3.getItems().clear();
        listaEspacios= usDAO.CONSULTAR();
        lista.addAll(listaEspacios);
        tipo_espacio3.getItems().addAll(lista);*/
    }


    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        Ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    private void altaUsuario(ActionEvent evt) throws Exception {

        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Usuario", txtNombreUsuario1.getText(),
                "-Id de Usuario: "+txtMatriculaUsuario1.getText()+"\n-Nombre de usuario: "+txtNombreUsuario1.getText()+
                        "\n-Tipo de usuario: "+tipo_usuario.getValue()+"\n-Contraseña: "+txtPass.getText()+"\n-Grupo: "+txtGrupo.getText()+"\n");
        if (resultado.isPresent() & resultado.get() == ButtonType.YES) {
            usuario.setId_usuario(txtMatriculaUsuario1.getText());
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
        /*
        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "modificacion", "Confirmación de modificación de Espacio", nombreEspacioBase,
                "-Nombre de Espacio: "+txtNombreEspacio2.getText()+"\n-Tipo de espacio: "+(String)(tipo_espacio_seleccionado.getValue())+"\n-Lugares: "+txtLugares2.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            espacio.setNombre_espacio(txtNombreEspacio2.getText());
            espacio.setTipo_Espacio((String)tipo_espacio_seleccionado.getValue());
            espacio.setLugares(Integer.parseInt(txtLugares2.getText()));
            result = espDAO.ACTUALIZAR(nombreEspacioBase, espacio);
            if(result){
                espacio = espDAO.CONSULTAR(espacio);
                alerta.mostrarAlerta("aviso", "modificacion","Modificación de espacio satisfactoria", espacio.getTipo_Espacio(),
                        "-Id de Espacio: "+espacio.getId_Espacio()+"\n-Nombre de Espacio: "+espacio.getNombre_espacio()+"\n-Tipo de espacio: "+espacio.getTipo_Espacio()+"\n-Lugares: "+espacio.getLugares()+"\n");
                tabPaneEspacios.getSelectionModel().select(0);
                lista.clear();
                listaEspacios.clear();
                txtNombreEspacio2.clear();
                txtLugares2.clear();
                tipo_espacio2.getItems().clear();
                tipo_espacio_seleccionado.getItems().clear();
            }
            else{
                alerta.mostrarAlerta("error", "alta_existente","Error", "Error al intentar realizar registro","No se ha podido realizar el registro, por favor intente nuevamente");
            }
        }
        txtNombreEspacio2.setDisable(true);
        txtLugares2.setDisable(true);
        tipo_espacio_seleccionado.setDisable(true);
        btnModif.setDisable(true);
*/
    }

    @FXML
    private void elimUsuario() throws Exception {/*
        Optional<String> resultado = alerta.mostrarAlerta("confirmacion", "eliminacion", "Confirmación de eliminación de Espacio", txtNombreEspacio3.getText(),
                "-Nombre de Espacio: "+txtNombreEspacio3.getText()+"\n-Tipo de espacio: "+txtTipo_espacio3.getText()+"\n-Lugares: "+txtLugares3.getText()+"\n");
        if (resultado.isPresent()) {
            System.out.println(resultado.get());
            if(resultado.get().equals(txtNombreEspacio3.getText())){
                espacio.setNombre_espacio(txtNombreEspacio3.getText());
                espacio.setTipo_Espacio(txtTipo_espacio3.getText());
                espacio.setLugares(Integer.parseInt(txtLugares3.getText()));
                result = espDAO.BORRAR(espacio);
                if (result) {
                    alerta.mostrarAlerta("aviso", "eliminacion", "Eliminación de espacio satisfactoria", espacio.getTipo_Espacio(),
                            "-Id de Espacio: " + espacio.getId_Espacio() + "\n-Nombre de Espacio: " + espacio.getNombre_espacio() + "\n-Tipo de espacio: " + espacio.getTipo_Espacio() + "\n-Lugares: " + espacio.getLugares() + "\n");
                    tabPaneEspacios.getSelectionModel().select(0);
                    lista.clear();
                    listaEspacios.clear();
                    txtNombreEspacio3.clear();
                    txtLugares3.clear();
                } else {
                    alerta.mostrarAlerta("error", "alta_existente", "Error", "Error al intentar realizar registro", "No se ha podido realizar el registro, por favor intente nuevamente");
                    tabPaneEspacios.getSelectionModel().select(0);
                }
            }
            else {
                alerta.mostrarAlerta("error", "eliminacionfallida", null,null,null);
                tabPaneEspacios.getSelectionModel().select(0);
            }
        }
        else {
            alerta.mostrarAlerta("error", "eliminacionfallida", null,null,null);
            tabPaneEspacios.getSelectionModel().select(0);
        }
        btnBaja.setDisable(true);
    */}



}
