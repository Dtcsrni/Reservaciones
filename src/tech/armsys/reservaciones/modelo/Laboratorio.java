package tech.armsys.reservaciones.modelo;

import javafx.beans.property.*;

public class Laboratorio {

    //Se definen las variables del singleton
    private DoubleProperty id_lab;
    private StringProperty nombre_lab;
    private IntegerProperty no_maquinas;
    private IntegerProperty operativas;
    private BooleanProperty disponible;

    private static Laboratorio lab;
    //función que asigna las variables provenientes de BD a las variables locales para el objeto de tipo paciente
    public Laboratorio(){

    }

    public Laboratorio(double id_lab1, String nombre_lab1, int no_maquinas1, int operativas1, boolean disponible1) {

        this.id_lab = new SimpleDoubleProperty(id_lab1);
        this.nombre_lab = new SimpleStringProperty(nombre_lab1);
        this.no_maquinas = new SimpleIntegerProperty(no_maquinas1);
        this.operativas = new SimpleIntegerProperty(operativas1);
        this.disponible = new SimpleBooleanProperty(disponible1);
    }



    public static Laboratorio getInstanceUser(double id_lab1, String nombre_lab1, int no_maquinas1, int operativas1, boolean disponible1){

        if(lab == null){//Si no se ha instanciado aún el objeto
            //Se instancia un objeto (implementación de singleton)
            lab = new Laboratorio(id_lab1, nombre_lab1, no_maquinas1, operativas1, disponible1);
        }

        return lab;//se devuelve el objeto
    }
    //Implementación de get y set para cada valor (encapsulamiento)
    public double getId_Lab() {
        return id_lab.get();
    }

    public void setId_lab(double id_lab1) {
        this.id_lab = new SimpleDoubleProperty(id_lab1);
    }

    public String getNombre_lab() {
        return nombre_lab.get();
    }

    public void setNombre_lab(String nombre_lab1) {
        this.nombre_lab = new SimpleStringProperty(nombre_lab1);
    }

    public int getNo_maquinas(){
        return no_maquinas.get();
    }
    public void setNo_maquinas(int no_maquinas1){
        this.no_maquinas = new SimpleIntegerProperty(no_maquinas1);
    }

    public int getOperativas(){
        return operativas.get();
    }
    public void setOperativas(int operativas1){
        this.operativas = new SimpleIntegerProperty(operativas1);
    }
    public boolean isDisponible() {
        return disponible.get();
    }

    public void setDisponible(boolean disponible1) {
        this.disponible = new SimpleBooleanProperty(disponible1);
    }
}
