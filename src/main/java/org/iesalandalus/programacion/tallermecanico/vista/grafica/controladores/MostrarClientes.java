package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import java.util.List;

public class MostrarClientes extends Controlador {
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
}
