package com.example.mati.sumaresta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText num1= (EditText)findViewById(R.id.editText);
        final EditText num2= (EditText)findViewById(R.id.editText2);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup);
        int numero1 = Integer.parseInt(num1.getText().toString());
        int numero2 = Integer.parseInt(num2.getText().toString());
        int resultado;
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int idCheck) {
                if (group.getCheckedRadioButtonId() == R.id.sumaButton)
                    opcion = "suma";
                if (group.getCheckedRadioButtonId() == R.id.restaButton)
                    opcion = "resta";
            }
        });
        final Button result = (Button)findViewById(R.id.button);
        result.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                operacion();
            }
        });

    }
    public void operacion(){
        final EditText num1= (EditText)findViewById(R.id.editText);
        final EditText num2= (EditText)findViewById(R.id.editText2);
        final TextView resultado = (TextView)findViewById(R.id.textResultado);

        int numero1 = Integer.parseInt(num1.getText().toString());
        int numero2 = Integer.parseInt(num2.getText().toString());

        if(opcion.equals("suma"))
            resultado.setText(String.valueOf(numero1 + numero2));
        if(opcion.equals("resta"))
            resultado.setText(String.valueOf(numero1-numero2));

    }
}
