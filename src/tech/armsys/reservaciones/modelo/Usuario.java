package tech.armsys.reservaciones.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Usuario {
    //Se definen las variables del singleton
    private StringProperty id_usuario;
    private StringProperty nombre;
    private IntegerProperty tipoUsuario;
    private StringProperty contra;

    private static Usuario usr;
    //función que asigna las variables provenientes de BD a las variables locales para el objeto de tipo paciente
    public Usuario(){

    }

    public Usuario(String id_usuario1, String nombre1, int tipoUsuario1, String contra1) {

        this.id_usuario = new SimpleStringProperty(id_usuario1);
        this.nombre = new SimpleStringProperty(nombre1);
        this.tipoUsuario = new SimpleIntegerProperty(tipoUsuario1);
        this.contra = new SimpleStringProperty(contra1);
    }



    public static Usuario getInstanceUser(String id_usuario1, String nombre1, int tipoUsuario1, String contra1){

        if(usr == null){//Si no se ha instanciado aún el objeto
            //Se instancia un objeto (implementación de singleton)
            usr = new Usuario(id_usuario1, nombre1, tipoUsuario1, contra1);
        }

        return usr;//se devuelve el objeto
    }
    //Implementación de get y set para cada valor (encapsulamiento)
    public String getId_Usuario() {
        return id_usuario.get();
    }

    public void setId_usuario(String id_usuario1) {
        this.id_usuario = new SimpleStringProperty(id_usuario1);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre1) {
        this.nombre = new SimpleStringProperty(nombre1);
    }

    public int getTipoUsuario(){
        return tipoUsuario.get();
    }
    public void setTipoUsuario(int tipoUsuario1){
        this.tipoUsuario = new SimpleIntegerProperty(tipoUsuario1);
    }

    public String getContra(){
        return contra.get();
    }
    public void setContra(String contra1){
        this.contra = new SimpleStringProperty(contra1);
    }
}
