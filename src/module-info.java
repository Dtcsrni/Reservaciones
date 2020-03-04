module Reservaciones{
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;


    opens tech.armsys.reservaciones.controlador;
    opens tech.armsys.reservaciones.main;
    opens tech.armsys.reservaciones.modelo;
    opens tech.armsys.reservaciones.recursos;
    opens tech.armsys.reservaciones.vista;


}