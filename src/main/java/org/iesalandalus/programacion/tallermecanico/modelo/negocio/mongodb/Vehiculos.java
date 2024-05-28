package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.mariadb.jdbc.Statement;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class Vehiculos implements IVehiculos {
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";
    private static final String COLECCCION = "vehiculos";

    private MongoCollection<Document> coleccionVehiculos;

    private static Vehiculos instancia;

    public static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    private Vehiculos() {
    }

    @Override
    public void comenzar() {
        coleccionVehiculos = MongoDB.getBD().getCollection(COLECCCION);

    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    Vehiculo getVehiculo(Document documento) {
        Vehiculo vehiculo = null;
        if (documento != null) {
            vehiculo = new Vehiculo(documento.getString(MARCA), documento.getString(MODELO), documento.getString(MATRICULA));
        }
        return vehiculo;
    }

    private void prepararSentencia(PreparedStatement sentencia, Vehiculo vehiculo) throws SQLException {
        sentencia.setString(1, vehiculo.marca());
        sentencia.setString(2, vehiculo.modelo());
        sentencia.setString(3, vehiculo.matricula());
    }

    @Override
    public List<Vehiculo> get() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Document documento : coleccionVehiculos.find()) {
            vehiculos.add(getVehiculo(documento));
        }
        return vehiculos;
    }

    Document getDocumento(Vehiculo vehiculo) {
        Document documento = null;
        if (vehiculo != null) {
            String marca = vehiculo.marca();
            String modelo = vehiculo.modelo();
            String matricula = vehiculo.matricula();
            documento = new Document().append(MARCA, marca).append(MATRICULA, matricula).append(MODELO, MODELO);
        }
        return documento;
    }

    private Bson getCriterioBusqueda(Vehiculo vehiculo) {
        return eq(MATRICULA, vehiculo.matricula());
    }


    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehiculo nulo.");
        FindOneAndReplaceOptions opciones = new FindOneAndReplaceOptions().upsert(true);
        Document resultado = coleccionVehiculos.findOneAndReplace(getCriterioBusqueda(vehiculo), getDocumento(vehiculo), opciones);
        if (resultado != null) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehiculo nulo.");
        return getVehiculo(coleccionVehiculos.find(getCriterioBusqueda(vehiculo)).first());
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehiculo nulo.");
        DeleteResult resultado = coleccionVehiculos.deleteOne(getCriterioBusqueda(vehiculo));
        if (resultado.getDeletedCount() == 0) {
            throw new OperationNotSupportedException("No existe ningún vehiculo con esa MATRICULA.");
        }
    }
}
