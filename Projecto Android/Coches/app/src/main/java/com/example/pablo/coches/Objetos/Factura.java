package com.example.pablo.coches.Objetos;

import java.io.Serializable;

/**
 * Created by Pablo on 28/12/2015.
 */
public class Factura implements Serializable {

    private int id,idCoche, idCliente, tiempo, total, extras;
    private boolean seguro;

    public Factura(int idCoche,int idCliente, int tiempo, int extras, int total, boolean seguro) {
        this.idCoche=idCoche;
        this.idCliente=idCliente;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.seguro = seguro;
    }
    public Factura(int id, int idCoche,int idCliente, int tiempo, int extras, int total, boolean seguro) {
        this.id=id;
        this.idCoche=idCoche;
        this.idCliente=idCliente;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.seguro = seguro;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public int getId() {
        return id;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getTotal() {
        return total;
    }

    public int getExtras() {
        return extras;
    }

    public boolean getSeguro() {
        return seguro;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
