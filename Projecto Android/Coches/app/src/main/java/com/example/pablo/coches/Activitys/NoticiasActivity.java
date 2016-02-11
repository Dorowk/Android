package com.example.pablo.coches.Activitys;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.coches.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NoticiasActivity extends AppCompatActivity {
    Button buscar;
    TextView texto;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_noticias);
        context = this;
        this.setTitle("Noticias");


        buscar = (Button) findViewById(R.id.boton1);
        texto = (TextView) findViewById(R.id.texto1);

        final String url = "http://www.elpais.com/rss/feed.html?feedId=1022";

        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                new TareaHttpAsincrona().execute(url);
            }
        });

    }

    public class TareaHttpAsincrona extends AsyncTask<String, String, String> {

        private void checkInternetConenction() {

            ConnectivityManager conectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conectivityManager != null) {
                NetworkInfo[] info = conectivityManager.getAllNetworkInfo();

                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                            Toast.makeText(context, "Conexión a Internet exitosa", Toast.LENGTH_SHORT).show();
                    }
                }
            } else
                Toast.makeText(context, "Fallo en conexión a Internet", Toast.LENGTH_SHORT).show();
        }

        protected void onPreExecute() {
            checkInternetConenction();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection httpURLConnection = null;

            String salida = "";

            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    int i, j;
                    String linea = bufferedReader.readLine();

                    while (linea != null) {
                        if (linea.contains("<title>")) {
                            i = linea.indexOf("<title>") + 16;
                            j = linea.indexOf("</title>") - 3;
                            salida += linea.substring(i, j);
                            salida += "\n\n\n";
                        }
                        linea = bufferedReader.readLine();
                    }

                    bufferedReader.close();
                    inputStream.close();

                }
                publishProgress(salida);

            } catch (Exception e) {
                salida = "Excepción: " + e.getMessage();
            } finally {
                httpURLConnection.disconnect();
            }
            return salida;
        }

        protected void onProgressUpdate(String... pasos) {
            texto.append(pasos[0]);
        }

    }

}
