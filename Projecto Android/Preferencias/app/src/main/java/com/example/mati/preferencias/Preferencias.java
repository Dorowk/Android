package com.example.mati.preferencias;
import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * Created by mati on 17/12/15.
 */

public class Preferencias extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.preferencias_layout);
    }
}