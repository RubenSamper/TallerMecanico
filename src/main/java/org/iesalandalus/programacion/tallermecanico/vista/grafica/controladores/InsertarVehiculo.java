package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class InsertarVehiculo extends Controlador {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCerrar;

    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfMarca;


    @FXML
    private TextField tfModelo;

    private Vehiculos vehiculos;

    public void inicializarVehiculos(Vehiculos vehiculos) {
        this.vehiculos = vehiculos;
    }


    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void insertarVehiculo(ActionEvent event) {
        if (vehiculos == null) {
            mostrarError("Error de inicialización", "El objeto Clientes no se ha inicializado correctamente.");
            return;
        }

        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String matricula = tfMatricula.getText();

        try {
            Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, matricula);

            vehiculos.insertar(nuevoVehiculo);
            Vehiculos.getInstancia().terminar();


            mostrarInformacion("Vehiculo insertado", "Vehiculo insertado correctamente.");
        } catch (IllegalArgumentException e) {
            mostrarError("Error al insertar vehiculo", e.getMessage());
        } catch (OperationNotSupportedException e) {
            mostrarError("Error al insertar vehiculo", e.getMessage());
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.ERROR);
        dialogo.setTitle("ERROR: " + titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);

        dialogo.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);

        dialogo.showAndWait();
    }
}