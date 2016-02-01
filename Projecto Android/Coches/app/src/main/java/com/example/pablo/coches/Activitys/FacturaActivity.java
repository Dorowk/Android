package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.coches.Objetos.Cliente;
import com.example.pablo.coches.Objetos.Coche;
import com.example.pablo.coches.Objetos.Factura;
import com.example.pablo.coches.R;
import com.example.pablo.coches.SQLiteClass.SQLiteClientes;
import com.example.pablo.coches.SQLiteClass.SQLiteCoches;
import com.example.pablo.coches.SQLiteClass.SQLiteFacturas;

/**
 * Created by Pablo on 28/12/2015.
 */
public class FacturaActivity extends Activity {
    final Context context = this;
    Boolean flag=false;
    Factura factura;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_factura);

        TextView modelo = (TextView) findViewById(R.id.textModelo);
        TextView precioHora = (TextView) findViewById(R.id.textPrecioHora);
        TextView extras = (TextView) findViewById(R.id.textExtra);
        TextView tiempo = (TextView) findViewById(R.id.textTiempo);
        TextView seguro = (TextView) findViewById(R.id.textSeguro);
        TextView total = (TextView) findViewById(R.id.textCoste);
        TextView usuario = (TextView) findViewById(R.id.textUsuario);
        TextView id = (TextView) findViewById(R.id.textIDFactura);

        ImageView imagen = (ImageView) findViewById(R.id.imageView2);
        Button button = (Button) findViewById(R.id.buttonGuardar);

        Bundle bundle = getIntent().getExtras();


        factura = (Factura) bundle.getSerializable("Factura");
        Coche coche= SQLiteCoches.cargarCoche(context, factura.getIdCoche());
        Cliente cliente = SQLiteClientes.cargarCliente(context, factura.getIdCliente());


        usuario.setText(cliente.getNombre());
        id.setText(String.valueOf(cliente.getId()));
        modelo.setText(coche.getNombre()+coche.getMarca());
        precioHora.setText(coche.getPrecio() + "€");
        extras.setText(factura.getExtras() + "€");
        tiempo.setText(String.valueOf(factura.getTiempo()));
        if (factura.getSeguro())
            seguro.setText("Con Seguro");
        else
            seguro.setText("Sin Seguro");
        total.setText(factura.getTotal() + "€");
        imagen.setImageResource(coche.getIdImagen());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag)
                    Toast.makeText(context,"Ya se ha guardado esta factura",Toast.LENGTH_SHORT).show();
                else {
                    SQLiteFacturas SQL = new SQLiteFacturas(context, "DBAppCoches", null, 1);
                    SQL.comprobarBD(SQL);
                    SQL.guardarFactura(SQL, context, factura);
                    Toast.makeText(context, "Factura Guardada", Toast.LENGTH_SHORT).show();
                    flag=true;
                }
            }
        });


    }
}

