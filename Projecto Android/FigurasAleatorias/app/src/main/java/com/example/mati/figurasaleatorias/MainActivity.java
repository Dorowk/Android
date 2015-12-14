package com.example.mati.figurasaleatorias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchDrawShapes1(View clickedButton) {
        Intent activityIntent =
                new Intent(this, DrawShapes1.class);
        startActivity(activityIntent);
    }


    public void launchDrawShapes2(View clickedButton) {
        Intent activityIntent =
                new Intent(this, DrawShapes2.class);
        startActivity(activityIntent);
    }

    public void transicion (View clickedButton) {
        Intent activityIntent =
                new Intent(this, Transicion.class);
        startActivity(activityIntent);
    }
    public void animacion (View clickedButton) {
        Intent activityIntent =
                new Intent(this, Animacion.class);
        startActivity(activityIntent);
    }


}