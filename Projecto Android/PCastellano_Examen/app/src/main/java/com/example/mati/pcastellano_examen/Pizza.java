package com.example.mati.pcastellano_examen;

/**
 * Created by mati on 9/11/15.
 */
public class Pizza {
    String nombre, des;
    int precio, id;

    public Pizza(String nombre, String descrip, int precio, int id) {
        this.nombre = nombre;
        this.des = descrip;
        this.precio = precio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDes() {
        return des;
    }


    public int getPrecio() {
        return precio;
    }


    public int getId() {
        return id;
    }

}
