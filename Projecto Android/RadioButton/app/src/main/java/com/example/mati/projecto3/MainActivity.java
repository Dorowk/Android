package com.example.mati.projecto3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxMemorias;
    CheckBox chkBoxHarry;
    CheckBox chkBoxCancion;
    Button btnHobby;
    TextView txtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialUISetup();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void initialUISetup()
    {
        chkBoxCancion = (CheckBox) findViewById(R.id.chkBoxCancion);
        chkBoxHarry = (CheckBox) findViewById(R.id.chkBoxHarry);
        chkBoxMemorias = (CheckBox) findViewById(R.id.chkBoxMemorias);
        btnHobby = (Button)findViewById(R.id.btnHobby);
        txtHobby = (TextView)findViewById(R.id.txtHobby);

        btnHobby.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getHobbyClick(v);
            }
        });
    }
    public void getHobbyClick(View v)
    {  String strMessage = "";

        if(chkBoxMemorias.isChecked())
        {
            strMessage+="Memorias de Idhun \n ";
        }
        if(chkBoxHarry.isChecked())
        {
            strMessage+="Harry Potter \n ";
        }
        if(chkBoxCancion.isChecked())
        {
            strMessage+="Cancion de Hielo y fuego \n";
        }
        showTextNotification(strMessage);
    }

    public void showTextNotification(String msgToDisplay)
    {
        txtHobby.setText(msgToDisplay);
        //Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();

    }
}

