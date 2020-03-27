package tech.armsys.reservaciones.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;
import static tech.armsys.reservaciones.controlador.loginControl.usuarioToken;
import static tech.armsys.reservaciones.controlador.usuarioControl_reservaciones.cantidadTotalBotones;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class usuarioControl_reservas implements Initializable {
    private Reserva reserva = new Reserva();
    private Espacio  espacioObj = new Espacio();
    private espacioDAO espacioDao = new espacioDAOImpl();
    @FXML
    private ComboBox boxReservas;
    private reservaDAO reservaDao = new reservaDAOImpl();
    private List<String> listaFechas = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();
    private List<String> listaHorarios = new ArrayList<>();
    private List<String> listaEspacios = new ArrayList<>();
    private ObservableList lista= FXCollections.observableArrayList();
    @FXML
    private Label lblNombreEspacio;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHorario;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblEspacioReservado;
    @FXML
    private Label lblFechaReservacion;
    @FXML
    private Label lblHorarioReservacion;
    @FXML
    private Label lblMensajeInicial;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelarReservacion;
    @FXML
    private TilePane tilePaneHorarios;
    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn03;
    @FXML
    private Button btn04;
    @FXML
    private Button btn05;
    @FXML
    private Button btn06;
    @FXML
    private Button btn07;
    @FXML
    private Button btn08;
    @FXML
    private Button btn09;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14;
    @FXML
    private Button btn15;
    @FXML
    private Button btn16;
    @FXML
    private Button btn17;
    @FXML
    private Button btn18;
    @FXML
    private Button btn19;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;

    private Alertas alerta = new Alertas();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarReservaciones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lblNombreUsuario.setText(usuarioToken.getNombre());
    }

    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario.fxml","MENU PRINCIPAL", "admin");
    }
    @FXML
    private void cargarReservaciones() throws SQLException {
            reserva.setNombre_usuario(usuarioToken.getNombre());
            listaReservas = reservaDao.CONSULTAR(reserva);
            if(listaReservas.isEmpty()){
                lblMensajeInicial.setText("Usted no ha hecho ninguna reservación aún");
            }
            else{

                boxReservas.setVisible(true);
                lista.clear();
                boxReservas.getItems().clear();
                for(Reserva listas: listaReservas){
                    if(!lista.contains(listas.getFecha())){
                        lista.add(listas.getFecha());
                    }
                }

                lista.addAll(listaFechas);
                boxReservas.getItems().addAll(lista);

            }

    }
    private void mostrarBotones(int cantidad){
        //For de borrado localizado de botones
        for(int i=23;cantidad<i; i--){
            switch(i){

                case 23:
                    tilePaneHorarios.getChildren().remove(btn23);

                    break;
                case 22:
                    tilePaneHorarios.getChildren().remove(btn22);

                    break;
                case 21:
                    tilePaneHorarios.getChildren().remove(btn21);

                    break;
                case 20:
                    tilePaneHorarios.getChildren().remove(btn20);

                    break;
                case 19:
                    tilePaneHorarios.getChildren().remove(btn19);

                    break;
                case 18:
                    tilePaneHorarios.getChildren().remove(btn18);

                    break;
                case 17:
                    tilePaneHorarios.getChildren().remove(btn17);

                    break;
                case 16:
                    tilePaneHorarios.getChildren().remove(btn16);

                    break;
                case 15:
                    tilePaneHorarios.getChildren().remove(btn15);

                    break;
                case 14:
                    tilePaneHorarios.getChildren().remove(btn14);

                    break;
                case 13:
                    tilePaneHorarios.getChildren().remove(btn13);

                    break;
                case 12:
                    tilePaneHorarios.getChildren().remove(btn12);

                    break;
                case 11:
                    tilePaneHorarios.getChildren().remove(btn11);

                    break;
                case 10:
                    tilePaneHorarios.getChildren().remove(btn10);

                    break;
                case 9:
                    tilePaneHorarios.getChildren().remove(btn09);

                    break;
                case 8:
                    tilePaneHorarios.getChildren().remove(btn08);

                    break;
                case 7:
                    tilePaneHorarios.getChildren().remove(btn07);

                    break;
                case 6:
                    tilePaneHorarios.getChildren().remove(btn06);

                    break;
                case 5:
                    tilePaneHorarios.getChildren().remove(btn05);

                    break;
                case 4:
                    tilePaneHorarios.getChildren().remove(btn04);

                    break;
                case 3:
                    tilePaneHorarios.getChildren().remove(btn03);

                    break;
                case 2:
                    tilePaneHorarios.getChildren().remove(btn02);

                    break;
                case 1:
                    tilePaneHorarios.getChildren().remove(btn01);

                    break;
                case 0:
                    tilePaneHorarios.getChildren().remove(btn00);

                    break;
                default:
                    break;
            }
        }
    }
    private void nombrarBotones(int cantidad, List<String> horariosLista, List<String> espaciosLista){
        for(int i=0; i<cantidad; i++){
            switch(i){
                case 0:
                    btn00.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn00.setVisible(true);
                    break;
                case 1:
                    btn01.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn01.setVisible(true);
                    break;
                case 2:
                    btn02.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn02.setVisible(true);
                    break;
                case 3:
                    btn03.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn03.setVisible(true);
                    break;
                case 4:
                    btn04.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn04.setVisible(true);
                    break;
                case 5:
                    btn05.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn05.setVisible(true);
                    break;
                case 6:
                    btn06.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn06.setVisible(true);
                    break;
                case 7:
                    btn07.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn07.setVisible(true);
                    break;
                case 8:
                    btn08.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn08.setVisible(true);
                    break;
                case 9:
                    btn09.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn09.setVisible(true);
                    break;
                case 10:
                    btn10.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn10.setVisible(true);
                    break;
                case 11:
                    btn11.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn11.setVisible(true);
                    break;
                case 12:
                    btn12.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn12.setVisible(true);
                    break;
                case 13:
                    btn13.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn13.setVisible(true);
                    break;
                case 14:
                    btn14.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn14.setVisible(true);
                    break;
                case 15:
                    btn15.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn15.setVisible(true);
                    break;
                case 16:
                    btn16.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn16.setVisible(true);
                    break;
                case 17:
                    btn17.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn17.setVisible(true);
                    break;
                case 18:
                    btn18.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn18.setVisible(true);
                    break;
                case 19:
                    btn19.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn19.setVisible(true);
                    break;
                case 20:
                    btn20.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn20.setVisible(true);
                    break;
                case 21:
                    btn21.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn21.setVisible(true);
                    break;
                case 22:
                    btn22.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn22.setVisible(true);
                    break;
                case 23:
                    btn23.setText(espaciosLista.get(i)+" | "+horariosLista.get(i));

                    btn23.setVisible(true);
                    break;

                default:
                    break;
            }

        }
    }

    @FXML
    private void fechaSeleccionada() throws SQLException {
        btnLimpiar.setVisible(true);
        for(int i=0 ;i<listaReservas.size(); i++){
            if(listaReservas.get(i).getFecha().equals(boxReservas.getValue().toString())){
                listaHorarios.add(listaReservas.get(i).getHorario());
                listaEspacios.add(listaReservas.get(i).getNombre_espacio());
            }
        }

        mostrarBotones(listaHorarios.size());
        nombrarBotones(listaHorarios.size(),listaHorarios,listaEspacios);
        boxReservas.setDisable(true);
    }

    @FXML
    private void mostrarReservaciónSeleccionada(ActionEvent evt) throws SQLException {
        String nombreEspacio;
        String nombreHorario;
        StringTokenizer tokenizer;
        List<Espacio> listaEspacios = new ArrayList<Espacio>();
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(36);//se corta el texto para eliminar lo anterior al nombre de espacio
        botonSeleccionado = botonSeleccionado.replace("'","");//se da formato
        botonSeleccionado = botonSeleccionado.replace(" | ","|");

        tokenizer = new StringTokenizer(botonSeleccionado, "|");//se divide el texto antes y después de |

        nombreEspacio = tokenizer.nextToken();//se almacena el contenido del primer token, el nombre de espacio
        nombreHorario = tokenizer.nextToken();//se almacena el contenido del segundo token, el nombre de horario




        reserva.setNombre_espacio(nombreEspacio);
        reserva.setFecha(boxReservas.getValue().toString());
        reserva.setHorario(nombreHorario);

        lblEspacioReservado.setVisible(true);
        lblFechaReservacion.setVisible(true);
        lblHorarioReservacion.setVisible(true);
        btnCancelarReservacion.setVisible(true);
        lblNombreEspacio.setVisible(true);
        lblNombreEspacio.setText(reserva.getNombre_espacio());
        lblFecha.setVisible(true);
        lblFecha.setText(reserva.getFecha());
        lblHorario.setVisible(true);
        lblHorario.setText(reserva.getHorario());
    }

    @FXML
    private void botonLimpiar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario_reservaciones.fxml","Reservaciones", "usuario");
    }

    @FXML
    private void botonCancelarReservacion(ActionEvent evt) throws SQLException, IOException {
        boolean result;



        Alertas alerta = new Alertas();
        if (!boxReservas.getSelectionModel().isEmpty()) {


            Optional<String> resultado = alerta.mostrarAlerta("confirmacion", "baja_reserva", "Confirmación de cancelación de Reserva", reserva.getNombre_espacio(),
                    "-Usuario que reserva: " + reserva.getNombre_usuario() + "\n-Espacio: " + reserva.getNombre_espacio() +
                            "\n-Horario: " + reserva.getHorario() + "\n-Fecha: " + reserva.getFecha() +"\n");
            if (resultado.isPresent()) {
                if (resultado.get().equals(reserva.getNombre_espacio())) {

                    result = reservaDao.BORRAR(reserva);
                    if (result) {
                        alerta.mostrarAlerta("aviso", "baja_reserva", "Eliminación de reserva satisfactoria", reserva.getNombre_espacio(),
                                "\n-Nombre de Espacio: " + reserva.getNombre_espacio()+"\n-Horario: "+reserva.getHorario());
                        lista.clear();
                        ventanas.mostrarVentana(evt, null, "usuario.fxml", "Reservas", "usuario");
                    } else {
                        alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");
                        ventanas.mostrarVentana(evt, null, "usuario.fxml", "Reservas", "usuario");
                    }
                } else {
                    alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");
                    ventanas.mostrarVentana(evt, null, "usuario.fxml", "Reservas", "usuario");
                }
            } else {
                alerta.mostrarAlerta("error", "baja_reserva", "Error", "Error al intentar eliminar registro", "No se ha podido eliminar el registro, por favor intente nuevamente");

            }


        }else{
            alerta.mostrarAlerta("error", "baja_reserva", "Error", "Indique una reservación a borrar e intente nuevamente", "No se ha podido borrar la reservación por que no se ha indicado una");

        }


    }
}



