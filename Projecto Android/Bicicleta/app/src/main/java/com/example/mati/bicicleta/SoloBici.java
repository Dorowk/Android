package com.example.mati.bicicleta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SoloBici extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bJuego;
    private Button bPreferencias;
    private Button bSalir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bici);

        //Boton y escuchador para la pantalla "Juego"
        bJuego = (Button) findViewById(R.id.Boton01);
        bJuego.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarJuego();
            }
        });
        //Boton y escuchador para la pantalla "Acerca de"
        bAcercaDe = (Button) findViewById(R.id.Boton03);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe();
            }
        });
        //Boton y escuchador para la pantalla "Salir"
        bSalir = (Button) findViewById(R.id.Boton04);
        bSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarSalir();
            }
        });
    }

    //Metodo que activa la pantalla Juego
    public void lanzarJuego(){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla AcercaDe
    public void lanzarAcercaDe(){
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }


    //Metodo que activa la pantalla AcercaDe
    public void lanzarSalir(){
        finish();
    }

}