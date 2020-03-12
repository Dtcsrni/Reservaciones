package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class adminControl_espacios implements Initializable {
    //Definición de campos de texto, etiquetas y botón
    @FXML
    private ProgressIndicator progIn;
    @FXML
    private TextField txtNombreEspacio;
    @FXML
    private TextField txtLugares;
    private ObservableList lista= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> tipo_espacio;
    @FXML
    private ChoiceBox<String>  espacios_en_BD;

    private FXMLLoader loader = new FXMLLoader();
    private Alertas alerta = new Alertas();
    private Espacio espacio = new Espacio();
    private espacioDAO espDAO= new espacioDAOImpl();
    private boolean result;

    private List<String> tipoEspacios = new ArrayList<>();
    private List<Espacio> listaEspacios = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void cargarContenido(){//carga las carreras en los choicebox
        tipoEspacios.clear();
        lista.clear();
        tipo_espacio.getItems().clear();
        tipoEspacios.add("Laboratorio de Computo");
        tipoEspacios.add("Aula de usos Multiples");
        tipoEspacios.add("Sala de juicios orales");
        tipoEspacios.add("Sala familiar");
        lista.addAll(tipoEspacios);
        tipo_espacio.getItems().addAll(lista);
         }
    @FXML
    private void cargarContenidoModif() throws SQLException {
        int numeroEspacios;
        tipoEspacios.clear();
        lista.clear();
        tipo_espacio.getItems().clear();
        listaEspacios= espDAO.CONSULTAR();
        lista.addAll(listaEspacios);
        tipo_espacio.getItems().addAll(lista);
    }


    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
            ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    private void altaEspacio(ActionEvent evt) throws IOException, SQLException, InterruptedException {

        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Espacio", txtNombreEspacio.getText(),
                "-Nombre de Espacio: "+txtNombreEspacio.getText()+"\n-Tipo de espacio: "+tipo_espacio.getValue()+"\n-Lugares: "+txtLugares.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
           espacio.setNombre_espacio(txtNombreEspacio.getText());
           espacio.setTipo_Espacio(tipo_espacio.getValue());
           espacio.setLugares(Integer.parseInt(txtLugares.getText()));
           result = espDAO.CREAR(espacio);
           if(result==true){
              espacio = espDAO.CONSULTAR(espacio);
               alerta.mostrarAlerta("aviso", "alta","Alta de espacio satisfactoria", espacio.getTipo_Espacio(),
                       "-Id de Espacio: "+espacio.getId_Espacio()+"\n-Nombre de Espacio: "+espacio.getNombre_espacio()+"\n-Tipo de espacio: "+espacio.getTipo_Espacio()+"\n-Lugares: "+espacio.getLugares()+"\n");
           }
           else{
               alerta.mostrarAlerta("error", "alta_existente","Error", "Error al intentar realizar registro","No se ha podido realizar el registro, por favor intente nuevamente");
           }
        }

    }
    @FXML
    private void modifEspacio(ActionEvent evt){

    }



}
