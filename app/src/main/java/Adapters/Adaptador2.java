package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_android_http.ActivityAltas;
import com.example.bd_android_http.Alumnos;
import com.example.bd_android_http.EliminarAlumno;
import com.example.bd_android_http.R;

import java.util.List;

import viewHolders.ItemViewHolder;

public class Adaptador2 extends RecyclerView.Adapter<ItemViewHolder>{
    List<Alumnos> items;
    public Context context;
    public Adaptador2(Context context, List<Alumnos> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Alumnos data = this.items.get(position);
        holder.nombre.setText(data.getNombre());
        holder.primerap.setText(data.getPrimerap());
        holder.segundoap.setText(data.getSegundoap());
        holder.edad.setText(String.valueOf(data.getEdad()));
        holder.Semestre.setText(String.valueOf(data.getSemestre()));
        holder.numcontrol.setText(String.valueOf(data.getNocontrol()));
        holder.Carrera.setText(data.getCarrera());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

