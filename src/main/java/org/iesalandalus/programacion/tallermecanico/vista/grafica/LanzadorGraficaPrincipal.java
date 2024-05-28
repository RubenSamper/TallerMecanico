package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class LanzadorGraficaPrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Clientes.getInstancia().comenzar();
        Vehiculos.getInstancia().comenzar();

        Controlador graficaPrincipal = Controladores.get("/vistas/GraficaPrincipal.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Clientes.getInstancia().terminar();
    }

    public static void comenzar() {
        launch(LanzadorGraficaPrincipal.class);
    }
}
