package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class LanzadorGraficaPrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controlador graficaPrincipal = Controladores.get("/vistas/GraficaPrincipal.fxml", "Taller Mecánico", null);
        graficaPrincipal.getEscenario().show();

    }

    public static void comenzar() {
        launch(LanzadorGraficaPrincipal.class);
    }
}
