package com.example.bd_android_http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class EliminarAlumno extends AsyncTask<String, String, Boolean> {
    private Context context;
    public boolean exito = false;

    public EliminarAlumno(Context context){
        this.context = context;
    }


    @Override
    protected Boolean doInBackground(String... datos) {

        String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/api_bajas_alumnos.php";
        String metodo = "POST";

        Map<String, String> mapDatos = new HashMap<String, String>();
        mapDatos.put("nc", datos[0]);


        AnalizadorJSON aj = new AnalizadorJSON();
        JSONObject resultado = aj.peticionHTTP(url, metodo, mapDatos);

        try {
            exito = resultado.getBoolean("exito");
//            ActivityBajas.mensajeResultados = exito;
//            Intent i=new Intent(context,ActivityBajas.class);
//            startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        Log.i("RESULTADO", String.valueOf(s));
        exito = s;
    }
}
