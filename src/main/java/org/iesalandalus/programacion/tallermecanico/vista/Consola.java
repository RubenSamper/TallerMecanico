package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy HH:mm:ss";

    private Consola() {

    }

    public static void mostrarCabecera(String mensaje) {
        StringBuilder cabecera = new StringBuilder();
        cabecera.append("-");
        System.out.printf("%n%s%n", mensaje);
        for (int i = 0; i < mensaje.length(); i++) {
            System.out.print(cabecera);
        }
        System.out.println("");
    }

    public static void mostrarMenu() {
        mostrarCabecera("Gestión de un taller Mecánico:");

        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
        do {
            opcion = leerEntero("Elige una opción: ");
        } while (!Opcion.esValida(opcion));
        return Opcion.get(opcion);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            System.out.print(mensaje + " (" + CADENA_FORMATO_FECHA + "): ");
            String fechaTexto = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaTexto, formatter);
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto. Introduce la fecha en formato dd/MM/yyyy.");
            }
        } while (!fechaValida);
        return fecha;
    }

    public static Cliente leerCliente() {
        Cliente clienteNuevo = null;
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String dni = leerCadena("Introduce el DNI del cliente: ");
        String telefono = leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Introduce el DNI del cliente: "));
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce la marca del vehículo: ");
        String modelo = leerCadena("Introduce el modelo del vehículo: ");
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Introduce la matrícula del vehículo: "));
    }

    public static Revision leerRevision() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la revisión");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce las horas trabajadas: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre de la revisión");
    }

}
