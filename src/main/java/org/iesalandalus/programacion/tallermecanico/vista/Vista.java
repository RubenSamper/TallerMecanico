package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion aux;
        do {
            Consola.mostrarMenu();
            aux = Consola.elegirOpcion();
            ejecutar(aux);
        } while (aux != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Proceso terminado; ¡Hasta la próxima!");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case SALIR -> salir();
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTE -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULO -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrarRevision();
        }
    }

    private void insertarCliente() {
        Consola.mostrarCabecera("Insertar clientes");
        try {
            controlador.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Se ha insertado el cliente");
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar turismo");
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }

        System.out.println("Se ha insertado un vehículo.");
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("Insertar revisión");
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha insertado una revisión.");

    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        try {
            controlador.buscar(Consola.leerCliente());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Se ha buscado el cliente.");

    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehiculo");
        try {
            controlador.buscar(Consola.leerCliente());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Se ha buscado el vehículo.");
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        try {
            controlador.buscar(Consola.leerCliente());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Se ha buscado la revisión.");
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("Modificar cliente");

        try {
            controlador.modificar(Consola.leerCliente(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Se ha modificado el cliente.");
    }

    private void anadirHoras() {
        Consola.mostrarCabecera("Añadir horas");
        try {
            Revision revision = Consola.leerRevision();
            int horas = Consola.leerHoras();
            controlador.anadirHoras(revision, horas);
            System.out.println("Se han añadido las horas.");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se han añadido las horas.");
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("Añadir precio material");
        try {
            Revision revision = Consola.leerRevision();
            float precioMaterial = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision, precioMaterial);
            System.out.println("Se ha añadido el precio del material.");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha añadido el precio del material.");
    }


    private void cerrarRevision() {
        Consola.mostrarCabecera("Cerrar revisión");
        try {
            Revision revision = Consola.leerRevision();
            controlador.cerrar(revision, Consola.leerFechaCierre());
            System.out.println("Revisión cerrada correctamente.");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha cerrado la revisión.");
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        try {
            controlador.borrar(Consola.leerCliente());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha borrado el cliente.");
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar Vehículo");
        try {
            controlador.borrar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha borrado el Vehículo.");
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("Borrar Revisión");
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha borrado la Revisión.");
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar Cliente");
        try {
            for (Cliente aux : controlador.getClientes()) {
                System.out.println(aux);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha listado los clientes.");

    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar Vehículo");
        try {
            for (Vehiculo aux : controlador.getVehiculos()) {
                System.out.println(aux);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha listado los Vehículos.");
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar Revisiones");
        try {
            for (Revision aux : controlador.getRevisiones()) {
                System.out.println(aux);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha listado las Revisiones.");
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones de cliente");
        try {
            for (Revision aux : controlador.getRevisiones(Consola.leerCliente())) {
                System.out.println(aux);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha listado las revisiones de cliente.");
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones de vehículo");
        try {
            for (Revision aux : controlador.getRevisiones(Consola.leerVehiculo())) {
                System.out.println(aux);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Se ha listado las revisiones de vehículo.");
    }

    private void salir() {
        controlador.terminar();
    }
}
