package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import tech.armsys.reservaciones.controlador.utilitarias.Alertas;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Espacio;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.espacioDAO;
import tech.armsys.reservaciones.modelo.dao.espacioDAOImpl;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static tech.armsys.reservaciones.controlador.loginControl.usuarioToken;

public class usuarioControl_reservaciones {
    @FXML
    private TilePane tilePaneReservas;
    @FXML
    private Button btnEsp01;
    @FXML
    private Button btnEsp02;
    @FXML
    private Button btnEsp03;
    @FXML
    private Button btnEsp04;
    @FXML
    private Button btnEsp05;
    @FXML
    private Button btnEsp06;
    @FXML
    private Button btnEsp07;
    @FXML
    private Button btnEsp08;
    @FXML
    private Button btnEsp09;
    @FXML
    private Button btnEsp10;
    @FXML
    private Button btnEsp11;
    @FXML
    private Button btnEsp12;
    @FXML
    private Button btnEsp13;
    @FXML
    private Button btnEsp14;
    @FXML
    private Button btnEsp15;
    @FXML
    private Button btnEsp16;
    @FXML
    private Button btnEsp17;
    @FXML
    private Button btnEsp18;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private Label lblDisponibles;
    @FXML
    private Button btnHorario1;
    @FXML
    private Button btnHorario2;
    @FXML
    private Button btnHorario3;
    @FXML
    private Button btnHorario4;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblHorarios;
    @FXML
    private Label lblReser1;
    @FXML
    private Label lblReser2;
    @FXML
    private Label lblReser3;
    @FXML
    private Label lblNombreReser1;
    @FXML
    private Label lblFechaReser2;
    @FXML
    private Label lblHorarioReser3;
    @FXML
    private Button btnReservar;
    @FXML
    private ImageView imgFull;

    private String horario1 = "7:00-9:00 hrs";
    private String horario2 = "9:00-11:00 hrs";
    private String horario3 = "18:00-20:00 hrs";
    private String horario4 = "20:00-22:00 hrs";
    private int numeroReservasporDia = 4;

    public static int cantidadTotalBotones = 18;

    private Reserva reserva = new Reserva();
    private reservaDAO reservaDao = new reservaDAOImpl();

    private Espacio  espacioObj = new Espacio();
    private espacioDAO espacioDao = new espacioDAOImpl();

    private LocalDate fechaLocal = LocalDate.now();
    private DateTimeFormatter formato;

    private List<Reserva> listaReservas = new ArrayList<Reserva>();
    private List<Espacio> listaEspacios = new ArrayList<Espacio>();
    private List<String> listaNombresEspaciosOriginales = new ArrayList<>();

