package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {

    INSERTAR_CLIENTE(1, "Insertar cliente."),
    BUSCAR_CLIENTE(2, "Buscar Cliente."),
    BORRAR_CLIENTE(3, "Borrar Cliente."),
    LISTAR_CLIENTE(4, "Listar Cliente."),
    MODIFICAR_CLIENTE(5, "Modificar cliente."),
    INSERTAR_VEHICULO(6, "Insertar vehículo."),
    BUSCAR_VEHICULO(7, "Buscar vehículo."),
    BORRAR_VEHICULO(8, "Borrar vehículo."),
    LISTAR_VEHICULO(9, "Listar vehículo."),
    INSERTAR_REVISION(10, "Insertar revisión."),
    BUSCAR_REVISION(11, "Buscar revisión."),
    BORRAR_REVISION(12, "Borrar revisión."),
    LISTAR_REVISIONES(13, "Listar revisiones."),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisiones cliente."),
    LISTAR_REVISIONES_VEHICULO(15, "Listar revisiones vehiculo."),
    ANADIR_HORAS_REVISION(16, "Añadir horas revision."),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio material revisión."),
    CERRAR_REVISION(18, "Cerrar revisión."),
    SALIR(0, "Salir.");

    private final int numeroOpcion;
    private final String mensaje;

    private static Map<Integer, Opcion> opciones = new TreeMap<>();

    static {
        for (Opcion opcion : values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    private Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (esValida(numeroOpcion)) {
            return opciones.get(numeroOpcion);
        } else {
            throw new IllegalArgumentException("Número de opción no válido");
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.numeroOpcion, this.mensaje);
    }
}
