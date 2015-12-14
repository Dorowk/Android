package com.example.mati.bicicleta;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by mati on 14/12/15.
 */
public class AcercaDe extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Hacemos visible la interfaz/layoutque se encuentra en acercade.xml
        setContentView(R.layout.layout_acercade);
    }
}

