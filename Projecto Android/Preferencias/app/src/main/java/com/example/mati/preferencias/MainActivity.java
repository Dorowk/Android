package com.example.mati.preferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button guardar;
    private Button mostrar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardar = (Button)findViewById(R.id.buttonGuardar);
        mostrar = (Button)findViewById(R.id.buttonMostrar);

        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Preferencias.class));
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this);


                boolean pref1 = pref.getBoolean("opcion1", false);
                String pref2= pref.getString("opcion2", "");
                String pref3 = pref.getString("opcion3", "");

                Toast.makeText(getApplicationContext(),String.valueOf(pref1),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref2,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref3,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

