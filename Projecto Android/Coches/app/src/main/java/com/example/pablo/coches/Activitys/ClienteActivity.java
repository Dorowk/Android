package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.coches.R;
import com.example.pablo.coches.SQLiteClass.SQLiteClientes;

/**
 * Created by mati on 25/01/16.
 */
public class ClienteActivity extends Activity {
    EditText user,pass;
    Button conectar;
    TextView registro;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_layout);
        context=this;

        comprobar();

        user =(EditText)findViewById(R.id.editUsuario);
        pass=(EditText)findViewById(R.id.editPassword);
        conectar=(Button)findViewById(R.id.buttonConectar);
        registro=(TextView)findViewById(R.id.textRegistro);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Cliente = new Intent(context, NewClientActivity.class);
                startActivity(Cliente);
            }
        });

    }

    private void iniciar(){
        String usuario = user.getText().toString(), password = pass.getText().toString();
        String[] args = new String[]{usuario,password};
        int id = SQLiteClientes.comprobarCuenta(context, args);
        if (id!=0)
            Toast.makeText(context,String.valueOf(id),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Usuario o contraseña incorrectos \n¿Has creado la cuenta?",Toast.LENGTH_SHORT).show();
    }

    private void comprobar(){
        SQLiteClientes SQL = new SQLiteClientes(context, "DBAppCoches", null, 1);
        SQL.comprobarBD(SQL);
    }

}
