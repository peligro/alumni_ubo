package alumniubo.cl.alumni.alumni.adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.Tarjeta_beneficio_1_Model;
import alumniubo.cl.alumni.alumni.vistas.BeneficiosDetalleActivity;

public class Tarjeta_beneficio_1_adaptador extends RecyclerView.Adapter<Tarjeta_beneficio_1_adaptador.MyViewHolder>
{
    private List<Tarjeta_beneficio_1_Model> lista;
    private Activity activity;

    public Tarjeta_beneficio_1_adaptador(List<Tarjeta_beneficio_1_Model> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_beneficios_1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, final int position)
    {
        holder.tarjeta_beneficio_1_titulo.setText(lista.get(position).getTarjeta_beneficio_1_titulo());
        holder.tarjeta_beneficio_1_sub_titulo.setText(lista.get(position).getTarjeta_beneficio_1_sub_titulo());
        Picasso.with(this.activity).load(Constantes.PATH+"empresas/"+lista.get(position).getTarjeta_beneficio_1_imagen()).into(holder.tarjeta_beneficio_1_imagen);
        //se agrega un click a la imagen
        holder.tarjeta_beneficio_1_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity.getApplicationContext(),"se hizo click en la foto: "+lista.get(position).getTarjeta_beneficio_1_titulo(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(activity.getApplicationContext(),BeneficiosDetalleActivity.class);
                intent.putExtra("id",lista.get(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tarjeta_beneficio_1_titulo, tarjeta_beneficio_1_sub_titulo;
        private ImageView tarjeta_beneficio_1_imagen;
        public MyViewHolder(View view)
        {
            super(view);
            tarjeta_beneficio_1_imagen=(ImageView)view.findViewById(R.id.tarjeta_beneficio_1_imagen);
            tarjeta_beneficio_1_titulo=(TextView)view.findViewById(R.id.tarjeta_beneficio_1_titulo);
            tarjeta_beneficio_1_sub_titulo=(TextView)view.findViewById(R.id.tarjeta_beneficio_1_sub_titulo);
        }
    }
}
