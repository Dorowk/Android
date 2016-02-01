package com.example.pablo.coches.Activitys;

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

import com.example.pablo.coches.Objetos.Cliente;
import com.example.pablo.coches.Objetos.Coche;
import com.example.pablo.coches.Objetos.Factura;
import com.example.pablo.coches.R;
import com.example.pablo.coches.SQLiteClass.SQLiteClientes;
import com.example.pablo.coches.SQLiteClass.SQLiteCoches;
import com.example.pablo.coches.SQLiteClass.SQLiteFacturas;


/**
 * Created by mati on 14/01/16.
 */

public class ListarFacturaActivity extends Activity{
    ListView listView;
    Factura[] listaFacturas;
    ViewHolder holder;
    Context context;
    int id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
        listView = (ListView) findViewById(R.id.listView);
        context = this;

        Comprobar();
        id = getIntent().getExtras().getInt("ID",-1);


        listaFacturas = SQLiteFacturas.CargarArray(context,id);

        AdaptadorFacturas adaptadorFacturas = new AdaptadorFacturas(this);
        listView.setAdapter(adaptadorFacturas);
        registerForContextMenu(listView);

    }
    private void Comprobar(){
        SQLiteFacturas SQL = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQL.comprobarBD(SQL);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listView) {
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
                Intent intent = new Intent(this,InfoFactura_Activity.class);
                intent.putExtra("ID",listaFacturas[info.position].getId());
                startActivity(intent);
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

                holder.cliente=(TextView)columna.findViewById(R.id.textCliente);
                holder.nombre =(TextView)columna.findViewById(R.id.facturaNombre);
                holder.horas =(TextView)columna.findViewById(R.id.facturaTiempo);
                holder.total =(TextView)columna.findViewById(R.id.facturaTotal);
                holder.imagen =(ImageView)columna.findViewById(R.id.facturaImagen);

                columna.setTag(holder);
            }else{
                holder=(ViewHolder)columna.getTag();
            }

            Coche coche= SQLiteCoches.cargarCoche(context, listaFacturas[position].getIdCoche());
            Cliente cliente = SQLiteClientes.cargarCliente(context,listaFacturas[position].getIdCliente());

            holder.cliente.setText(cliente.getNombre());
            holder.nombre.setText(coche.getNombre()+" "+coche.getMarca());
            holder.horas.setText(String.valueOf(listaFacturas[position].getTiempo()));
            holder.total.setText(String.valueOf(listaFacturas[position].getTotal()));
            holder.imagen.setImageResource(coche.getIdImagen());


            return columna;
        }
    }

    class ViewHolder{
        TextView nombre,horas,total,cliente;
        ImageView imagen;
    }
}
