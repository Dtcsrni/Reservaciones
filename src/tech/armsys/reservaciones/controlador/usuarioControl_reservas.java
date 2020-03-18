package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import tech.armsys.reservaciones.controlador.utilitarias.ventanas;
import tech.armsys.reservaciones.modelo.Reserva;
import tech.armsys.reservaciones.modelo.dao.reservaDAO;
import tech.armsys.reservaciones.modelo.dao.reservaDAOImpl;
import static tech.armsys.reservaciones.controlador.loginControl.usuarioToken;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class usuarioControl_reservas implements Initializable {
    @FXML
    private TilePane tilePaneReservas = new TilePane();
    private Reserva reserva = new Reserva();
    private reservaDAO reservaDao = new reservaDAOImpl();
    private List<Reserva> listaReservas = new ArrayList<Reserva>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarReservaciones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonRegresar(ActionEvent evt) throws IOException {
        ventanas.mostrarVentana(evt, null, "usuario.fxml","MENU PRINCIPAL", "admin");
    }
    private void cargarReservaciones() throws SQLException {
        reserva.setNombre_usuario(usuarioToken.getNombre());
        listaReservas = reservaDao.CONSULTAR(reserva);

        for(int i=0; i<listaReservas.size(); i++){
            tilePaneReservas.getChildren().add(new Button(listaReservas.get(i).getFecha()));
        }

    }
    private void clickBoton(){
        
    }

}
