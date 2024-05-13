package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class InsertarVehiculo extends Controlador {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCerrar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    @FXML
    void cerrarVentana(ActionEvent event) {
        Controlador ventanaPrincipal = Controladores.get("/vistas/InsertarVehiculo.fxml", "Hola mundo desde JavaFX", null);
        ventanaPrincipal.getEscenario().close();
    }

    @FXML
    void mostrarMensaje(ActionEvent event) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("ERROR: Campos incompletos");
        dialogo.setHeaderText(null);
        dialogo.setContentText("No se ha podido insertar el Vehiculo");

        dialogo.showAndWait();
    }

}