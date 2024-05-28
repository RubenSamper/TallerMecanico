package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.Vehiculos;
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
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String matricula = tfMatricula.getText();

        try {
            Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, matricula);

            Vehiculos.getInstancia().insertar(nuevoVehiculo);

            mostrarInformacion("Vehículo insertado", "El vehículo se ha insertado correctamente.");

            getEscenario().close();
        } catch (IllegalArgumentException e) {
            mostrarError("Error al insertar vehículo", e.getMessage());
        } catch (OperationNotSupportedException e) {
            mostrarError("Error al insertar vehículo", e.getMessage());
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}