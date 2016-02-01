package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.coches.Objetos.Cliente;
import com.example.pablo.coches.R;
import com.example.pablo.coches.SQLiteClass.SQLiteClientes;

/**
 * Created by mati on 28/01/16.
 */
public class ListarClientesActivity extends Activity {
    Cliente[] clientes;
    ViewHolder holder;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);

        context=this;
        Comprobar();

        clientes = SQLiteClientes.CargarArray(this);
        ListView list = (ListView) findViewById(R.id.listView);
        AdaptadorClientes adapter = new AdaptadorClientes(this);
        list.setAdapter(adapter);


    }

    private void Comprobar(){
        SQLiteClientes SQL = new SQLiteClientes(context, "DBAppCoches", null, 1);
        SQL.comprobarBD(SQL);
    }

    class AdaptadorClientes extends ArrayAdapter<Cliente> {
        Activity main;

        public AdaptadorClientes(Activity activity) {
            super(activity,R.layout.lista_clientes,clientes);
            this.main = activity;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View columna = convertView;

            if(columna==null) {
                LayoutInflater inflater = getLayoutInflater();
                columna = inflater.inflate(R.layout.lista_clientes,null);

                holder=new ViewHolder();

                holder.id =(TextView)columna.findViewById(R.id.textID3);
                holder.usuario =(TextView)columna.findViewById(R.id.textUsuario3);
                holder.contraseña =(TextView)columna.findViewById(R.id.textPassword3);
                holder.nombre =(TextView)columna.findViewById(R.id.textNombre3);
                holder.telefono =(TextView)columna.findViewById(R.id.textTelefono3);
                holder.correo =(TextView)columna.findViewById(R.id.textCorreo3);

                columna.setTag(holder);
            }else{
                holder=(ViewHolder)columna.getTag();
            }

            String valor;
            if(String.valueOf(clientes[position].getTelfono()).equalsIgnoreCase("0") )
                valor="";
            else
                valor = String.valueOf(clientes[position].getTelfono());

            holder.id.setText(String.valueOf(clientes[position].getId()));
            holder.usuario.setText(clientes[position].getNombre());
            holder.contraseña.setText(clientes[position].getPassword());
            holder.nombre.setText(clientes[position].getNombre());
            holder.telefono.setText(valor);
            holder.correo.setText(clientes[position].getCorreo());

            return columna;
        }
    }

    class ViewHolder{
        TextView id,usuario,contraseña,nombre,telefono,correo;
    }

}
