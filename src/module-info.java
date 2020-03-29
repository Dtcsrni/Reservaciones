module Reservaciones{
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires poi;
    requires poi.ooxml;
    requires java.desktop;


    opens tech.armsys.reservaciones.controlador;
    opens tech.armsys.reservaciones.modelo.main;
    opens tech.armsys.reservaciones.modelo;
    opens tech.armsys.reservaciones.vista;
    opens tech.armsys.reservaciones.vista.recursos;



}