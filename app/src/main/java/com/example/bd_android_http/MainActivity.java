package com.example.bd_android_http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText cajausuario, cajacontraseña;
    Button btnAcceder, btnRegistro;
    //Button btn;
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnAcceder=findViewById(R.id.btn_acceso);
        btnRegistro=findViewById(R.id.btn_registro);
        cajausuario=findViewById(R.id.caja_usuario);
        cajacontraseña=findViewById(R.id.caja_contraseña);
        btnAcceder.setOnClickListener(this);
    }

    public void abrirActivities(View v){

    }//metodo abrirActivities

    private void iniciarSesion() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://10.0.2.2/HTML_CSS_JavaScript/pruebas_php/Aplicacion_ABCC/API_REST_Android/validar_usuario.php";
                String metodo = "POST";
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("Correo", cajausuario.getText().toString());
                parametros.put("Pass", cajacontraseña.getText().toString());

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();
                JSONObject jsonObject = analizadorJSON.peticionHTTP2(url, metodo, parametros);
                Log.i("Ds:", String.valueOf(jsonObject));
                try {
if (jsonObject!=null) {
    JSONArray jsonArray = jsonObject.getJSONArray("usuarios");
    Intent i;
    i = new Intent(MainActivity.this, ActivityMenu.class);
    startActivity(i);
}else{
    //Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
}
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }//run
        }).start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_acceso:

                //VALIDAR USUARIO Y CONTRASEÑA EN BD de MySQL
                iniciarSesion();                                    break;            case R.id.btn_registro: break;
            default: break;
        }
    }
}//class