    @FXML
    void mostrarReservaciones(ActionEvent evt) throws IOException, SQLException {
        int[][] espacios;
        int[] espacio;
        int espaciosAMostrar = 0;
        int[] numeroEspacioLleno;

        Locale spanishLocale=new Locale("es", "MX");
        fechaLocal = dateReserva.getValue();
        reserva.setFecha(fechaLocal.toString());
        formato = DateTimeFormatter.ofPattern("dd, MMMM, yyyy",spanishLocale);
        fechaLocal.format(formato);

        listaReservas = reservaDao.CONSULTAR_FECHA(reserva);

        if(listaReservas.size()==0){
            listaEspacios = espacioDao.CONSULTAR();
            numeroEspacioLleno = new int[listaEspacios.size()];
            for(int i=0; i<listaEspacios.size();i++){
                if(numeroEspacioLleno[i]==0){
                    listaNombresEspaciosOriginales.add(listaEspacios.get(i).getNombre_espacio());
                }
            }
        }
        if(listaReservas.size()!=0){
            listaEspacios = espacioDao.CONSULTAR();
            espacios = new int[listaEspacios.size()][numeroReservasporDia];

            for(int i = 0; i < listaEspacios.size(); i++) {
                for (int j = 0; j < listaReservas.size(); j++){
                   if (listaEspacios.get(i).getNombre_espacio().equals(listaReservas.get(j).getNombre_espacio())) {
                        //Si el nombre de espacio en la lista de espacios obtenida es equivalente a el espacio mencionado
                        // en la reserva, entonces este espacio tiene una reserva
                        if (listaReservas.get(j).getHorario().equals(horario1)) {
                            espacios[i][0] = 1;
                        }
                        if (listaReservas.get(j).getHorario().equals(horario2)) {
                            espacios[i][1] = 1;
                        }
                        if (listaReservas.get(j).getHorario().equals(horario3)) {
                            espacios[i][2] = 1;
                        }
                        if (listaReservas.get(j).getHorario().equals(horario4)) {
                            espacios[i][3] = 1;
                        }

                    }
                }
            }
            //se instancia un array de tipo entero de tamaño igual a la cantidad de espacios para hacer la cuenta
            espacio = new int[listaEspacios.size()];
            //bucle para hacer cuenta de reservaciones en cada espacio
            for(int j = 0; j < listaEspacios.size(); j++) {//se recorren las filas
                for (int i = 0; i < numeroReservasporDia; i++){//y se recorren las columnas
                    if (espacios[j][i]==1) {//Si el horario i (de 4 posibles horarios) está ocupado
          // se suma uno a la posición (i) de espacio para llevar la cuenta de la cantidad de reservaciones que tiene
                        espacio[j]++;
                    }
                    System.out.print(espacios[j][i]);
                }
                System.out.println();;
            }
            //Se inicializa la varibale para marcar espacios llenos
            numeroEspacioLleno = new int[listaEspacios.size()];
            //bucle para marcar a los espacios llenos (con todos sus horarios ocupados)
            for (int i = 0; i<listaEspacios.size(); i++){
                if(espacio[i]==numeroReservasporDia){
                    //si la posición (i) del espacio es la máxima de reservas, está lleno y se marca
                    numeroEspacioLleno[i]=1;
                }
            }
            for(int i=0; i<listaEspacios.size();i++){
                if(numeroEspacioLleno[i]==0){
                    listaNombresEspaciosOriginales.add(listaEspacios.get(i).getNombre_espacio());
                }
            }
        }
        if(listaNombresEspaciosOriginales.size()<=0 && listaReservas.size()>0){
            lblDisponibles.setText("Todas las reservas para esa fecha han sido apartadas. Favor de elegir otra fecha");
            imgFull.setVisible(true);
            dateReserva.setDisable(true);
            btnLimpiar.setVisible(true);
            System.out.println("listaNombresEspaciosOriginales.size()<=0");
        }else{
            System.out.println("Else");
            lblDisponibles.setText("Para la fecha elegida hay "+listaNombresEspaciosOriginales.size()+" espacios disponibles " +
                    "mostrados a continuación. Por favor, elija el de su preferencia");
                    mostrarBotones(listaNombresEspaciosOriginales.size());
                    nombrarBotonesEspacio(listaNombresEspaciosOriginales.size(),listaNombresEspaciosOriginales);
                    dateReserva.setDisable(true);
                    btnLimpiar.setVisible(true);
        }




    }
    private void mostrarBotones(int cantidad){
        //For de borrado localizado de botones
        for(int i=cantidadTotalBotones;cantidad<i; i--){
            switch(i){
                case 18:
                    tilePaneReservas.getChildren().remove(btnEsp18);

                    break;
                case 17:
                    tilePaneReservas.getChildren().remove(btnEsp17);
                    
                    break;
                case 16:
                    tilePaneReservas.getChildren().remove(btnEsp16);
                    
                    break;
                case 15:
                    tilePaneReservas.getChildren().remove(btnEsp15);
                    
                    break;
                case 14:
                    tilePaneReservas.getChildren().remove(btnEsp14);
                    
                    break;
                case 13:
                    tilePaneReservas.getChildren().remove(btnEsp13);
                    
                    break;
                case 12:
                    tilePaneReservas.getChildren().remove(btnEsp12);
                    
                    break;
                case 11:
                    tilePaneReservas.getChildren().remove(btnEsp11);
                    
                    break;
                case 10:
                    tilePaneReservas.getChildren().remove(btnEsp10);
                    
                    break;
                case 9:
                    tilePaneReservas.getChildren().remove(btnEsp09);
                    
                    break;
                case 8:
                    tilePaneReservas.getChildren().remove(btnEsp08);
                    
                    break;
                case 7:
                    tilePaneReservas.getChildren().remove(btnEsp07);
                    
                    break;
                case 6:
                    tilePaneReservas.getChildren().remove(btnEsp06);
                    
                    break;
                case 5:
                    tilePaneReservas.getChildren().remove(btnEsp05);
                    
                    break;
                case 4:
                    tilePaneReservas.getChildren().remove(btnEsp04);
                    
                    break;
                case 3:
                    tilePaneReservas.getChildren().remove(btnEsp03);
                    
                    break;
                case 2:
                    tilePaneReservas.getChildren().remove(btnEsp02);
                    
                    break;
                case 1:
                    tilePaneReservas.getChildren().remove(btnEsp01);
                    
                    break;
                default:
                    break;
            }
        }
    }

