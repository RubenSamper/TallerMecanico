package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import java.util.List;

public class BuscarCliente extends Controlador {

    @FXML
    private TableView<Cliente> tClienteBuscado;
    @FXML
    private TableColumn<Cliente, String> cDni;
    @FXML
    private TableColumn<Cliente, String> cNombre;
    @FXML
    private TableColumn<Cliente, String> cTelefono;
    @FXML
    private Button btBorrar;
    @FXML
    private Button btBuscar;
    @FXML
    private Button btCerrar;
    @FXML
    private Button btModificar;
    @FXML
    private Button btActualizar;
    @FXML
    private TextField tfDni;
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        inicializarTabla();
        cargarClientes();
    }

    private void inicializarTabla() {
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    private void cargarClientes() {
        List<Cliente> clientes = Clientes.getInstancia().get();
        listaClientes.setAll(clientes);
        tClienteBuscado.setItems(listaClientes);
    }

    @FXML
    void buscarCliente(ActionEvent event) {
        String dniBuscado = tfDni.getText();
        if (!dniBuscado.isEmpty()) {
            Cliente clienteEncontrado = Clientes.getInstancia().buscar(new Cliente("Ruben", dniBuscado, "684232981"));
            if (clienteEncontrado != null) {
                listaClientes.setAll(clienteEncontrado);
            } else {
                mostrarInformacion("Cliente no encontrado", "No se ha encontrado ningún cliente con el DNI especificado.");
            }
        } else {
            mostrarInformacion("Campo vacío", "Por favor, introduce un DNI para buscar un cliente.");
        }
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void modificarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = tClienteBuscado.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            Controlador controladorModificarCliente = Controladores.get("/vistas/ModificarCliente.fxml", "Modificar Cliente", getEscenario());
            ((ModificarCliente) controladorModificarCliente).inicializarCliente(clienteSeleccionado);
            controladorModificarCliente.getEscenario().show();
        } else {
            mostrarError("Error", "Debes seleccionar un cliente para modificar.");
        }
    }

    @FXML
    void borrarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = tClienteBuscado.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            try {
                Clientes.getInstancia().borrar(clienteSeleccionado);
                cargarClientes();
            } catch (Exception e) {
                mostrarError("Error al borrar cliente", "Se ha producido un error al intentar borrar el cliente.");
            }
        } else {
            mostrarError("Error", "Debes seleccionar un cliente para borrar.");
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
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);

        dialogo.showAndWait();
    }

    @FXML
    void actualizarTabla(ActionEvent event) {
        cargarClientes();
    }
}