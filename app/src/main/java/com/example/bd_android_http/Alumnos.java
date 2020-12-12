package com.example.bd_android_http;

import org.json.JSONException;
import org.json.JSONObject;

public class Alumnos {
private int edad, semestre, nocontrol;
private String nombre, primerap, segundoap, carrera;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getNocontrol() {
        return nocontrol;
    }

    public void setNocontrol(int nocontrol) {
        this.nocontrol = nocontrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerap() {
        return primerap;
    }

    public void setPrimerap(String primerap) {
        this.primerap = primerap;
    }

    public String getSegundoap() {
        return segundoap;
    }

    public void setSegundoap(String segundoap) {
        this.segundoap = segundoap;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Alumnos createFromJSONObject(JSONObject data){
        try{
            this.nombre = data.getString("nombre");
            this.nocontrol = data.getInt("num_control");
            this.edad = data.getInt("edad");
            this.primerap = data.getString("primer_ap");
            this.segundoap = data.getString("segundo_ap");
            this.carrera = data.getString("carrera");
            this.semestre = data.getInt("semestre");
        }catch (JSONException e){
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public String toString() {
        return "Alumnos{" +
                "edad=" + edad +
                ", semestre=" + semestre +
                ", nocontrol=" + nocontrol +
                ", nombre='" + nombre + '\'' +
                ", primerap='" + primerap + '\'' +
                ", segundoap='" + segundoap + '\'' +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
