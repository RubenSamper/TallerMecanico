package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class InsertarClientes extends Controlador {

    private Clientes clientes;

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

    public void inicializarClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void insertarCliente(ActionEvent event) {
        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String telefono = tfTelefono.getText();

        if (validarDatos(nombre, dni, telefono)) {
            try {
                Cliente nuevoCliente = new Cliente(nombre, dni, telefono);
                clientes.insertar(nuevoCliente);
                mostrarInformacion("Cliente insertado", "Cliente insertado correctamente.");
            } catch (IllegalArgumentException e) {
                mostrarError("Error al insertar cliente", e.getMessage());
            } catch (OperationNotSupportedException e) {
                mostrarError("Error al insertar cliente", e.getMessage());
            }
        }
    }

    private boolean validarDatos(String nombre, String dni, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError("Datos inválidos", "El nombre no puede estar vacío.");
            return false;
        }
        if (dni == null || dni.trim().isEmpty()) {
            mostrarError("Datos inválidos", "El DNI no puede estar vacío.");
            return false;
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            mostrarError("Datos inválidos", "El teléfono no puede estar vacío.");
            return false;
        }
        return true;
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
