/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.armsys.reservaciones.main;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import tech.armsys.reservaciones.controlador.ventanas;

import static jdk.xml.internal.SecuritySupport.getResourceAsStream;

/**
 *
 * @author ErickVega
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try{
            ventanas.mostrarVentana(null,primaryStage,"login.fxml", "login", "");

        }
        catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}