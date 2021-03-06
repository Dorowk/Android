package com.example.mati.pcastellano_examen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Pizza[] listaPizza = new Pizza[]{
            new Pizza("Margarita","jamon/tomate",12,R.drawable.pizza1),
            new Pizza("Tres Quesos","Elemental, Azul",15,R.drawable.pizza2),
            new Pizza("Barbacoa","Salsa Barbacoa",18,R.drawable.pizza3),
    };
    TextView nombre;
    TextView descripcion;
    TextView precio;
    ImageView imagen;
    Spinner lista;

    RadioGroup rg;
    RadioButton local;
    RadioButton llevar;

    CheckBox grande;
    CheckBox queso;
    CheckBox ingrediente;

    EditText unidades;
    Button calculo;
    Button factura;

    TextView precioFinal;

    boolean opcionLlevar;
    int extras, total, idImagen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Pizzeria");
        lista = (Spinner) findViewById(R.id.spinnerLista);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        local = (RadioButton) findViewById(R.id.radioLocal);
        llevar = (RadioButton) findViewById(R.id.radioLlevar);

        grande = (CheckBox) findViewById(R.id.checkGrande);
        queso = (CheckBox) findViewById(R.id.checkQueso);
        ingrediente = (CheckBox) findViewById(R.id.checkIngre);

        unidades = (EditText)findViewById(R.id.editText);
        calculo = (Button)findViewById(R.id.buttonResultado);
        factura = (Button)findViewById(R.id.buttonFactura);

        precioFinal = (TextView)findViewById(R.id.textResultado);

        AdaptadorPizza adaptador = new AdaptadorPizza(this);
        lista.setAdapter(adaptador);

       rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           public void onCheckedChanged(RadioGroup group, int idCheck) {
               if (group.getCheckedRadioButtonId() == R.id.radioLocal)
                   opcionLlevar = false;
               if (group.getCheckedRadioButtonId() == R.id.radioLlevar)
                   opcionLlevar = true;
           }


       });
        queso.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (queso.isChecked())
                    extras +=1;
                else
                    extras-=1;
            }
        });

        grande.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (grande.isChecked())
                    extras +=1;
                else
                    extras -=1;
            }
        });

        ingrediente.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ingrediente.isChecked())
                    extras += 1;
                else
                    extras -= 1;
            }
        });




        calculo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                total = 0;
                total = operacion();
                precioFinal.setText(String.valueOf(total));
            }
        });

        factura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion();
                Intent cambio = new Intent(MainActivity.this, Factura.class);
                Bundle paquete = new Bundle();

                paquete.putInt("IMAGEN",idImagen);
                paquete.putString("NOMBRE", nombre.getText().toString());
                paquete.putString("PRECIO", precio.getText().toString());
                paquete.putString("EXTRA", String.valueOf(extras));
                paquete.putString("UNIDADES",(unidades.getText().toString()));
                paquete.putBoolean("TIPO", opcionLlevar);
                paquete.putString("COSTE", String.valueOf(total));

                cambio.putExtras(paquete);
                startActivity(cambio);
            }
        });
    }

    public int operacion(){
        int preciopizza;
        int suma;
        int cantidad;
        int total;

        preciopizza=Integer.parseInt((String)precio.getText());


        suma = preciopizza + extras;
        cantidad = Integer.parseInt(unidades.getText().toString());
        total = suma*cantidad;

        if (opcionLlevar)
            total *= 1.10;

        return  total;

    }

    class AdaptadorPizza extends ArrayAdapter<Pizza> {
        public Activity principal;
        public AdaptadorPizza(Activity actividad) {
            super(actividad, R.layout.spinner_pizza,listaPizza);
            this.principal = actividad;
        }


        @Override
        public View getDropDownView (int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View columna = convertView;
            ViewHolder contenedor;
            if(columna == null) {
                LayoutInflater inflater = getLayoutInflater();
                columna = inflater.inflate(R.layout.spinner_pizza, null);

                contenedor = new ViewHolder();

                contenedor.nombre = (TextView) columna.findViewById(R.id.textNombrePizza);
                contenedor.descripcion = (TextView) columna.findViewById(R.id.textDescripcion);
                contenedor.precio = (TextView) columna.findViewById(R.id.textPrecio);
                contenedor.imagen = (ImageView) columna.findViewById(R.id.imagenPizza);
                columna.setTag(contenedor);

            }else {
                contenedor=(ViewHolder)columna.getTag();}

            contenedor.nombre.setText(listaPizza[position].getNombre());
            contenedor.descripcion.setText(listaPizza[position].getDes());
            contenedor.precio.setText(String.valueOf(listaPizza[position].getPrecio()));
            contenedor.imagen.setImageResource(listaPizza[position].getId());
            idImagen = listaPizza[position].getId();

            return columna;
        }
    }
    static class ViewHolder{
        TextView nombre;
        TextView descripcion;
        TextView precio;
        ImageView imagen;
    }
}
