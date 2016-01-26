package com.example.pablo.coches.Objetos;

/**
 * Created by mati on 25/01/16.
 */
public class Cliente {
    private String usuario,password,nombre,correo;
    private int id,telfono;

    public Cliente(String usuario, int telfono, String correo, String nombre, String password) {
        this.usuario = usuario;
        this.telfono = telfono;
        this.correo = correo;
        this.nombre = nombre;
        this.password = password;
    }

    public Cliente(String usuario, String password, String nombre, String correo, int id, int telfono) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.telfono = telfono;
    }
}
