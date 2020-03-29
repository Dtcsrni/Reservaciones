package tech.armsys.reservaciones.controlador;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.Exportaciones;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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
    @FXML
    private Label lblCantidadReservaciones;
    @FXML
    private Label lblCantidad;
    @FXML
    private Label lblMes;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnExportar;

    Exportaciones excel = new Exportaciones();
    Reserva reserva = new Reserva();
    private reservaDAO reservaDao= new reservaDAOImpl();
    Alertas alerta = new Alertas();

    int anio;
    int mes;
    String nombreMes;
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
        StringTokenizer tokenizer;
        anio = Integer.parseInt(txtAnio.getText());
        mes = (comboMeses.getSelectionModel().getSelectedIndex())+1;
        nombreMes = comboMeses.getValue().toString();

        /*TableColumn id_Columna = new TableColumn("Id de Reserva");
        id_Columna.setMinWidth(100);
        id_Columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, Integer>("id_reserva"));*/

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

        /*TableColumn lugaresDisponibles_columna = new TableColumn("Lugares Disponibles");
        lugaresDisponibles_columna.setMinWidth(100);
        lugaresDisponibles_columna.setCellValueFactory(
                new PropertyValueFactory<Reserva, Integer>("lugares_disponibles"));*/

        if (txtAnio.getLength()<4 || txtAnio.getLength()>4){//si el año no tiene exactamente 4 cifras
            alerta.mostrarAlerta("error", "falta_datos", "Campos incompletos o erroneos", "Falta información", "No se han completado correctamente todos los campos para hacer la consulta, favor de completarlos");
        }else{
            listaReservas = reservaDao.CONSULTAR_ANIO(anio,mes);
            if(listaReservas.isEmpty()){//Si no se encuentran registros
                alerta.mostrarAlerta("error", "sin_resultados", "Campos incompletos o erroneos", "Falta información", "No se han completado correctamente todos los campos para hacer la consulta, favor de completarlos");
            }else{//Si se encuentran registros en el mes y año indicados
                for(int i=0; i<listaReservas.size();i++){
                    filas.add(listaReservas.get(i));
                }
                tblReportes.setVisible(true);
                tblReportes.setItems(filas);
                tblReportes.getColumns().addAll(nombreEspacio_Columna, horario_columna, fecha_columna, nombreUsuario_columna);
                lblCantidadReservaciones.setVisible(true);
                lblCantidadReservaciones.setText(String.valueOf(listaReservas.size()));
                lblCantidad.setVisible(true);
                lblMes.setVisible(true);
                lblMes.setText(nombreMes+"-"+anio);
                btnConsultar.setDisable(true);
                txtAnio.setDisable(true);
                comboMeses.setDisable(true);
                btnLimpiar.setVisible(true);
                btnExportar.setVisible(true);
            }
        }
    }

    @FXML
    private void botonExportar() throws IOException {
        boolean resultado;
        File documentoExcel = new File(excel.excelRuta);
        Optional<ButtonType> resultado2;
        resultado = excel.exportarAExcel(listaReservas,nombreMes,anio);
        if(resultado){
        resultado2 = alerta.mostrarAlerta("confirmacion", "excel_creado", nombreMes, String.valueOf(anio), null);
            if (resultado2.isPresent() && resultado2.get() == ButtonType.YES) {
                Desktop.getDesktop().open( documentoExcel );
            }
        }
    }

    @FXML
    private void botonLimpiar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin_control_reportes.fxml","Control de Reportes", "admin");
    }

    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "admin.fxml","MENU PRINCIPAL", "admin");
    }
}
