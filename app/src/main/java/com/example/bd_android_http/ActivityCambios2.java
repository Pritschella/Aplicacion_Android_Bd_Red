package com.example.bd_android_http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityCambios2 extends AppCompatActivity implements View.OnClickListener {
    TextView numcontrol, nombre, primerap, segundoap, edad, carrera;
    Spinner semestre;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios2);

        Bundle extra = getIntent().getExtras();
        String nc = extra.getString("nc");
        String n = extra.getString("n");
        String pa = extra.getString("pa");
        String sa = extra.getString("sa");
        String e = extra.getString("e");
        String s = extra.getString("s");
        String c = extra.getString("c");

        numcontrol= findViewById(R.id.NControl);
        nombre = findViewById(R.id.EditNombre);
        primerap = findViewById(R.id.EditPrimerAp);
        segundoap = findViewById(R.id.EditSegundoAp);
        edad=findViewById(R.id.EditEdad);
        semestre= findViewById(R.id.spnSemestre);
        carrera = findViewById(R.id.editCarrera);
        btnGuardar=findViewById(R.id.btnGuardar);


        numcontrol.setText(nc);
        nombre.setText(n);
        primerap.setText(pa);
        segundoap.setText(sa);
        edad.setText(e);
        semestre.setSelection(Integer.parseInt(s));
        carrera.setText(c);

        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGuardar:
                String nc=numcontrol.getText().toString();
                String n =nombre.getText().toString();
                String pa = primerap.getText().toString();
                String sa = segundoap.getText().toString();
                String e = edad.getText().toString();
                String s = semestre.getSelectedItem().toString();
                String c = carrera.getText().toString();
                GuardarAlumno guardar = new GuardarAlumno(v.getContext());
                guardar.execute(nc, n, pa, sa, e, s, c);
                break;
            default: break;
        }

    }
}
