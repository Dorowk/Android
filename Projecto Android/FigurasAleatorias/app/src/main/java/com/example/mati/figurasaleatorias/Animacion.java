package com.example.mati.figurasaleatorias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by mati on 10/12/15.
 */
public class Animacion extends Activity {
    AnimationDrawable animacion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion);
        animacion = (AnimationDrawable) getResources().getDrawable(R.drawable.animacion);

        ImageView imagen = (ImageView) findViewById(R.id.imageAnimacion);

        imagen.setBackgroundColor(Color.WHITE);
        imagen.setImageDrawable(animacion);
    }

    public boolean onTouchEvent(MotionEvent evento) {
        if (evento.getAction() == MotionEvent.ACTION_DOWN) {
            animacion.start();
            return true;
        }
        return super.onTouchEvent(evento);
    }
}
