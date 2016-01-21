package com.example.pablo.coches;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Coche[] listaCoches;

    TextView  precioFinal;

    Spinner lista;

    RadioGroup rg;
    RadioButton sin,con;

    CheckBox aire,gps,radio;

    EditText tiempo;
    Button calcular,factura,factura2;

    ViewHolder holder;

    boolean opcionSeguro;
    int extras, total, idImagen, position;

    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Alquiler de Coches");
        context = this;

        CargarDatos();
        listaCoches = SQLiteCoches.CargarArray(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lista = (Spinner) findViewById(R.id.spinnerLista);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        sin = (RadioButton) findViewById(R.id.radioSin);
        con = (RadioButton) findViewById(R.id.radioSeguro);

        aire = (CheckBox) findViewById(R.id.checkAire);
        gps = (CheckBox) findViewById(R.id.checkGPS);
        radio = (CheckBox) findViewById(R.id.checkRadio);

        tiempo = (EditText)findViewById(R.id.editText);
        calcular = (Button)findViewById(R.id.buttonResultado);
        factura = (Button)findViewById(R.id.buttonFactura);
        factura2 = (Button)findViewById(R.id.buttonFactura2);

        precioFinal = (TextView)findViewById(R.id.textResultado);


        AdaptadorCoches adapter = new AdaptadorCoches(this);
        lista.setAdapter(adapter);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int idCheck) {
                if (group.getCheckedRadioButtonId() == R.id.radioSin)
                    opcionSeguro = false;
                if (group.getCheckedRadioButtonId() == R.id.radioSeguro)
                    opcionSeguro = true;
            }
        });

        aire.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (aire.isChecked())
                    extras +=20;
                else
                    extras -=20;
            }
        });

        gps.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (gps.isChecked())
                    extras += 10;
                else
                    extras -= 10;
            }
        });

        radio.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radio.isChecked())
                    extras +=5;
                else
                    extras -=5;
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!tiempo.getText().toString().isEmpty()) {
                    total = operacion();
                    precioFinal.setText(String.valueOf(total));
                }else
                    Toast.makeText(getApplicationContext(), "Inserta las horas", Toast.LENGTH_SHORT).show();
            }
        });

        factura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!tiempo.getText().toString().isEmpty() || listaCoches.length!=0 ) {
                    total = operacion();
                    Factura factura = crearFactura();

                    Intent intent = new Intent(MainActivity.this, FacturaActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("Factura", factura);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(tiempo.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Inserta las horas", Toast.LENGTH_SHORT).show();
                else if(listaCoches.length == 0)
                    Toast.makeText(getApplicationContext(), "Lista de coches vacia", Toast.LENGTH_SHORT).show();


            }
        });
        factura2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* if (!tiempo.getText().toString().isEmpty()) {
                    total = operacion();
                    Factura2 factura = crearFactura2();

                    Intent intent = new Intent(MainActivity.this, Factura2Activity.class);
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("Factura", factura);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Inserta las horas", Toast.LENGTH_SHORT).show();
            */
                Toast.makeText(context,"Parcelable-Sin Uso-Comentado",Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void CargarDatos(){
        SQLiteCoches SQH = new SQLiteCoches(context, "DBAppCoches", null, 1);
        if (SQH.comprobarBD(context))
            refresh();

    }
    private void refresh(){
        Intent refresh = new Intent(this,MainActivity.class);
        startActivity(refresh);
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Opcion1:
                Intent dibujo = new Intent(this, Imagen.class);
                startActivity(dibujo);
                return true;
            case R.id.Opcion2:
                Intent acerca = new Intent(this, AcercaDe.class);
                startActivity(acerca);
                return true;
            case R.id.Opcion3:
                Intent facturas = new Intent(this,ListarFacturaActivity.class);
                startActivity(facturas);
                return true;
            case R.id.Opcion4:
                SQLiteCoches.onDelete(context);
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private Factura crearFactura(){
        String aux1 = holder.marca.getText().toString()+" "+holder.nombre.getText().toString();
        int aux2 = Integer.valueOf(holder.precio.getText().toString());
        int aux3 = Integer.valueOf(tiempo.getText().toString());
        int aux4 = extras;
        int aux5 = total;
        int aux6 = idImagen;
        boolean aux7 = opcionSeguro;

        return new Factura(aux1,aux2,aux3,aux4,aux5,aux6,aux7);
    }

    /*private Factura2 crearFactura2(){
        String aux1 = holder.marca.getText().toString()+" "+holder.nombre.getText().toString();
        String aux2 = holder.precio.getText().toString();
        String aux3 = tiempo.getText().toString();
        String aux4 = Integer.toString(extras);
        String aux5 = Integer.toString(total);
        int aux6 = idImagen;


        Factura2 factura2 = new Factura2();

        factura2.setModelo(aux1);
        factura2.setPrecioHoras(aux2);
        factura2.setTiempo(aux3);
        factura2.setExtras(aux4);
        factura2.setTotal(aux5);
        factura2.setId(aux6);


        return  factura2;
    }*/


    private int operacion(){
        int precioHoras,horas,total;
        precioHoras = Integer.parseInt(holder.precio.getText().toString());
        horas = Integer.parseInt(tiempo.getText().toString());
        total = precioHoras*horas+extras;

        if(opcionSeguro)
            total *= 1.20;

        return total;
    }
    class AdaptadorCoches extends ArrayAdapter<Coche>{
        public Activity main;

        public AdaptadorCoches(Activity activity) {
            super(activity,R.layout.spinner_coches,listaCoches);
            this.main = activity;
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View columna = convertView;


            if(columna==null) {
                LayoutInflater inflater = getLayoutInflater();
                columna = inflater.inflate(R.layout.spinner_coches,null);
                holder=new ViewHolder();

                holder.nombre = (TextView) columna.findViewById(R.id.textNombre);
                holder.marca = (TextView) columna.findViewById(R.id.textMarca);
                holder.precio = (TextView) columna.findViewById(R.id.textPrecio);
                holder.imagen = (ImageView) columna.findViewById(R.id.imagenCoche);

                columna.setTag(holder);
            }else{
                holder=(ViewHolder)columna.getTag();
            }

            holder.nombre.setText(listaCoches[position].getNombre());
            holder.marca.setText(listaCoches[position].getMarca());
            holder.precio.setText(String.valueOf(listaCoches[position].getPrecio()));
            holder.imagen.setImageResource(listaCoches[position].getIdImagen());
            idImagen = listaCoches[position].getIdImagen();

            return columna;

        }
    }

    class ViewHolder{
        TextView nombre,marca,precio,id;
        ImageView imagen;
    }
}

