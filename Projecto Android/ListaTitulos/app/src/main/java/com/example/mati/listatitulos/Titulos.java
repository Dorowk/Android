package com.example.mati.listatitulos;

/**
 * Created by mati on 9/11/15.
 */
public class Titulos {

    private String titulo;
    private String subtitulo;

    public Titulos(String titulo,String subtitulo ){
        super();
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }


    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
}

