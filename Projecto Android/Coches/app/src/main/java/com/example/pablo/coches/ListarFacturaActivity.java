package com.example.pablo.coches;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



/**
 * Created by mati on 14/01/16.
 */

public class ListarFacturaActivity extends Activity{
    ListView listView;
    Factura[] listaFacturas;
    ViewHolder holder;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listarfactura_layout);
        listView = (ListView) findViewById(R.id.listaFactura);
        context = this;

        listaFacturas = SQLiteHelper.CargarArray(context);

        AdaptadorFacturas adaptadorFacturas = new AdaptadorFacturas(this);
        listView.setAdapter(adaptadorFacturas);

    }

    class AdaptadorFacturas extends ArrayAdapter<Factura>{
        Activity main;

        public AdaptadorFacturas(Activity activity) {
            super(activity,R.layout.lista_factura,listaFacturas);
            this.main = activity;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View columna = convertView;

            if(columna==null) {
                LayoutInflater inflater = getLayoutInflater();
                columna = inflater.inflate(R.layout.lista_factura,null);

                holder=new ViewHolder();

                holder.nombre =(TextView)columna.findViewById(R.id.facturaNombre);
                holder.horas =(TextView)columna.findViewById(R.id.facturaTiempo);
                holder.extra =(TextView)columna.findViewById(R.id.facturaExtras);
                holder.total =(TextView)columna.findViewById(R.id.facturaTotal);
                holder.seguro =(TextView)columna.findViewById(R.id.facturaSeguro);
                holder.imagen =(ImageView)columna.findViewById(R.id.facturaImagen);

                columna.setTag(holder);
            }else{
                holder=(ViewHolder)columna.getTag();
            }

            String seguroText;
            if(listaFacturas[position].getSeguro())
                seguroText = "Con Seguro";
            else
                seguroText= "Sin seguro";

            holder.nombre.setText(listaFacturas[position].getNombre());
            holder.horas.setText(listaFacturas[position].getTiempo());
            holder.extra.setText(listaFacturas[position].getExtras());
            holder.total.setText(listaFacturas[position].getTotal());
            holder.seguro.setText(seguroText);
            holder.imagen.setImageResource(listaFacturas[position].getId());


            return columna;
        }
    }

    class ViewHolder{
        TextView nombre,horas,extra,total,seguro;
        ImageView imagen;
    }
}