    private void nombrarBotones(int cantidad, List<Espacio> espaciosLista){
        for(int i=0; i<cantidad; i++){
            switch(i){
                case 0:
                    btnEsp01.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp01.setVisible(true);
                    break;
                case 1:
                    btnEsp02.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp02.setVisible(true);
                    break;
                case 2:
                    btnEsp03.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp03.setVisible(true);
                    break;
                case 3:
                    btnEsp04.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp04.setVisible(true);
                    break;
                case 4:
                    btnEsp05.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp05.setVisible(true);
                    break;
                case 5:
                    btnEsp06.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp06.setVisible(true);
                    break;
                case 6:
                    btnEsp07.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp07.setVisible(true);
                    break;
                case 7:
                    btnEsp08.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp08.setVisible(true);
                    break;
                case 8:
                    btnEsp09.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp09.setVisible(true);
                    break;
                case 9:
                    btnEsp10.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp10.setVisible(true);
                    break;
                case 10:
                    btnEsp11.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp11.setVisible(true);
                    break;
                case 11:
                    btnEsp12.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp12.setVisible(true);
                    break;
                case 12:
                    btnEsp13.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp13.setVisible(true);
                    break;
                case 13:
                    btnEsp14.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp14.setVisible(true);
                    break;
                case 14:
                    btnEsp15.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp15.setVisible(true);
                    break;
                case 15:
                    btnEsp16.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp16.setVisible(true);
                    break;
                case 16:
                    btnEsp17.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp17.setVisible(true);
                    break;
                case 17:
                    btnEsp18.setText(espaciosLista.get(i).getNombre_espacio());
                    
                    btnEsp18.setVisible(true);
                    break;
                default:
                    break;
            }

        }
    }

