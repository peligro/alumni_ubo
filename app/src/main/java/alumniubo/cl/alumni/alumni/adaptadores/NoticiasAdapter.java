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


import com.squareup.picasso.Picasso;

import java.util.List;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.Noticias_Model;
import alumniubo.cl.alumni.alumni.vistas.NoticiasDetalleActivity;


public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.MyViewHolder>
{

    private List<Noticias_Model> lista;
    private Activity activity;

    public NoticiasAdapter(List<Noticias_Model> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_noticias,parent,false);
        return new NoticiasAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String ruta;
        holder.tarjeta_noticias_titulo.setText(lista.get(position).getTitulo());
        holder.tarjeta_noticias_categoria.setText(lista.get(position).getCategoria());
        if(lista.get(position).getTipo_media().equals("1"))
        {
            ruta=Constantes.PATH+"noticias/"+lista.get(position).getMedia();
        }else
        {
            ruta="https://i.ytimg.com/vi/"+lista.get(position).getMedia()+"/hqdefault.jpg";
        }
        Picasso.with(this.activity).load(ruta).into(holder.media);
        //se agrega un click a la imagen
        holder.media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity.getApplicationContext(), NoticiasDetalleActivity.class);
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
        private TextView tarjeta_noticias_categoria, tarjeta_noticias_titulo, detalle, fuente, fecha, tipo_media;
        private ImageView media;
        public MyViewHolder(View view)
        {
            super(view);
            media=(ImageView)view.findViewById(R.id.tarjeta_noticias_imagen);
            tarjeta_noticias_categoria=(TextView)view.findViewById(R.id.tarjeta_noticias_categoria);
            tarjeta_noticias_titulo=(TextView)view.findViewById(R.id.tarjeta_noticias_titulo);
        }
    }
}
