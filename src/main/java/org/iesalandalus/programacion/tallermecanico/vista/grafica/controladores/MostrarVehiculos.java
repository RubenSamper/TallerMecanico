package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import java.util.List;

public class MostrarVehiculos extends Controlador {
    @FXML
    private Button btActualizarVehiculo;

    @FXML
    private Button btBorrarVehiculo;

    @FXML
    private Button btBuscarVehiculo;


    @FXML
    private TableColumn<Vehiculo, String> cMarca;

    @FXML
    private TableColumn<Vehiculo, String> cMatricula;

    @FXML
    private TableColumn<Vehiculo, String> cModelo;

    @FXML
    private TableView<Vehiculo> tVehiculos;

    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        inicializarTabla();
        cargarVehiculos();
        btActualizarVehiculo.setOnAction(event -> actualizarTabla());

    }


    private void inicializarTabla() {
        cMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        cModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        cMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = Vehiculos.getInstancia().get();
        if (vehiculos.isEmpty()) {
            System.out.println("No se encontraron vehículos.");
        } else {
            System.out.println("Vehículos cargados correctamente: " + vehiculos.size());
        }
        listaVehiculos = FXCollections.observableArrayList(vehiculos);
        tVehiculos.setItems(listaVehiculos);
    }

    private void actualizarTabla() {
        listaVehiculos.clear();
        listaVehiculos.addAll(Vehiculos.getInstancia().get());
    }

    @FXML
    void borrarVehiculo(ActionEvent event) {
        Vehiculo vehiculoSeleccionado = tVehiculos.getSelectionModel().getSelectedItem();
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
    void actualizarTabla(ActionEvent event) {
        cargarVehiculos();
    }

    @FXML
    void buscarVehiculo(ActionEvent event) {
        Controlador graficaPrincipal = Controladores.get("/vistas/BuscarVehiculo.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }


}
