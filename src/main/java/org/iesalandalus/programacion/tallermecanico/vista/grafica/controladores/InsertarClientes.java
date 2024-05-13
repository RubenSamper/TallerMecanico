package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class InsertarClientes extends Controlador {
    private Clientes clientes;

    public void inicializarClientes(Clientes clientes) {
        this.clientes = clientes;
    }

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

    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void insertarCliente(ActionEvent event) {
        if (clientes == null) {
            mostrarError("Error de inicialización", "El objeto Clientes no se ha inicializado correctamente.");
            return;
        }

        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String telefono = tfTelefono.getText();

        try {
            Cliente nuevoCliente = new Cliente(nombre, dni, telefono);

            clientes.insertar(nuevoCliente);
            Clientes.getInstancia().terminar();


            mostrarInformacion("Cliente insertado", "Cliente insertado correctamente.");
        } catch (IllegalArgumentException e) {
            mostrarError("Error al insertar cliente", e.getMessage());
        } catch (OperationNotSupportedException e) {
            mostrarError("Error al insertar cliente", e.getMessage());
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
