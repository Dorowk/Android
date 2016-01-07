package com.example.pablo.coches;

import java.io.Serializable;

/**
 * Created by Pablo on 28/12/2015.
 */
public class Factura implements Serializable {
    private String modelo, precioHoras, tiempo, extras, total;
    private int id;
    private boolean seguro;

    public Factura(String modelo, String precioHoras, String tiempo, String extras, String total, int id, boolean seguro) {
        this.modelo = modelo;
        this.precioHoras = precioHoras;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.id = id;
        this.seguro = seguro;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPrecioHoras() {
        return precioHoras;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getExtras() {
        return extras;
    }

    public String getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public boolean getSeguro() {
        return seguro;
    }
}