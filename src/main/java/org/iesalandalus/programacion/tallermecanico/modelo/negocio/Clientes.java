package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private final List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();

    }

    public List<Cliente> get() {

        return new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");

        int indice = coleccionClientes.indexOf(cliente);

        if (indice != -1) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }

        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);

        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }

        if ((nombre != null) && (!nombre.isBlank())) {
            buscar(cliente).setNombre(nombre);
        }
        if ((telefono != null) && (!telefono.isBlank())) {
            buscar(cliente).setTelefono(telefono);
        }

        return true;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        Cliente aux = null;

        if (indice != -1) {
            aux = coleccionClientes.get(indice);
        }

        return aux;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");

        int indice = coleccionClientes.indexOf(cliente);

        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(indice);

    }
}
