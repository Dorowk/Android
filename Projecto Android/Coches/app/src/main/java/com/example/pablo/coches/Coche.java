package com.example.pablo.coches;


/**
 * Created by Pablo on 28/12/2015.
 */
public class Coche {
    private String nombre, marca;
    private int precio, id;

    public Coche(String nombre, String marca, int precio, int id) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public int getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }
}
