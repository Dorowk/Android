package com.example.pablo.coches;

import java.io.Serializable;

/**
 * Created by Pablo on 28/12/2015.
 */
public class Factura implements Serializable {
    private String nombre;
    private int imagenId, id, precioHoras, tiempo, total, extras;
    private boolean seguro;

    Factura(String nombre, int precioHoras,int tiempo, int extras,  int total, int idImagen, boolean seguro) {
        this.nombre = nombre;
        this.precioHoras = precioHoras;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.imagenId = idImagen;
        this.seguro = seguro;
    }
    Factura(int id,String nombre, int precioHoras,int tiempo, int extras,  int total, int idImagen, boolean seguro) {
        this.id = id;
        this.nombre = nombre;
        this.precioHoras = precioHoras;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.imagenId = idImagen;
        this.seguro = seguro;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenId() {
        return imagenId;
    }

    public int getPrecioHoras() {
        return precioHoras;
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
}
