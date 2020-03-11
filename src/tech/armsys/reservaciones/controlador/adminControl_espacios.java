package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;
import tech.armsys.reservaciones.modelo.dao.usuarioDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static tech.armsys.reservaciones.controlador.loginControl.usuario;

public class adminControl_espacios implements Initializable {
    //Definición de campos de texto, etiquetas y botón

    @FXML
    Button btnRegresar;
    @FXML
    Button btnAltaEsp;
    @FXML
    ProgressIndicator progIn;
    @FXML
    TextField txtNombreEsp;
    @FXML
    TextField txtLugares;
    private ObservableList list= FXCollections.observableArrayList();
    @FXML ChoiceBox<String> tipo_espacio;



    FXMLLoader loader = new FXMLLoader();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void loadData(){//carga las carreras en los choicebox
        list.removeAll(list);
        String a = "Laboratorio de Computo";
        String b = "Aula de usos Multiples";
        String c = "Sala de juicios orales";
        String d = "Sala familiar";
        list.addAll(a,b,c,d);
        tipo_espacio.getItems().addAll(list);
    }

    @FXML
    void botonRegresar(ActionEvent evt) throws IOException {
            ventanas.mostrarVentana(evt, null, "admin.fxml","PANEL DE CONTROL", "admin");
    }
    @FXML
    void altaEsp(ActionEvent evt) throws IOException, SQLException {
        Espacio espacio = new Espacio();
        espacioDAO espDAO;
        espDAO = new espacioDAOImpl();
        boolean result;

        Optional<ButtonType> resultado = alertas.mostrarAlerta("confirmacion", "alta", "Confirmación de alta de Espacio", txtNombreEsp.getText(),
                "-Nombre de Espacio: "+txtNombreEsp.getText()+"\n-Tipo de espacio: "+tipo_espacio.getValue()+"\n-Lugares: "+txtLugares.getText()+"\n");
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
           espacio.setNombre_espacio(txtNombreEsp.getText());
           espacio.setTipo_Espacio(tipo_espacio.getValue());
           espacio.setLugares(Integer.parseInt(txtLugares.getText()));
           result = espDAO.CREAR(espacio);
           if(result){
              espacio = espDAO.CONSULTAR(espacio);
               alertas.mostrarAlerta("confirmacion", "altas","Alta de espacio", "Laboratorio de computo",
                       "-Id de Espacio:"+espacio.getId_Espacio()+"-Nombre de Espacio: "+espacio.getNombre_espacio()+"\n-Tipo de espacio: "+espacio.getTipo_Espacio()+"\n-Lugares: "+espacio.getLugares()+"\n");
           }
        }

    }


}
