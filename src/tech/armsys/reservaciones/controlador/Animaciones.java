package tech.armsys.reservaciones.controlador;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animaciones {
    private FadeTransition desvanecer;
    public void animarDesvanecer(Node nodo) {
        desvanecer = new FadeTransition(Duration.seconds(1), nodo);
        animar();
    }
    public void animarDesvanecer(Node nodo, float duracion) {
        desvanecer = new FadeTransition(Duration.seconds(duracion), nodo);
        animar();
    }
    private void animar(){
        desvanecer.setFromValue(0.0);
        desvanecer.setToValue(1.0);

        desvanecer.setCycleCount(1);
        desvanecer.play();
    }



}