    private void nombrarBotonesEspacio(int cantidad, List<String> espaciosLista){
        int i=0;
        do{
            switch(i){
                case 0:

                    btnEsp01.setText(espaciosLista.get(i));
                    btnEsp01.disarm();
                    btnEsp01.setVisible(true);
                    break;
                case 1:
                    btnEsp02.setText(espaciosLista.get(i));
                    btnEsp02.disarm();
                    btnEsp02.setVisible(true);
                    break;
                case 2:
                    btnEsp03.setText(espaciosLista.get(i));
                    btnEsp03.disarm();
                    btnEsp03.setVisible(true);
                    break;
                case 3:
                    btnEsp04.setText(espaciosLista.get(i));
                    btnEsp04.disarm();
                    btnEsp04.setVisible(true);
                    break;
                case 4:
                    btnEsp05.setText(espaciosLista.get(i));
                    btnEsp05.disarm();
                    btnEsp05.setVisible(true);
                    break;
                case 5:
                    btnEsp06.setText(espaciosLista.get(i));
                    btnEsp06.disarm();
                    btnEsp06.setVisible(true);
                    break;
                case 6:
                    btnEsp07.setText(espaciosLista.get(i));
                    btnEsp07.disarm();
                    btnEsp07.setVisible(true);
                    break;
                case 7:
                    btnEsp08.setText(espaciosLista.get(i));
                    btnEsp08.disarm();
                    btnEsp08.setVisible(true);
                    break;
                case 8:
                    btnEsp09.setText(espaciosLista.get(i));
                    btnEsp09.disarm();
                    btnEsp09.setVisible(true);
                    break;
                case 9:
                    btnEsp10.setText(espaciosLista.get(i));
                    btnEsp10.disarm();
                    btnEsp10.setVisible(true);
                    break;
                case 10:
                    btnEsp11.setText(espaciosLista.get(i));
                    btnEsp11.disarm();
                    btnEsp11.setVisible(true);
                    break;
                case 11:
                    btnEsp12.setText(espaciosLista.get(i));
                    btnEsp12.disarm();
                    btnEsp12.setVisible(true);
                    break;
                case 12:
                    btnEsp13.setText(espaciosLista.get(i));
                    btnEsp13.disarm();
                    btnEsp13.setVisible(true);
                    break;
                case 13:
                    btnEsp14.setText(espaciosLista.get(i));
                    btnEsp14.disarm();
                    btnEsp14.setVisible(true);
                    break;
                case 14:
                    btnEsp15.setText(espaciosLista.get(i));
                    btnEsp15.disarm();
                    btnEsp15.setVisible(true);
                    break;
                case 15:
                    btnEsp16.setText(espaciosLista.get(i));
                    btnEsp16.disarm();
                    btnEsp16.setVisible(true);
                    break;
                case 16:
                    btnEsp17.setText(espaciosLista.get(i));
                    btnEsp17.disarm();
                    btnEsp17.setVisible(true);
                    break;
                case 17:
                    btnEsp18.setText(espaciosLista.get(i));
                    btnEsp18.disarm();
                    btnEsp18.setVisible(true);
                    break;
                default:
                    break;
            }
        i++;
        }while(i<cantidad);
    }

    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario.fxml","MENU PRINCIPAL", "admin");
    }

    @FXML
    private void botonLimpiar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario_reservar.fxml","Reservas", "usuario");
    }

    @FXML
    private void botonEspacioSeleccionado(ActionEvent evt) throws SQLException {
        List<Espacio> listaEspacios = new ArrayList<Espacio>();
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(39);
        botonSeleccionado = botonSeleccionado.replace("'","");
        listaEspacios = espacioDao.CONSULTAR();

        for(Espacio espacios: listaEspacios){
            if(espacios.getNombre_espacio().contains(botonSeleccionado)){
                espacioObj.setNombre_espacio(botonSeleccionado);
            }
        }
        btnHorario1.setText(horario1);
        btnHorario2.setText(horario2);
        btnHorario3.setText(horario3);
        btnHorario4.setText(horario4);

        if(listaReservas.isEmpty()){
                btnHorario1.setVisible(true);
                btnHorario1.setDisable(false);

                btnHorario2.setVisible(true);
                btnHorario2.setDisable(false);

                btnHorario3.setVisible(true);
                btnHorario3.setDisable(false);

                btnHorario4.setVisible(true);
                btnHorario4.setDisable(false);

        }else{

        for(Reserva reservas: listaReservas){
            System.out.println("Reserva "+reservas.getHorario());
            if(reservas.getNombre_espacio().equals(espacioObj.getNombre_espacio())){

                if(reservas.getHorario().equals(horario1)){
                    System.out.println("Reserva encontrada en "+horario1);
                    btnHorario1.setVisible(true);
                    btnHorario1.setDisable(true);
                }


                if(reservas.getHorario().equals(horario2)){
                    System.out.println("Reserva encontrada en "+horario2);
                    btnHorario2.setVisible(true);
                    btnHorario2.setDisable(true);
                }


                if(reservas.getHorario().equals(horario3)){
                    System.out.println("Reserva encontrada en "+horario3);
                    btnHorario3.setVisible(true);
                    btnHorario3.setDisable(true);
                }


                if(reservas.getHorario().equals(horario4)){
                    System.out.println("Reserva encontrada en "+horario4);
                    btnHorario4.setVisible(true);
                    btnHorario4.setDisable(true);
                }


            }else{
                btnHorario1.setVisible(true);
                btnHorario1.setDisable(false);

                btnHorario2.setVisible(true);
                btnHorario2.setDisable(false);

                btnHorario3.setVisible(true);
                btnHorario3.setDisable(false);

                btnHorario4.setVisible(true);
                btnHorario4.setDisable(false);
            }
        }
        if(!btnHorario1.isVisible()){
            btnHorario1.setVisible(true);
            btnHorario1.setDisable(false);
        }
        if(!btnHorario2.isVisible()){
                btnHorario2.setVisible(true);
                btnHorario2.setDisable(false);
        }
        if(!btnHorario3.isVisible()){
                btnHorario3.setVisible(true);
                btnHorario3.setDisable(false);
        }
        if(!btnHorario4.isVisible()){
                btnHorario4.setVisible(true);
                btnHorario4.setDisable(false);
        }

        }
        tilePaneReservas.setDisable(true);

        lblHorarios.setText("Los horarios disponibles para el espacio "+espacioObj.getNombre_espacio()+" son:");
    }

    private void visibilizarBotonesLabels(String horario){
        LocalDate fechaLocal;
        Locale spanishLocale=new Locale("es", "MX");
        fechaLocal = dateReserva.getValue();
        formato = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",spanishLocale);

        lblReser1.setVisible(true);
        lblReser2.setVisible(true);
        lblReser3.setVisible(true);
        lblNombreReser1.setVisible(true);
        lblFechaReser2.setVisible(true);
        lblHorarioReser3.setVisible(true);
        btnReservar.setVisible(true);
        lblNombreReser1.setText(usuarioToken.getNombre());
        lblFechaReser2.setText(fechaLocal.format(formato));
        reserva.setHorario(horario);
        lblHorarioReser3.setText(reserva.getHorario());
    }

    @FXML
    private void botonHorario1(ActionEvent evt){
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(42);
        botonSeleccionado = botonSeleccionado.replace("'","");
        lblHorarioReser3.setText(botonSeleccionado);

        visibilizarBotonesLabels(horario1);

    }
    @FXML
    private void botonHorario2(ActionEvent evt){
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(42);
        botonSeleccionado = botonSeleccionado.replace("'","");
        lblHorarioReser3.setText(botonSeleccionado);

        visibilizarBotonesLabels(horario2);
    }
    @FXML
    private void botonHorario3(ActionEvent evt){
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(42);
        botonSeleccionado = botonSeleccionado.replace("'","");
        lblHorarioReser3.setText(botonSeleccionado);

        visibilizarBotonesLabels(horario3);
    }

    @FXML
    private void botonHorario4(ActionEvent evt){
        String botonSeleccionado = evt.getSource().toString();
        botonSeleccionado = botonSeleccionado.substring(42);
        botonSeleccionado = botonSeleccionado.replace("'","");
        lblHorarioReser3.setText(botonSeleccionado);

        visibilizarBotonesLabels(horario4);
    }

    @FXML
    private void botonReservar(ActionEvent evt) throws SQLException, IOException {
        LocalDate fechaLocal;
        Locale spanishLocale=new Locale("es", "MX");
        formato = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",spanishLocale);
        fechaLocal = dateReserva.getValue();

        boolean result;
        Alertas alerta = new Alertas();
        reserva.setNombre_espacio(espacioObj.getNombre_espacio());
        reserva.setNombre_usuario(usuarioToken.getNombre());
        espacioObj.setLugares(40);
        reserva.setLugares_disponibles(espacioObj.getLugares());


        Optional<ButtonType> resultado = alerta.mostrarAlerta("confirmacion", "alta_reserva", "Confirmación de Reservación", reserva.getNombre_espacio(),
                "-Usuario que reserva: "+reserva.getNombre_usuario()+"\n-Espacio: "+reserva.getNombre_espacio()+
                        "\n-Horario: "+reserva.getHorario()+"\n-Fecha: "+fechaLocal.format(formato)+"\n-Lugares disponibles: "+reserva.getLugares_Disponibles()+"\n");
        if (resultado.isPresent() & resultado.get() == ButtonType.YES) {
            result = reservaDao.CREAR(reserva);
            System.out.println(reserva.getNombre_espacio());

            if(result){
                alerta.mostrarAlerta("aviso", "alta_reserva","Reservación satisfactoria", reserva.getNombre_espacio(),
                        "-Usuario que reserva: "+reserva.getNombre_usuario()+"\n-Espacio: "+reserva.getNombre_espacio()+
                                "\n-Horario: "+reserva.getHorario()+"\n-Fecha: "+reserva.getFecha()+"\n-Lugares disponibles: "+reserva.getLugares_Disponibles()+"\n");

                ventanas.mostrarVentana(evt, null, "usuario_reservar.fxml","Reservas", "usuario");
            }
            else{
                alerta.mostrarAlerta("error", "alta_fallida","Error", "Error en la reservación","No se ha podido realizar el registro, por favor intente nuevamente");
            }
        }


    }

}
