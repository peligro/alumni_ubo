package alumniubo.cl.alumni.alumni.vistas;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.Tarjeta_beneficio_1_detalle;
import alumniubo.cl.alumni.alumni.singleton.MySingleton;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.MenuDrawer;
import alumniubo.cl.alumni.alumni.utilidades.Utilidades;

public class BeneficiosDetalleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BD_Consultas bd_consultas;
    private  String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios_detalle);
        ejecutar();

    }
    private void ejecutar()
    {
        bd_consultas=new BD_Consultas(this);
        bd_consultas.verificaLogin();
        this.id=getIntent().getExtras().getString("id");
        TextView detalle_titulo=findViewById(R.id.detalle_titulo);
        ImageView detalle__imagen=findViewById(R.id.detalle_imagen);
        TextView detalle_descripcion=findViewById(R.id.detalle_descripcion);
        TextView detalle_fecha=findViewById(R.id.detalle_fecha);
        peticion(detalle_titulo, detalle__imagen, detalle_descripcion, detalle_fecha);

    }

    public void peticion(final TextView detalle_titulo, final ImageView detalle_imagen, final TextView detalle_descripcion, final TextView detalle_fecha)
    {
        StringRequest stringRequest=new StringRequest
                (
                        Request.Method.GET,
                        Constantes.BASE_URL + "beneficios/" + this.id,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                Tarjeta_beneficio_1_detalle tarjeta_beneficio_1_detalle=gson.fromJson(response, Tarjeta_beneficio_1_detalle.class);
                                showDrawer(tarjeta_beneficio_1_detalle.getTitulo());
                                Utilidades utilidades=new Utilidades();
                                detalle_titulo.setText(tarjeta_beneficio_1_detalle.getTitulo());
                                Picasso.with(BeneficiosDetalleActivity.this).load(Constantes.PATH+"beneficios/"+tarjeta_beneficio_1_detalle.getImagen()).into(detalle_imagen);
                                detalle_descripcion.setText(tarjeta_beneficio_1_detalle.getDescripcion());
                                detalle_fecha.setText(utilidades.formateaFecha(tarjeta_beneficio_1_detalle.getFecha())+" | Publicado por:  "+tarjeta_beneficio_1_detalle.getEmpresa());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        NetworkResponse networkResponse = error.networkResponse;
                        String jsonError="veamos primero1400";
                        if (networkResponse != null && networkResponse.data != null) {
                            jsonError = new String(networkResponse.data);
                            // Print Error!
                        }
                        Log.d( "error volley cesar: ", ""+jsonError);
                    }
                }
                ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Token", "6q3}HQ1yk8dG{BkyII9U");
                headers.putAll(headersSys);
                return headers;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    public void showDrawer(String sub_titulo)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(sub_titulo);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        final TextView drawer_titulo = (TextView)header.findViewById(R.id.drawer_titulo);
        final TextView drawer_sub_titulo=(TextView)header.findViewById(R.id.drawer_sub_titulo);
        Cursor cursor=bd_consultas.getCursor();
        cursor.moveToFirst();
        drawer_titulo.setText(cursor.getString(2));
        drawer_sub_titulo.setText(cursor.getString(4));
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        MenuDrawer menuDrawe=new MenuDrawer(this,this);
        menuDrawe.Menu(item);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
