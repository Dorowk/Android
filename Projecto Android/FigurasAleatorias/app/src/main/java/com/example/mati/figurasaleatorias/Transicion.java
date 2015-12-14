package com.example.mati.figurasaleatorias;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by mati on 10/12/15.
 */
public class Transicion extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicion);
        ImageView imagen =(ImageView) findViewById(R.id.imageTransicion);
        TransitionDrawable transicion = (TransitionDrawable) getResources().getDrawable(R.drawable.transicion);
        imagen.setImageDrawable(transicion);
        transicion.startTransition(2000);
    }
}
