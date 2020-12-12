package com.example.bd_android_http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class ActivityAltas extends Activity {

    static String mensajeResultados = "";
    EditText cajaNumControl, cajaNombre, cajaPrimerap, cajaSegundoap, cajaEdad, cajaCarrera;
    Spinner semestre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_numcontrol_altas);
        cajaNombre = findViewById(R.id.caja_nombre_altas);
        cajaPrimerap = findViewById(R.id.caja_primerap_altas);
        cajaSegundoap = findViewById(R.id.caja_segundoap_altas);
        cajaEdad = findViewById(R.id.caja_edad_altas);
        semestre=findViewById(R.id.spn_semestre_altas);
        cajaCarrera = findViewById(R.id.caja_carrera_altas);

    }

    public void agregarRegistro(View v){

        String nc = cajaNumControl.getText().toString();
        String n = cajaNombre.getText().toString();
        String pa = cajaPrimerap.getText().toString();
        String sa = cajaSegundoap.getText().toString();
        String e = cajaEdad.getText().toString();
        String s = semestre.getSelectedItem().toString();
        String c = cajaCarrera.getText().toString();

        //Verificar que la comunicacion con WIFI funcione
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if(ni != null && ni.isConnected()){

           new AgregarAlumno().execute(nc, n, pa, sa, e, s, c);

            try {
                Thread.currentThread().join(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }


            Toast.makeText(getBaseContext(),
                    (mensajeResultados.equals("exito"))?"SUPER EXITO":"ME CAMBIO DE CARRERA",
                    Toast.LENGTH_LONG).show();

        }else
            Log.e("MSJ-->", "Error en WIFI");



        Toast.makeText(getBaseContext(), "Magia", Toast.LENGTH_LONG).show();

    }//metodo AgregarRegistro

    //clase interna
    class AgregarAlumno extends AsyncTask<String, String, String> {

        @Override                       //VARARGS
        protected String doInBackground(String...datos) {

            String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/api_altas_alumos.php";
            String metodo = "POST";

            Map<String, String> mapaDatos = new HashMap<>();
            mapaDatos.put("nc", datos[0]);
            mapaDatos.put("n", datos[1]);
            mapaDatos.put("pa", datos[2]);
            mapaDatos.put("sa", datos[3]);
            mapaDatos.put("e", datos[4]);
            mapaDatos.put("s", datos[5]);
            mapaDatos.put("c", datos[6]);

            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            JSONObject resultado = analizadorJSON.peticionHTTP(url, metodo, mapaDatos);

            String exito = null;
            try {
                exito = resultado.getString("exito");
                ActivityAltas.mensajeResultados = exito;

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), exito?"EXITO":"ME CAMBIO DE CARRERA", Toast.LENGTH_LONG).show();
                    }
                });*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(exito);
        }
    }

}//class ActivityAltas












