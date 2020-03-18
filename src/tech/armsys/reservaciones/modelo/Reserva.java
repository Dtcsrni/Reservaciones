package tech.armsys.reservaciones.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Reserva {
    //Se definen las variables del singleton
    private IntegerProperty id_reserva;
    private StringProperty  nombre_espacio;
    private StringProperty  horario;
    private StringProperty   fecha;
    private StringProperty   nombre_usuario;
    private IntegerProperty  lugares_disponibles;

    private static Reserva rsv;
    //función que asigna las variables provenientes de BD a las variables locales para el objeto de tipo paciente
    public Reserva(){

    }

    public Reserva(int id_reserva1, String nombre_espacio1, String horario, String nombre_usuario1, String fecha1, int lugares_disponibles1) {

        this.id_reserva = new SimpleIntegerProperty(id_reserva1);
        this.nombre_espacio = new SimpleStringProperty(nombre_espacio1);
        this.horario = new SimpleStringProperty(horario);
        this.nombre_usuario = new SimpleStringProperty(nombre_usuario1);
        this.fecha = new SimpleStringProperty(fecha1);
        this.lugares_disponibles = new SimpleIntegerProperty(lugares_disponibles1);
    }

    public static Reserva apartar(int id_reserva1, String nombre_espacio1,String horario, String nombre_usuario1, String fecha1, int lugares_disponibles1){

        if(rsv == null){//Si no se ha instanciado aún el objeto
            //Se instancia un objeto (implementación de singleton)
            rsv = new Reserva(id_reserva1, nombre_espacio1,horario, nombre_usuario1, fecha1, lugares_disponibles1);
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

    public String getNombre_espacio() {
        return nombre_espacio.get();
    }

    public void setNombre_espacio(String nombre_espacio1) {
        this.nombre_espacio = new SimpleStringProperty(nombre_espacio1);
    }
    public String getHorario() {
        return horario.get();
    }

    public void setHorario(String horario1) {
        this.horario = new SimpleStringProperty(horario1);
    }

    public String getNombre_usuario(){
        return nombre_usuario.get();
    }
    public void setNombre_usuario(String nombre_usuario1){
        this.nombre_usuario = new SimpleStringProperty(nombre_usuario1);
    }

    public String getFecha(){
        return fecha.get();
    }
    public void setFecha(String fecha1){
        this.fecha = new SimpleStringProperty(fecha1);
    }
    public int getLugares_Disponibles(){
        return lugares_disponibles.get();
    }
    public void setLugares_disponibles(int lugares_disponibles1){
        this.lugares_disponibles = new SimpleIntegerProperty(lugares_disponibles1);
    }
}

