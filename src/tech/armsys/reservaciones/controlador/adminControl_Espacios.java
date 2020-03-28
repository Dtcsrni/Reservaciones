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
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.onSpinWait;
import static java.lang.Thread.sleep;
import static javafx.beans.binding.Bindings.select;

public class adminControl_Espacios implements Initializable {
    //Definición de campos de texto, etiquetas y botón
    @FXML
    private ProgressIndicator progIn;
    @FXML
    private TextField txtNombreEspacio;
    @FXML
    private TextField txtNombreEspacio2;
    @FXML
    private TextField txtLugares;
    @FXML
    private TextField txtLugares2;
    @FXML
    private TextField txtNombreEspacio3;
    @FXML
    private TextField txtTipo_espacio3;
    @FXML
    private TextField txtLugares3;
    @FXML
    private ChoiceBox<String> tipo_espacio;
    @FXML
    private ComboBox tipo_espacio2;
    @FXML
    private ComboBox tipo_espacio3;
    @FXML
    private ChoiceBox tipo_espacio_seleccionado;
    @FXML
    private Tab tabPaneModif;
    @FXML
    private Tab tabPaneElim;
    @FXML
    private TabPane tabPaneEspacios;
    @FXML
    private AnchorPane anchorPaneEspacios;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnBaja;

    private ObservableList lista= FXCollections.observableArrayList();

