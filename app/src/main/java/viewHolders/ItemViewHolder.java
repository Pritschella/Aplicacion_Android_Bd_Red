package viewHolders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_android_http.ActivityCambios2;
import com.example.bd_android_http.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView numcontrol;
    public TextView nombre;
    public TextView primerap;
    public TextView segundoap;
    public TextView edad;
    public TextView Semestre;
    public TextView Carrera;
    public Button btnEditar;
    CardView tarjeta;
    private View.OnClickListener listener;
    public Context c;
    //private Adaptador onClickListener;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        c = itemView.getContext();
        nombre = (TextView) itemView.findViewById(R.id.nombreC);
        numcontrol = (TextView) itemView.findViewById(R.id.nocontrolC);
        primerap = (TextView) itemView.findViewById(R.id.primerapC);
        segundoap = (TextView) itemView.findViewById(R.id.segundoapC);
        edad = (TextView) itemView.findViewById(R.id.edadC);
        Semestre = (TextView) itemView.findViewById(R.id.semestreC);
        Carrera = (TextView) itemView.findViewById(R.id.carreraC);
        btnEditar = (Button) itemView.findViewById(R.id.editar);
        tarjeta = itemView.findViewById(R.id.tarjeta2);
        btnEditar.setOnClickListener(this);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.editar://se mandan los datos con put al activity
                String nc = numcontrol.getText().toString();
                String n = nombre.getText().toString();
                String pa = primerap.getText().toString();
                String sa = segundoap.getText().toString();
                String e = edad.getText().toString();
                String s = Semestre.getText().toString();
                String ca = Carrera.getText().toString();

                i = new Intent(c, ActivityCambios2.class);
                i.putExtra("nc", nc);
                i.putExtra("n", n);
                i.putExtra("pa", pa);
                i.putExtra("sa", sa);
                i.putExtra("e", e);
                i.putExtra("s", s);
                i.putExtra("c", ca);

                c.startActivity(i);

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

}
