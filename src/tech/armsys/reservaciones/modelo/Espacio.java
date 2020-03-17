package tech.armsys.reservaciones.modelo;

import javafx.beans.property.*;

public class Espacio {

    //Se definen las variables del singleton
    private IntegerProperty id_espacio;
    private StringProperty nombre_espacio;
    private StringProperty tipo_espacio;
    private IntegerProperty lugares;

    private static Espacio esp;
    //función que asigna las variables provenientes de BD a las variables locales para el objeto de tipo paciente
    public Espacio(){

    }

    public Espacio(int id_espacio1, String nombre_espacio1,String tipo_espacio1, int lugares1) {

        this.id_espacio = new SimpleIntegerProperty(id_espacio1);
        this.nombre_espacio = new SimpleStringProperty(nombre_espacio1);
        this.tipo_espacio = new SimpleStringProperty(tipo_espacio1);
        this.lugares = new SimpleIntegerProperty(lugares1);
    }



    public static Espacio getSesionUsuario(int id_espacio1, String nombre_espacio1, String tipo_espacio1, int lugares1){

        if(esp == null){//Si no se ha instanciado aún el objeto
            //Se instancia un objeto (implementación de singleton)
            esp = new Espacio(id_espacio1, nombre_espacio1,tipo_espacio1, lugares1);
        }

        return esp;//se devuelve el objeto
    }
    //Implementación de get y set para cada valor (encapsulamiento)
    public int getId_Espacio() {
        return id_espacio.get();
    }

    public void setId_espacio(Integer id_espacio1) {
        this.id_espacio = new SimpleIntegerProperty(id_espacio1);
    }

    public String getNombre_espacio() {
        return nombre_espacio.get();
    }

    public void setNombre_espacio(String nombre_espacio1) {
        this.nombre_espacio = new SimpleStringProperty(nombre_espacio1);
    }

    public String getTipo_Espacio(){
        return tipo_espacio.get();
    }
    public void setTipo_Espacio(String tipo_espacio1){
        this.tipo_espacio = new SimpleStringProperty(tipo_espacio1);
    }

    public int getLugares(){
        return lugares.get();
    }
    public void setLugares(int lugares1){
        this.lugares = new SimpleIntegerProperty(lugares1);
    }
}
