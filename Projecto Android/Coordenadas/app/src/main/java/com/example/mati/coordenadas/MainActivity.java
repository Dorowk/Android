package com.example.mati.coordenadas;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText latitudd, longitudd;
    Context context;
    Button ir;
    TextView res;

    String direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudd = (EditText)findViewById(R.id.lat);
        longitudd = (EditText)findViewById(R.id.longi);
        res = (TextView)findViewById(R.id.tResult);

        ir = (Button)findViewById(R.id.btir);

        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitud, latitud;
                longitud = longitudd.getText().toString();
                latitud = latitudd.getText().toString();

                final String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitud + "," + longitud + "&sensor=false";
                new TareaHttpAsincrona().execute(url);
            }
        });
    }

    public class TareaHttpAsincrona extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;
            Integer res = 0;
            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestMethod("GET");
                int statusCode = httpURLConnection.getResponseCode();

                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    String result = InputStreamString(inputStream);
                    parsearres(result);
                    res = 1;
                }
                else
                    res = 0;
            }
            catch (Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(result == 1)
                res.setText(direccion);
            else
                Toast.makeText(getApplicationContext(), "Error en obtener los datos!", Toast.LENGTH_SHORT).show();
        }
    }
    private String InputStreamString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String aux1;
        String aux2 = "";

        while((aux1 = bufferedReader.readLine()) != null)
            aux2 += aux1;

        inputStream.close();

        return aux2;
    }

    private void parsearres(String res) {
        try {
            JSONObject reis = new JSONObject(res);
            JSONArray lugares = reis.optJSONArray("results");
            direccion = lugares.getJSONObject(0).getString("formatted_address");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}