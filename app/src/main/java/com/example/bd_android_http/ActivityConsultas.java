/*package com.example.bd_android_http;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import controlador.AnalizadorJSON;

public class ActivityConsultas extends Activity {

    ListView listviewAlumnos;

    ArrayList<String> datos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        new MostrarAlumnos().execute();


        listviewAlumnos = findViewById(R.id.listview_alumnos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listviewAlumnos.setAdapter(adapter);
    }


    class MostrarAlumnos extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String url = "http://10.0.2.2:8888/Semestre_ago_dic_2020/Pruebas_PHP/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
            String metodo = "POST";

            AnalizadorJSON analizadorJSON = new AnalizadorJSON();
            JSONObject jsonObject = analizadorJSON.consultaHTTP(url);


            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");


                String cadena = "";
                for (int i=0; i<jsonArray.length();i++){

                    cadena = jsonArray.getJSONObject(i).getString("nc") + " | " +
                            jsonArray.getJSONObject(i).getString("n") + "|" +
                            jsonArray.getJSONObject(i).getString("pa") + "|" +
                            jsonArray.getJSONObject(i).getString("sa") + "|" +
                            jsonArray.getJSONObject(i).getString("e") + "|" +
                            jsonArray.getJSONObject(i).getString("s") + "|" +
                            jsonArray.getJSONObject(i).getString("c");

                    datos.add(cadena);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}//class ActivityConsultas*/

package com.example.bd_android_http;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controlador.AnalizadorJSON;

public class ActivityConsultas extends Activity{

    ListView listviewAlumnos;
    EditText buscar;

    static ArrayList<String> datos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        buscar = findViewById(R.id.buscarC);
        listviewAlumnos = findViewById(R.id.listview_alumnos);


        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ActivityConsultas.datos.clear();
                ArrayAdapter<String> adap = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, ActivityConsultas.datos);
                listviewAlumnos.setAdapter(adap);
                buscar(buscar.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void buscar(String d){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/api_consultas_alumnos.php";
                String metodo = "POST";
                Map<String, String> mapaDatos = new HashMap<>();
                mapaDatos.put("n", d);
                AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, mapaDatos);

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                    String cadena = "";
                    for (int i = 0; i < jsonArray.length(); i++) {

                        cadena = jsonArray.getJSONObject(i).getString("nc") + " | " +
                                jsonArray.getJSONObject(i).getString("n") + "|" +
                                jsonArray.getJSONObject(i).getString("pa") + "|" +
                                jsonArray.getJSONObject(i).getString("sa") + "|" +
                                jsonArray.getJSONObject(i).getString("e") + "|" +
                                jsonArray.getJSONObject(i).getString("s") + "|" +
                                jsonArray.getJSONObject(i).getString("c");

                        ActivityConsultas.datos.add(cadena);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listviewAlumnos = findViewById(R.id.listview_alumnos);
//                        listviewAlumnos = new ListView();
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(getBaseContext(),
                                        android.R.layout.simple_list_item_1, datos);

                        listviewAlumnos.setAdapter(adapter);
                    }
                });

            }//run
        }).start();
    }

}//class ActivityConsultas