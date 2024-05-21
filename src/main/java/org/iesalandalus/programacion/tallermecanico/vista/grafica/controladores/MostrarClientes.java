package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import java.util.List;

public class MostrarClientes extends Controlador {
    @FXML
    private Button btActualizar;

    @FXML
    private Button btBorrar;

    @FXML
    private Button btModificar;

    @FXML
    private Button btBuscarCliente;
    @FXML
    private TableView<Cliente> tClientes;

    @FXML
    private TableColumn<Cliente, String> cNombre;

    @FXML
    private TableColumn<Cliente, String> cDni;

    @FXML
    private TableColumn<Cliente, String> cTelefono;

    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        inicializarTabla();
        cargarClientes();
        btActualizar.setOnAction(event -> actualizarTabla());

    }

    private void inicializarTabla() {
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    private void cargarClientes() {
        List<Cliente> clientes = Clientes.getInstancia().get();
        listaClientes = FXCollections.observableArrayList(clientes);
        tClientes.setItems(listaClientes);
    }

    public void actualizarTabla() {
        listaClientes.clear();
        listaClientes.addAll(Clientes.getInstancia().get());
    }

    @FXML
    void borrarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = tClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            try {
                Clientes.getInstancia().borrar(clienteSeleccionado);
                Clientes.getInstancia().terminar();

                listaClientes.remove(clienteSeleccionado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void modificar(ActionEvent event) {
        Cliente clienteSeleccionado = tClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            Controlador controladorModificarCliente = Controladores.get("/vistas/ModificarCliente.fxml", "Modificar Cliente", getEscenario());
            ((ModificarCliente) controladorModificarCliente).inicializarCliente(clienteSeleccionado);
            controladorModificarCliente.getEscenario().show();
        } else {
            mostrarError("Error", "Debes seleccionar un cliente para modificar.");
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void actualizarTabla(ActionEvent event) {
        cargarClientes();
    }

    @FXML
    void buscarCliente(ActionEvent event) {
        Controlador graficaPrincipal = Controladores.get("/vistas/BuscarCliente.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }
}