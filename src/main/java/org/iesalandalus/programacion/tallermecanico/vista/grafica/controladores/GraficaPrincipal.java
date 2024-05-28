package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class GraficaPrincipal extends Controlador {

    private Clientes clientes;
    private Vehiculos vehiculos;

    @FXML
    private Menu mAcercaDe;

    @FXML
    private Menu mSalir;

    @FXML
    private MenuBar mbPrincipal;
    @FXML
    private Button btBuscarCliente;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btMostrarCliente;

    @FXML
    private Button btInsertarVehiculo;

    @FXML
    private Button btMostrarVehiculo;

    @FXML
    void initialize() {
        clientes = Clientes.getInstancia();
        clientes.comenzar();

        vehiculos = Vehiculos.getInstancia();
        vehiculos.comenzar();
    }

    @FXML
    void insertarCliente(ActionEvent event) {
        InsertarClientes controlador = (InsertarClientes) Controladores.get("/vistas/InsertarCliente.fxml", "Taller Mecánico", null);
        controlador.inicializarClientes(clientes);
        controlador.getEscenario().show();
    }

    @FXML
    void mostrarCliente(ActionEvent event) {
        Controlador graficaPrincipal = Controladores.get("/vistas/MostrarClientes.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }

    @FXML
    void insertarVehiculo(ActionEvent event) {
        InsertarVehiculo controlador = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Taller Mecánico", null);
        controlador.inicializarVehiculos(vehiculos);
        controlador.getEscenario().show();
    }


    @FXML
    void mostrarVehiculos(ActionEvent event) {
        Controlador graficaPrincipal = Controladores.get("/vistas/MostrarVehiculos.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }

    @FXML
    void informacion(ActionEvent event) {
        Controlador graficaPrincipal = Controladores.get("/vistas/AcercaDe.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }

    @FXML
    void salirAplicacion(ActionEvent event) {
        getEscenario().close();
    }
}
