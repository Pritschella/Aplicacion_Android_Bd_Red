package com.example.bd_android_http;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapters.Adaptador;
import Adapters.Adaptador2;
import controlador.AnalizadorJSON;

public class ActivityCambios extends Activity {
    RecyclerView lista;
    public static boolean mensajeResultados;
    public List<Alumnos> datos = new ArrayList<>();
    Adaptador2 adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        lista = findViewById(R.id.lista2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                JSONObject jsonObject = analizadorJSON.consultaHTTP(url);

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Alumnos a = new Alumnos();
                        a.setNocontrol(Integer.parseInt(jsonArray.getJSONObject(i).getString("nc")));
                        a.setNombre(jsonArray.getJSONObject(i).getString("n"));
                        a.setPrimerap(jsonArray.getJSONObject(i).getString("pa"));
                        a.setSegundoap(jsonArray.getJSONObject(i).getString("sa"));
                        a.setEdad(Integer.parseInt(jsonArray.getJSONObject(i).getString("e")));
                        a.setSemestre(Integer.parseInt(jsonArray.getJSONObject(i).getString("s")));
                        a.setCarrera(jsonArray.getJSONObject(i).getString("c"));

                        datos.add(a);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Adaptador2 adapter = new Adaptador2(getApplicationContext(), datos);
                        lista.setAdapter(adapter);
                        lista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });

            }//run
        }).start();
    }
}
