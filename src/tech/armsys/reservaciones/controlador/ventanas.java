package tech.armsys.reservaciones.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class ventanas {
    static private String rutaVistas = "/tech/armsys/reservaciones/vista/";

    public String getRutaVistas() {
        return rutaVistas;
    }

    public void setRutaVista(String rutaVista) {
        rutaVistas = rutaVista;
    }

    public static void mostrarVentana(ActionEvent evt, Stage stage, String fxml, String titulo, String tipo) throws IOException {

        fxml= rutaVistas+fxml;//se
        Parent root = FXMLLoader.load(ventanas.class.getResource(fxml)); //se crea la variable root referente a la ventana padre y se asigna a el tipo fxml (ventana) que obtenga el cargador de FXML
        Scene scene = new Scene(root);  //se crea la escena nueva con el contenido de la ventana padre
        Stage window = null;    //se crea el stage y se inicializa a null
        String tituloVentana="";    //se crea el titulo de ventana y se inicializa vacía

        //si hay un objeto de tipo evento se asigna, si no, entonces hay uno de tipo stage, y se asigna al objeto Stage local
        if(evt!= null){
            window = (Stage)((Node)evt.getSource()).getScene().getWindow();
        }
        else if(stage !=null){
            window = stage;
        }

        //si el titulo contiene la palabra "login" entonces se formatea completo para el login
        if(titulo.equals("login")){
            if (window != null) {
                window.setTitle("SIRELAC | BIENVENIDO > ACCESO");
            }
        }
        else{//si no lo contiene, se formatea de acuerdo al título y tipo proporcionado
            //se formatea el título dependiendo el tipo de usuario
            if(tipo.equals("admin")){
                tituloVentana = "SIRELAC | ADMINISTRADOR";
            }
            if(tipo.equals("usr")){
                tituloVentana = "SIRELAC | USUARIO";
            }
            if (window != null) {
                window.setTitle(tituloVentana+" > "+titulo);
            }
        }
        //se asigna la escena a la ventana y después se muestra
        window.setScene(scene);
        window.show();
    }

}
