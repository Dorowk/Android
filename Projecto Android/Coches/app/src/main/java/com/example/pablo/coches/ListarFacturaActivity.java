package com.example.pablo.coches;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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

        listaFacturas = SQLiteFacturas.CargarArray(context);

        AdaptadorFacturas adaptadorFacturas = new AdaptadorFacturas(this);
        listView.setAdapter(adaptadorFacturas);
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listaFactura) {
            menu.setHeaderTitle("Menu de Opciones");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_listafacturas, menu);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        switch (menuItemIndex) {
            case R.id.menuFacturaBorrar:
                createAndShowAlertDialog(info.position);
                return true;
            case R.id.menuFacturaInfo:
                Toast.makeText(context,"Info",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }
    private void createAndShowAlertDialog(final int num) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmacion");
        builder.setMessage("Esta seguro de querer borrar el registro");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SQLiteFacturas.borrarRegistro(getBaseContext(), listaFacturas[num].getId()) ;
                refresh();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"Operacion Cancelada",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void refresh(){
        Intent refresh = new Intent(this,ListarFacturaActivity.class);
        startActivity(refresh);
        this.finish();
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
            holder.imagen.setImageResource(listaFacturas[position].getImagenId());


            return columna;
        }
    }

    class ViewHolder{
        TextView nombre,horas,extra,total,seguro;
        ImageView imagen;
    }
}
