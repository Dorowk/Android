package com.example.mati.pcastellano_examen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mati on 9/11/15.
 */
public class Factura extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.factura);

        TextView nombre = (TextView) findViewById(R.id.textNombrePizza);
        TextView base = (TextView) findViewById(R.id.textPrecioBase);
        TextView extras = (TextView) findViewById(R.id.textExtra);
        TextView unidades = (TextView) findViewById(R.id.textUnidades);
        TextView tipo = (TextView) findViewById(R.id.textEnvio);
        TextView total = (TextView) findViewById(R.id.textCoste);
        ImageView imagen =(ImageView)findViewById(R.id.imageView2);

        Bundle  bundle = getIntent().getExtras();

        String nombrePizza = bundle.getString("NOMBRE");
        String precioPizza = bundle.getString("PRECIO");
        String numeroExtras = bundle.getString("EXTRA");
        String unidadesPizza = bundle.getString("UNIDADES");
        String costeTotal = bundle.getString("COSTE");
        Boolean tipoEnvio = bundle.getBoolean("TIPO");
        Integer idImagen = bundle.getInt("IMAGEN");

        nombre.setText(nombrePizza);
        base.setText(precioPizza);
        extras.setText(numeroExtras);
        unidades.setText(unidadesPizza);
        if (tipoEnvio)
            tipo.setText("LLevar");
        if (!tipoEnvio)
            tipo.setText("Local");
        total.setText(costeTotal);
        imagen.setImageResource(idImagen);

    }
}
