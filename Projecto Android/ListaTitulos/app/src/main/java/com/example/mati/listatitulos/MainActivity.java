package com.example.mati.listatitulos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Titulos[] listaTitulos = new Titulos[] {
            new Titulos("Titulo 1", "Subtitulo 1"),
            new Titulos("Titulo 2", "Subtitulo 2"),
            new Titulos("Titulo 3", "Subtitulo 3"),
            new Titulos("Titulo 4", "Subtitulo 4"),
            new Titulos("Titulo 5", "Subtitulo 5"),

    };

    TextView titulo;
    TextView subtitulo;
    Spinner spinner;
    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        boton = (Button)findViewById(R.id.button);

        Adaptador adaptador = new Adaptador(this);
        spinner.setAdapter(adaptador);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = titulo.getText() +", "+ subtitulo.getText();
                showToast(mensaje);
            }
        });


    }

    public  void showToast(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }


    class Adaptador extends ArrayAdapter<Titulos> {
        public Activity principal;
        public Adaptador(Activity actividad) {
            super(actividad, R.layout.titulos_layout,listaTitulos);
            this.principal = actividad;
        }


        @Override
        public View getDropDownView (int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=principal.getLayoutInflater();
            View columna=inflater.inflate(R.layout.titulos_layout,parent,false);

            titulo=(TextView)columna.findViewById(R.id.textView);
            titulo.setText(listaTitulos[position].getTitulo());

            subtitulo=(TextView)columna.findViewById(R.id.textView2);
            subtitulo.setText(listaTitulos[position].getSubtitulo());

            return columna;
        }
    }
    /*

     */


}


