package tech.armsys.reservaciones.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Reserva {
    //Se definen las variables del singleton
    private IntegerProperty id_reserva;
    private StringProperty laboratorio;
    private StringProperty usuario;
    private StringProperty fecha;
    private IntegerProperty espacios_disponibles;

    private static Reserva rsv;
    //función que asigna las variables provenientes de BD a las variables locales para el objeto de tipo paciente
    public Reserva(){

    }

    public Reserva(int id_reserva1, String laboratorio1, String usuario1, String fecha1, int espacios_disponibles1) {

        this.id_reserva = new SimpleIntegerProperty(id_reserva1);
        this.laboratorio = new SimpleStringProperty(laboratorio1);
        this.usuario = new SimpleStringProperty(usuario1);
        this.fecha = new SimpleStringProperty(fecha1);
        this.espacios_disponibles = new SimpleIntegerProperty(espacios_disponibles1);
    }



    public static Reserva getInstanceUser(int id_reserva1, String laboratorio1, String usuario1, String fecha1, int espacios_disponibles1){

        if(rsv == null){//Si no se ha instanciado aún el objeto
            //Se instancia un objeto (implementación de singleton)
            rsv = new Reserva(id_reserva1, laboratorio1, usuario1, fecha1, espacios_disponibles1);
        }

        return rsv;//se devuelve el objeto
    }
    //Implementación de get y set para cada valor (encapsulamiento)
    public int getId_Reserva() {
        return id_reserva.get();
    }

    public void setId_reserva(int id_reserva1) {
        this.id_reserva = new SimpleIntegerProperty(id_reserva1);
    }

    public String getLaboratorio() {
        return laboratorio.get();
    }

    public void setLaboratorio(String laboratorio1) {
        this.laboratorio = new SimpleStringProperty(laboratorio1);
    }

    public String getUsuario(){
        return usuario.get();
    }
    public void setUsuario(String usuario1){
        this.usuario = new SimpleStringProperty(usuario1);
    }

    public String getFecha(){
        return fecha.get();
    }
    public void setFecha(String fecha1){
        this.fecha = new SimpleStringProperty(fecha1);
    }
    public int getEspacios_Disponibles(){
        return espacios_disponibles.get();
    }
    public void setEspacios_Disponibles(int espacios_disponibles1){
        this.espacios_disponibles = new SimpleIntegerProperty(espacios_disponibles1);
    }
}

