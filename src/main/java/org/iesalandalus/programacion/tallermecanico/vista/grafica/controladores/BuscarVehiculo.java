package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import java.util.List;

public class BuscarVehiculo extends Controlador {

    @FXML
    private Button btActualizar;

    @FXML
    private Button btBorrar;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCerrar;

    @FXML
    private Button btModificar;

    @FXML
    private TableColumn<Vehiculo, String> cModelo;

    @FXML
    private TableColumn<Vehiculo, String> cMarca;

    @FXML
    private TableColumn<Vehiculo, String> cMatricula;

    @FXML
    private TableView<Vehiculo> tVehiculoBuscado;

    @FXML
    private TextField tfMatricula;

    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();

    private void initialize() {
        inicializarTabla();
        cargarVehiculos();
        btActualizar.setOnAction(event -> actualizarTabla());

    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = Vehiculos.getInstancia().get();
        listaVehiculos = FXCollections.observableArrayList(vehiculos);
        tVehiculoBuscado.setItems(listaVehiculos);
    }

    public void actualizarTabla() {
        listaVehiculos.clear();
        listaVehiculos.addAll(Vehiculos.getInstancia().get());
    }

    @FXML
    void buscarCliente(ActionEvent event) {
        String matriculaBuscada = tfMatricula.getText();
        if (!matriculaBuscada.isEmpty()) {
            Vehiculo vehiculoEncontrado = Vehiculos.getInstancia().buscar(new Vehiculo("Nissan", matriculaBuscada, "6860DSY"));
            if (vehiculoEncontrado != null) {
                listaVehiculos.clear();
                listaVehiculos.add(vehiculoEncontrado);
            } else {
                mostrarInformacion("Cliente no encontrado", "No se ha encontrado ningún cliente con el DNI especificado.");
            }
        } else {
            mostrarInformacion("Campo vacío", "Por favor, introduce un DNI para buscar un cliente.");
        }
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);

        dialogo.showAndWait();
    }

    private void inicializarTabla() {
        cMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        cModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        cMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }

    @FXML
    void borrarVehiculo(ActionEvent event) {
        Vehiculo vehiculoSeleccionado = tVehiculoBuscado.getSelectionModel().getSelectedItem();
        if (vehiculoSeleccionado != null) {
            try {
                Vehiculos.getInstancia().borrar(vehiculoSeleccionado);
                Vehiculos.getInstancia().terminar();

                listaVehiculos.remove(vehiculoSeleccionado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void buscarVehiculo(ActionEvent event) {

    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void actualizarTabla(ActionEvent event) {
        cargarClientes();
    }

    private void cargarClientes() {
        List<Vehiculo> vehiculos = Vehiculos.getInstancia().get();
        listaVehiculos = FXCollections.observableArrayList(vehiculos);
        tVehiculoBuscado.setItems(listaVehiculos);
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
