package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class ModificarCliente extends Controlador {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCerrar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    private Cliente clienteAModificar;

    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void modificarCliente(ActionEvent event) {
        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String telefono = tfTelefono.getText();

        try {
            Clientes.getInstancia().modificar(clienteAModificar, nombre, telefono);
            mostrarInformacion("Cliente modificado", "El cliente se ha modificado correctamente.");
            getEscenario().close();
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            mostrarError("Error al modificar cliente", e.getMessage());
        }
    }

    public void inicializarCliente(Cliente cliente) {
        clienteAModificar = cliente;
        tfNombre.setText(cliente.getNombre());
        tfDni.setText(cliente.getDni());
        tfTelefono.setText(cliente.getTelefono());
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