package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pablo.coches.Objetos.Factura2;
import com.example.pablo.coches.R;

/**
 * Created by Pablo on 28/12/2015.
 */
public class Factura2Activity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_factura);

        TextView modelo = (TextView) findViewById(R.id.textModelo);
        TextView precioHora = (TextView) findViewById(R.id.textPrecioHora);
        TextView extras = (TextView) findViewById(R.id.textExtra);
        TextView tiempo = (TextView) findViewById(R.id.textTiempo);
        TextView seguro = (TextView) findViewById(R.id.textSeguro);
        TextView total = (TextView) findViewById(R.id.textCoste);
        ImageView imagen =(ImageView)findViewById(R.id.imageView2);

        Bundle  bundle = getIntent().getExtras();


        Factura2 factura = bundle.getParcelable("Factura");

        modelo.setText(factura.getModelo());
        precioHora.setText(factura.getPrecioHoras()+"€");
        extras.setText(factura.getExtras()+"€");
        tiempo.setText(factura.getTiempo());
        if (factura.getSeguro())
            seguro.setText("Con Seguro");
        else
            seguro.setText("Sin Seguro");
        total.setText(factura.getTotal()+"€");
        imagen.setImageResource(factura.getId());
    }
}
