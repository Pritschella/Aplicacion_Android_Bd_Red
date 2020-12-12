package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_android_http.ActivityBajas;
import com.example.bd_android_http.Alumnos;
import com.example.bd_android_http.EliminarAlumno;
import com.example.bd_android_http.R;

import java.util.List;

import viewHolders.ItemViewHolder;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    public List<Alumnos> items;
    public Context context;

    public Adaptador(Context context, List<Alumnos> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alumnos data = this.items.get(position);
        holder.nombre.setText(data.getNombre());
        holder.primerap.setText(data.getPrimerap());
        holder.segundoap.setText(data.getSegundoap());
        holder.edad.setText(String.valueOf(data.getEdad()));
        holder.semestre.setText(String.valueOf(data.getSemestre()));
        holder.nocontrol.setText(String.valueOf(data.getNocontrol()));
        holder.carrera.setText(data.getCarrera());
//        holder.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    public void setOnClickListener(){}
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btnEliminar;
        Context c;
        public TextView nombre, edad, primerap, segundoap, semestre, carrera, nocontrol;
        CardView tarjeta;

        public ViewHolder(@NonNull View v) {
            super(v);
            c = v.getContext();
            btnEliminar = (Button) v.findViewById(R.id.eliminar);
            nombre = v.findViewById(R.id.nombre);
            edad = v.findViewById(R.id.edad);
            primerap = v.findViewById(R.id.primerap);
            segundoap = v.findViewById(R.id.segundoap);
            semestre = v.findViewById(R.id.semestre);
            nocontrol = v.findViewById(R.id.nocontrol);
            carrera=v.findViewById(R.id.carrera);
            tarjeta = v.findViewById(R.id.tarjeta);
            btnEliminar.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.eliminar://se mandan los datos con put al activity
                    String nc = nocontrol.getText().toString();
                    EliminarAlumno eliminar = new EliminarAlumno(context);//esto se hace en el boton del activity
                    eliminar.execute(nc);
                    items.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(0, items.size());//este tambien
                    //notifyItemChanged(getAdapterPosition()); para actualizar
                    /*ab.Eliminar(v);
                    ab.EliminarAlumno(nc);*/
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }


}




