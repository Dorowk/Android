package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
 * Created by mati on 1/02/16.
 */
public class InfoFactura_Activity extends Activity {
    Context context;
    int idCli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_infofactura);

        idCli = getIntent().getExtras().getInt("ID");
        context = this;
        TextView usuario,idCliente,telefono,correo,nombre,modelo,marca,tiempo,extra,total,seguro;
        ImageView imagen;
        ImageButton button = (ImageButton)findViewById(R.id.buttonBorrar);
        final Factura factura= SQLiteFacturas.CargarFactura(context,idCli);
        Coche coche= SQLiteCoches.cargarCoche(context, factura.getIdCoche());
        Cliente cliente = SQLiteClientes.cargarCliente(context, factura.getIdCliente());


        usuario=(TextView)findViewById(R.id.infoUsuario);
        idCliente =(TextView)findViewById(R.id.infoID);
        telefono =(TextView)findViewById(R.id.infoTelefono);
        correo =(TextView)findViewById(R.id.infoCorreo);
        nombre =(TextView)findViewById(R.id.infoNombre);

        modelo =(TextView)findViewById(R.id.infoModelo);
        marca =(TextView)findViewById(R.id.infoMarca);

        tiempo =(TextView)findViewById(R.id.infoTiempo);
        extra =(TextView)findViewById(R.id.infoExtras);
        seguro =(TextView)findViewById(R.id.infoSeguro);
        total =(TextView)findViewById(R.id.infoPrecio);
        imagen =(ImageView)findViewById(R.id.infoImagen);

        String seguroText;
        if(factura.getSeguro())
        seguroText = "Con Seguro";
        else
        seguroText= "Sin seguro";

        usuario.setText(cliente.getUsuario());
        idCliente.setText(String.valueOf(cliente.getId()));
        telefono.setText(String.valueOf(cliente.getTelfono()));
        correo.setText(cliente.getCorreo());
        nombre.setText(cliente.getNombre());

        modelo.setText(coche.getNombre());
        marca.setText(coche.getMarca());
        imagen.setImageResource(coche.getIdImagen());

        tiempo.setText(String.valueOf(factura.getTiempo()));
        extra.setText(String.valueOf(factura.getTiempo()));
        seguro.setText(seguroText);
        total.setText(String.valueOf(factura.getTiempo()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog(factura);

            }
        });
    }
    private void createAndShowAlertDialog(final Factura factura) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmacion");
        builder.setMessage("Esta seguro de querer borrar el registro");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SQLiteFacturas.borrarRegistro(getBaseContext(), factura.getId()) ;
                Intent intent = new Intent(context,ListarFacturaActivity.class);
                intent.putExtra("ID",idCli);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "Operacion Cancelada", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
