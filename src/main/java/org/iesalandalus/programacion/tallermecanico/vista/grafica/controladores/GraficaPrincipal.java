package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class GraficaPrincipal extends Controlador {
    private Clientes clientes;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btMostrarCliente;

    @FXML
    private Button btInsertarVehiculo;

    @FXML
    void initialize() {
        clientes = Clientes.getInstancia();
        clientes.comenzar();
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
        Controlador graficaPrincipal = Controladores.get("/vistas/InsertarVehiculo.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }

}
