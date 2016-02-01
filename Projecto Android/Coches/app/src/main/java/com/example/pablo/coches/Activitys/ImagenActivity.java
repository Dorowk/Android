package com.example.pablo.coches.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pablo.coches.Objetos.Dibujo;
import com.example.pablo.coches.R;


/**
 * Created by Pablo on 30/12/2015.
 */
public class ImagenActivity extends Activity {
    private Dibujo dibujo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imagen);
        dibujo = (Dibujo) findViewById(R.id.area_dibujo);
        registerForContextMenu(dibujo);
    }

   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dibujo, menu);
    }
   public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ctx1:
                Toast.makeText(this,"Estamos a su disposicion",Toast.LENGTH_LONG).show();
                return true;
            case R.id.ctx2:
                Toast.makeText(this,"Hasta la proxima",Toast.LENGTH_LONG).show();
                return true;
            default:
                return true;
        }
   }
}

