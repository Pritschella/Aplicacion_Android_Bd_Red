package com.example.bd_android_http;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class GuardarAlumno extends AsyncTask<String, String, Boolean> {
    private Context context;
    public boolean exito = false;

    public GuardarAlumno(Context context){
        this.context = context;
    }


    @Override
    protected Boolean doInBackground(String... datos) {

        String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/api_cambios_alumnos.php";
        String metodo = "POST";

        Map<String, String> mapDatos = new HashMap<String, String>();
        mapDatos.put("nc", String.valueOf(datos[0]));
        mapDatos.put("n", String.valueOf(datos[1]));
        mapDatos.put("pa", String.valueOf(datos[2]));
        mapDatos.put("sa", String.valueOf(datos[3]));
        mapDatos.put("e", String.valueOf(datos[4]));
        mapDatos.put("s", String.valueOf(datos[5]));
        mapDatos.put("c", String.valueOf(datos[6]));
//        Log.i("RESULTADO 1", String.valueOf(datos[0]));
//        Log.i("RESULTADO 2", String.valueOf(datos[1]));
//        Log.i("RESULTADO 3", String.valueOf(datos[2]));
//        Log.i("RESULTADO 4", String.valueOf(datos[3]));
//        Log.i("RESULTADO 5", String.valueOf(datos[4]));
//        Log.i("RESULTADO 6", String.valueOf(datos[5]));
//        Log.i("RESULTADO 7", String.valueOf(datos[6]));
        AnalizadorJSON aj = new AnalizadorJSON();
        JSONObject resultado = aj.peticionHTTP(url, metodo, mapDatos);

        try {
            exito = resultado.getBoolean("exito");

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
        Intent i = new Intent(context, ActivityCambios.class);
        context.startActivity(i);
    }
}
