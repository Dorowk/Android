package com.example.mati.prueba1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by mati on 5/10/15.
 */
public class Pantalla2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView otroSaludo = (TextView) findViewById(R.id.miMensaje);
        final Button volverBtn = (Button) findViewById(R.id.miVolver);
        Bundle miBundleRecoger = getIntent().getExtras();
        otroSaludo.setText(miBundleRecoger.getString("TEXTO"));
        final String completarSaludo = miBundleRecoger.getString("TEXTO");
        showAlert();

        volverBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent vueltaIntent = new Intent();
                Bundle vueltaBundle = new Bundle();
                String mensajeDevuelto = "Devuelvo a Principal " + completarSaludo;
                vueltaBundle.putString("DEVUELTO", mensajeDevuelto);
                vueltaIntent.putExtras(vueltaBundle);
                setResult(RESULT_OK, vueltaIntent);
                finish();

            }
        });
        ImageButton miBotonImagenVolver = (ImageButton) findViewById(R.id.imageButton);
        miBotonImagenVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent vueltaIntent = new Intent();
                Bundle vueltaBundle = new Bundle();
                String mensajeDevuelto = "Devuelvo a Principal " + completarSaludo;
                vueltaBundle.putString("DEVUELTO", mensajeDevuelto);
                vueltaIntent.putExtras(vueltaBundle);
                setResult(RESULT_OK, vueltaIntent);
                finish();
            }
        });
    }
    protected void showAlert() {
        CharSequence text = getResources().getString(R.string.noNameMsg2);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();
    }


}