    private Alertas alerta = new Alertas();
    private Espacio espacio = new Espacio();
    private espacioDAO espDAO= new espacioDAOImpl();
    private Animaciones animar = new Animaciones();
    private boolean result;
    private List<String> tipoEspacios = new ArrayList<>();
    private List<Espacio> listaEspacios = new ArrayList<>();
    private List<String> listaEspaciosNombre = new ArrayList<>();
    private List<String> listaEspaciosNombres = new ArrayList<>();
    private String nombreEspacioBase;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animar.animarDesvanecer(anchorPaneEspacios, 1.0f);
    }
    @FXML
    private void modifChoiceSeleccionado(ActionEvent actionEvent) throws SQLException {
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
            //Se anima la transición después de que se selecciona
            animar.animarDesvanecer(txtNombreEspacio2);
            animar.animarDesvanecer(tipo_espacio_seleccionado);
            animar.animarDesvanecer(txtLugares2);
        }
    }
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
    }
    private void cargarTiposEspacio(List lista){
        lista.add("Laboratorio de Computo");
        lista.add("Aula de usos Multiples");
        lista.add("Sala de juicios orales");
        lista.add("Sala familiar");
    }

    @FXML
    private void cargarContenido(){//carga las carreras en los choicebox
        tipoEspacios.clear();
        lista.clear();
        txtLugares.clear();
        txtNombreEspacio.clear();
        tipo_espacio.getItems().clear();
        cargarTiposEspacio(tipoEspacios);
        lista.addAll(tipoEspacios);
        tipo_espacio.getItems().addAll(lista);
         }
    @FXML
    private void cargarContenidoModif() throws SQLException {
        lista.clear();
        listaEspacios.clear();
        txtNombreEspacio2.clear();
        txtLugares2.clear();
        tipo_espacio2.getItems().clear();
        tipo_espacio_seleccionado.getItems().clear();
        listaEspaciosNombres.clear();
        listaEspaciosNombres= espDAO.CONSULTAR_NOMBRES();

        lista.addAll(listaEspaciosNombres);
        tipo_espacio2.getItems().addAll(lista);
    }
    @FXML
    private void cargarContenidoElim() throws SQLException {
        lista.clear();
        listaEspacios.clear();
        txtNombreEspacio3.clear();
        txtLugares3.clear();
        txtTipo_espacio3.clear();
        tipo_espacio3.getItems().clear();
        listaEspacios= espDAO.CONSULTAR();
        listaEspaciosNombre.clear();
        for(int i=0; i<listaEspacios.size(); i++){
            listaEspaciosNombre.add(listaEspacios.get(i).getNombre_espacio());
        }

        lista.addAll(listaEspaciosNombre);
        tipo_espacio3.getItems().addAll(lista);
    }


    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
            ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    private void botonLimpiar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin_control_espacios.fxml","Control de Usuarios", "admin");
    }
    @FXML
    private void altaEspacio(ActionEvent evt) throws Exception {

        if(!txtNombreEspacio.getText().isEmpty() || !txtLugares.getText().isEmpty() || tipo_espacio.getValue()!=null) {

            Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Espacio", txtNombreEspacio.getText(),
                    "-Nombre de Espacio: " + txtNombreEspacio.getText() + "\n-Tipo de espacio: " + tipo_espacio.getValue() + "\n-Lugares: " + txtLugares.getText() + "\n");
            if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
                espacio.setNombre_espacio(txtNombreEspacio.getText());
                espacio.setTipo_Espacio(tipo_espacio.getValue());
                espacio.setLugares(Integer.parseInt(txtLugares.getText()));
                result = espDAO.CREAR(espacio);
                if (result) {
                    espacio = espDAO.CONSULTAR(espacio);
                    alerta.mostrarAlerta("aviso", "alta", "Alta de espacio satisfactoria", espacio.getTipo_Espacio(),
                            "-Id de Espacio: " + espacio.getId_Espacio() + "\n-Nombre de Espacio: " + espacio.getNombre_espacio() + "\n-Tipo de espacio: " + espacio.getTipo_Espacio() + "\n-Lugares: " + espacio.getLugares() + "\n");
                    tipoEspacios.clear();
                    lista.clear();
                    txtLugares.clear();
                    txtNombreEspacio.clear();
                    tipo_espacio.getItems().clear();
                } else {
                    alerta.mostrarAlerta("error", "alta_existente", "Error", "Error al intentar realizar registro", "No se ha podido realizar el registro, por favor intente nuevamente");
                }
            }
        }else{
            alerta.mostrarAlerta("error", "falta_datos", "Error", "Error al intentar realizar registro", "Favor de llenar todos los datos");
        }
    }
    @FXML
    private void modifEspacio(ActionEvent evt) throws Exception {

        if(tipo_espacio2.getValue()!=null) {

            Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "modificacion", "Confirmación de modificación de Espacio", nombreEspacioBase,
                    "-Nombre de Espacio: " + txtNombreEspacio2.getText() + "\n-Tipo de espacio: " + (String) (tipo_espacio_seleccionado.getValue()) + "\n-Lugares: " + txtLugares2.getText() + "\n");
            if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
                espacio.setNombre_espacio(txtNombreEspacio2.getText());
                espacio.setTipo_Espacio((String) tipo_espacio_seleccionado.getValue());
                espacio.setLugares(Integer.parseInt(txtLugares2.getText()));
                result = espDAO.ACTUALIZAR(nombreEspacioBase, espacio);
                if (result) {
                    espacio = espDAO.CONSULTAR(espacio);
                    alerta.mostrarAlerta("aviso", "modificacion", "Modificación de espacio satisfactoria", espacio.getTipo_Espacio(),
                            "-Id de Espacio: " + espacio.getId_Espacio() + "\n-Nombre de Espacio: " + espacio.getNombre_espacio() + "\n-Tipo de espacio: " + espacio.getTipo_Espacio() + "\n-Lugares: " + espacio.getLugares() + "\n");
                    tabPaneEspacios.getSelectionModel().select(0);
                    lista.clear();
                    listaEspacios.clear();
                    txtNombreEspacio2.clear();
                    txtLugares2.clear();
                    tipo_espacio2.getItems().clear();
                    tipo_espacio_seleccionado.getItems().clear();
                } else {
                    alerta.mostrarAlerta("error", "alta_existente", "Error", "Error al intentar realizar registro", "No se ha podido realizar el registro, por favor intente nuevamente");
                }
            }
            txtNombreEspacio2.setDisable(true);
            txtLugares2.setDisable(true);
            tipo_espacio_seleccionado.setDisable(true);
            btnModif.setDisable(true);
        }
    }
    @FXML
    private void elimEspacio() throws Exception {

        if(tipo_espacio3.getValue()!=null) {

            Optional<String> resultado = alerta.mostrarAlerta("confirmacion", "eliminacion", "Confirmación de eliminación de Espacio", txtNombreEspacio3.getText(),
                    "-Nombre de Espacio: " + txtNombreEspacio3.getText() + "\n-Tipo de espacio: " + txtTipo_espacio3.getText() + "\n-Lugares: " + txtLugares3.getText() + "\n");
            if (resultado.isPresent()) {
                System.out.println(resultado.get());
                if (resultado.get().equals(txtNombreEspacio3.getText())) {
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
                } else {
                    alerta.mostrarAlerta("error", "eliminacionfallida", null, null, null);
                    tabPaneEspacios.getSelectionModel().select(0);
                }
            } else {
                alerta.mostrarAlerta("error", "eliminacionfallida", null, null, null);
                tabPaneEspacios.getSelectionModel().select(0);
            }
            btnBaja.setDisable(true);
        }
    }



}
