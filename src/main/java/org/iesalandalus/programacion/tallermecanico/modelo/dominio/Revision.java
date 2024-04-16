package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35.0f;


    public Revision(Cliente cliente, Vehiculo vehiculo,LocalDate fechaInicio ) {
        super(fechaInicio, vehiculo, cliente);
    }

    @Override
    public float getPrecioEspecifico() {
         return getHoras()*FACTOR_HORA;
    }


    public Revision(Revision revision) {
        super(revision);

    }

}
