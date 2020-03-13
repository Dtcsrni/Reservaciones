package tech.armsys.reservaciones.controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Alertas {

    public Optional mostrarAlerta(String tipoAlerta, String subtipo, String titulo, String encabezado, String contenido) {
        Optional result = Optional.empty();

        if (tipoAlerta.equals("error")){
                if(subtipo.equals("credenciales")){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error de validación");
                    error.setHeaderText("Credenciales incorrectas");
                    error.setContentText("Por favor verifique sus credenciales e intente nuevamente");
                    result = error.showAndWait();
                    }
                if(subtipo.equals("BD")){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error en la base de datos");
                    error.setHeaderText("No se ha podido conectar con la base de datos correctamente");
                    error.setContentText("Verifique que la base de datos esté inicializada y funcionando correctamente. \n"+contenido);
                    result = error.showAndWait();
                }
                if(subtipo.equals("alta_existente")){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error en la alta");
                    error.setHeaderText("No se ha podido dar de alta el registro solicitado");
                    error.setContentText("El registro solicitado ya existe. Por favor, verifique los datos ingresados");
                    result = error.showAndWait();
                }
                if(subtipo.equals("modificacion_fallida")){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error en la modificación");
                error.setHeaderText("No se ha podido modificar el registro solicitado");
                error.setContentText("Por favor, intente nuevamente");
                result = error.showAndWait();
                }
                if(subtipo.equals("eliminacionfallida")){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error en la eliminación");
                error.setHeaderText("No se ha podido eliminar el registro solicitado");
                error.setContentText("No ha indicado el nombre correcto. La operación de eliminación se ha cancelado");
                result = error.showAndWait();
                }
            if(subtipo.equals("busquedafallida")){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error en la busqueda");
                error.setHeaderText("No se ha podido encontrar el registro solicitado");
                error.setContentText("El registro solicitado no existe. Por favor, verifique los datos ingresados");
                result = error.showAndWait();
            }
            }
        if(tipoAlerta.equals("confirmacion")){
            if(subtipo.equals("logout")){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cerrar sesión");
                confirmacion.setHeaderText("Confirme cierre de sesión");
                confirmacion.setContentText("¿Está seguro que quiere cerrar su sesión y regresar a la zona de autenticación? ");

                confirmacion.getButtonTypes().clear();
                confirmacion.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                //Deactivate Defaultbehavior for yes-Button:
                Button yesButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );

                //Activate Defaultbehavior for no-Button:
                Button noButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );

                result = confirmacion.showAndWait();
            }
            if(subtipo.equals("alta")){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmación de Alta");
                confirmacion.setHeaderText("Confirme alta de "+encabezado);
                confirmacion.setContentText("¿Está seguro que quiere realizar el alta del "+encabezado+"?" +
                        "\nPor favor verifique que los datos sean correctos:\n\n" +contenido);

                confirmacion.getButtonTypes().clear();
                confirmacion.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                //Deactivate Defaultbehavior for yes-Button:
                Button yesButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );

                //Activate Defaultbehavior for no-Button:
                Button noButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );

                result = confirmacion.showAndWait();
            }
            if(subtipo.equals("modificacion")){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmación de Modificación");
                confirmacion.setHeaderText("Confirme la modificación de "+encabezado);
                confirmacion.setContentText("¿Está seguro que quiere realizar la siguiente modificación del "+encabezado+"?" +
                        "\nPor favor verifique que los datos sean correctos:\n\n" +contenido);

                confirmacion.getButtonTypes().clear();
                confirmacion.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                //Deactivate Defaultbehavior for yes-Button:
                Button yesButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );

                //Activate Defaultbehavior for no-Button:
                Button noButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );

                result = confirmacion.showAndWait();
            }
            if(subtipo.equals("eliminacion")){
                TextInputDialog confirmacion = new TextInputDialog("Nombre de entidad");
                confirmacion.setTitle("Confirmación de Eliminación");
                confirmacion.setHeaderText("Confirme la eliminación de "+encabezado+" escribiendo el nombre de la entidad en el recuadro");
                confirmacion.setContentText("¿Está seguro que quiere eliminar "+encabezado+"?" +
                        "\nPor favor verifique que los datos sean correctos, una vez eliminado de la base de datos " +
                        "LA OPERACIÓN ES IRREVERSIBLE:\n\n" +contenido);

                result = confirmacion.showAndWait();
            }
        }
        if(tipoAlerta.equals("aviso")) {
            if(subtipo.equals("alta")) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle(titulo);
                aviso.setHeaderText("**Alta de "+encabezado+" completada satisfactoriamente**");
                aviso.setContentText(encabezado+" ha sido agregado exitosamente a la base de datos.\n " +
                        "Los datos son los siguientes:\n"+contenido);
                result = aviso.showAndWait();
            }
            if(subtipo.equals("modificacion")) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle(titulo);
                aviso.setHeaderText("**Modificación de "+encabezado+" completada satisfactoriamente**");
                aviso.setContentText("El "+encabezado+" ha sido modificado exitosamente en la base de datos.\n " +
                        "Los datos son los siguientes:\n"+contenido);
                result = aviso.showAndWait();
            }
            if(subtipo.equals("eliminacion")) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle(titulo);
                aviso.setHeaderText("**Eliminación de "+encabezado+" completada satisfactoriamente**");
                aviso.setContentText(encabezado+" ha sido eliminado exitosamente de la base de datos.\n " +
                        "Los datos son los siguientes:\n"+contenido);
                result = aviso.showAndWait();
            }
        }
        if(result.isEmpty()){
            System.out.println("Result Vacío");
        }

        return result;
    }
}
