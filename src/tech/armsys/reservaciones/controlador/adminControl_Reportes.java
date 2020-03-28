package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class adminControl_Reportes implements Initializable {

    @FXML
    private TableView<Reserva> tblReportes = new TableView<Reserva>();;
    private ObservableList<Reserva> filas = FXCollections.observableArrayList();
    private List<String> lista = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<Reserva>();
    private List<String> listaAnios = new ArrayList<String>();
    @FXML
    private ComboBox comboMeses;
    @FXML
    private TextField txtAnio;
    Reserva reserva = new Reserva();
    private reservaDAO reservaDao= new reservaDAOImpl();
    Alertas alerta = new Alertas();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarMeses(lista);
    }

    private void cargarMeses(List<String> lista){

        lista.clear();//Se limpia la lista
        lista.add("Enero");//Se añaden los nombres de meses
        lista.add("Febrero");
        lista.add("Marzo");
        lista.add("Abril");
        lista.add("Mayo");
        lista.add("Junio");
        lista.add("Julio");
        lista.add("Agosto");
        lista.add("Septiembre");
        lista.add("Octubre");
        lista.add("Noviembre");
        lista.add("Diciembre");

        comboMeses.getItems().clear();//Se limpia el choicebox
        comboMeses.getItems().addAll(lista);//Se añade la lista al choicebox

    }

    @FXML
    private void botonConsultar() throws SQLException {
        int anio = 2020;
        String annio;
        StringTokenizer tokenizer;
        TableColumn id_Columna = new TableColumn("Id Reserva");
        id_Columna.setMinWidth(100);
        id_Columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, String>("id_columna"));

        TableColumn nombreEspacio_Columna = new TableColumn("Nombre de Espacio");
        nombreEspacio_Columna.setMinWidth(100);
        nombreEspacio_Columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, String>("nombre_espacio"));

        TableColumn horario_columna = new TableColumn("Horario");
        horario_columna.setMinWidth(100);
        horario_columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, String>("horario"));

        TableColumn fecha_columna = new TableColumn("Fecha");
        fecha_columna.setMinWidth(100);
        fecha_columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, String>("fecha"));

        TableColumn nombreUsuario_columna = new TableColumn("Nombre de Usuario");
        nombreUsuario_columna.setMinWidth(100);
        nombreUsuario_columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, String>("nombre_usuario"));

        if (txtAnio.getLength()<4 || txtAnio.getLength()>4){//si el año no tiene exactamente 4 cifras
            alerta.mostrarAlerta("error", "falta_datos", "Campos incompletos o erroneos", "Falta información", "No se han completado correctamente todos los campos para hacer la consulta, favor de completarlos");
        }else{
            listaReservas = reservaDao.CONSULTAR_ANIO(Integer.parseInt(txtAnio.getText()),(comboMeses.getSelectionModel().getSelectedIndex()+1));
            if(listaReservas.isEmpty()){//Si no se encuentran registros
                alerta.mostrarAlerta("error", "sin_resultados", "Campos incompletos o erroneos", "Falta información", "No se han completado correctamente todos los campos para hacer la consulta, favor de completarlos");
            }else{//Si se encuentran registros en el mes y año indicados

                firstNameCol.setMinWidth(100);
                firstNameCol.setCellValueFactory(
                        new PropertyValueFactory<Person, String>("firstName"));


                lastNameCol.setMinWidth(100);
                lastNameCol.setCellValueFactory(
                        new PropertyValueFactory<Person, String>("lastName"));

                TableColumn emailCol = new TableColumn("Email");
                emailCol.setMinWidth(200);
                emailCol.setCellValueFactory(
                        new PropertyValueFactory<Person, String>("email"));
            }
        }



    }


    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin.fxml","MENU PRINCIPAL", "admin");
    }
}
