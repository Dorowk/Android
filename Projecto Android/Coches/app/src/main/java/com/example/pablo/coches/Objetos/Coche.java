package com.example.pablo.coches.Objetos;


/**
 * Created by Pablo on 28/12/2015.
 */
public class Coche {
    private String nombre, marca;
    private int precio, id, idImagen;

    public Coche(String nombre, String marca, int precio, int idImagen) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.idImagen = idImagen;
    }
    public Coche(int id,String nombre, String marca, int precio, int idImagen) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.idImagen = idImagen;
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

    public int getIdImagen() {
        return idImagen;
    }
}
