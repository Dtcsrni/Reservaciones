/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.armsys.reservaciones.main;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static jdk.xml.internal.SecuritySupport.getResourceAsStream;

/**
 *
 * @author ErickVega
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try{
            FXMLLoader loader = new FXMLLoader();
            VBox root = loader.load(getClass().getResource("/tech/armsys/reservaciones/vista/login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("SIRELAC");
            primaryStage.setScene(scene);
            primaryStage.show();

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