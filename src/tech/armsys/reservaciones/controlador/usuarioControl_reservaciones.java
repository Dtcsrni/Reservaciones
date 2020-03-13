package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class usuarioControl_reservaciones {
    @FXML
    private TilePane tilePaneReservas;
    @FXML
    private Button btnLab1;
    @FXML
    private Button btnLab2;
    @FXML
    private Button btnLab3;
    @FXML
    private Button btnLab4;
    @FXML
    private Button btnLab5;
    @FXML
    private Button btnLab6;
    @FXML
    private Button btnLab7;
    @FXML
    private Button btnLab8;
    @FXML
    private Button btnLab9;
    @FXML
    private Button btnLab10;
    @FXML
    private Button btnLab11;
    @FXML
    private Button btnLab12;
    @FXML
    private Button btnLab13;
    @FXML
    private Button btnLab14;
    @FXML
    private Button btnLab15;
    @FXML
    private Button btnLab16;

    @FXML
    void mostrarReservaciones(ActionEvent evt) throws IOException {



        tilePaneReservas.getChildren().remove(btnLab1);
        tilePaneReservas.getChildren().remove(btnLab2);
        tilePaneReservas.getChildren().remove(btnLab3);
        tilePaneReservas.getChildren().remove(btnLab4);
        tilePaneReservas.getChildren().remove(btnLab5);
        tilePaneReservas.getChildren().remove(btnLab6);
        tilePaneReservas.getChildren().remove(btnLab7);
        tilePaneReservas.getChildren().remove(btnLab8);
        tilePaneReservas.getChildren().remove(btnLab9);
        tilePaneReservas.getChildren().remove(btnLab10);
        tilePaneReservas.getChildren().remove(btnLab11);
        tilePaneReservas.getChildren().remove(btnLab12);
        tilePaneReservas.getChildren().remove(btnLab13);
        tilePaneReservas.getChildren().remove(btnLab14);


    }

}
