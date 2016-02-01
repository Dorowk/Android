package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.coches.R;
import com.example.pablo.coches.SQLiteClass.SQLiteClientes;

/**
 * Created by mati on 26/01/16.
 */
public class NewClientActivity extends Activity {
    EditText usuario,password,nombre,telefono,correo;
    TextView estado;
    Button guardar;
    Boolean valido;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newclient);
        context = this;

        usuario = (EditText)findViewById(R.id.editUsu2);
        password = (EditText)findViewById(R.id.editPass2);
        nombre = (EditText)findViewById(R.id.editNombre2);
        telefono = (EditText)findViewById(R.id.editTelefono2);
        correo= (EditText)findViewById(R.id.editCorreo2);
        estado=(TextView)findViewById(R.id.textEstado2);

        guardar=(Button)findViewById(R.id.buttonGuardar2);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()) {
                    cargarDatos();
                    Intent Cliente = new Intent(context, ClienteActivity.class);
                    startActivity(Cliente);
                }else
                    Toast.makeText(context,"Datos no validos. Asegurate que los campos " +
                            "no estan vacios y los datos son validos",Toast.LENGTH_SHORT).show();
            }
        });

        usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                valido = comprobarTexto();
            }
        });

    }
    private boolean validar(){
        Boolean aux1=!password.getText().toString().isEmpty();
        Boolean aux2=!nombre.getText().toString().isEmpty();
        Boolean aux3=!telefono.getText().toString().isEmpty();
        Boolean aux4=!correo.getText().toString().isEmpty();

        return valido && aux1 && aux2 && aux3 && aux4;
    }

    private void cargarDatos(){
        String[] args = new String[]{
                usuario.getText().toString(),
                password.getText().toString(),
                nombre.getText().toString(),
                correo.getText().toString(),
                telefono.getText().toString(),
        };
        SQLiteClientes SQL = new SQLiteClientes(context, "DBAppCoches", null, 1);
        SQL.insertarCuenta(SQL,args);

    }

    private boolean comprobarTexto(){
        if (usuario.getText().toString().isEmpty()){
            estado.setText("");
            return false;
        }else if(usuario.getText().toString().length()<=4){
            estado.setText("El usuario debe tener una longitud de 5 o mas");
            estado.setTextColor(Color.RED);
            usuario.setTextColor(Color.RED);
            return false;
        }else{
            String aux = usuario.getText().toString();
            String[] args = new String[]{aux};
            int id = SQLiteClientes.comprobarCuenta(context, args);
            if (id == 0) {
                valido();
                return true;
            } else {
                invalido();
                return false;
            }
        }
    }
    private void valido(){
        estado.setText("Usuario Valido");
        estado.setTextColor(Color.rgb(102,255,102));
        usuario.setTextColor(Color.rgb(102,255,102));

    }
    private void invalido(){
        estado.setText("Usuario ya en uso, introduzca otro");
        estado.setTextColor(Color.RED);
        usuario.setTextColor(Color.RED);
    }
}
