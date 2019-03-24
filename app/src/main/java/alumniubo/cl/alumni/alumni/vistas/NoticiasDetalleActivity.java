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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.Noticias_Model;
import alumniubo.cl.alumni.alumni.modelos.Tarjeta_beneficio_1_detalle;
import alumniubo.cl.alumni.alumni.singleton.MySingleton;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.MenuDrawer;
import alumniubo.cl.alumni.alumni.utilidades.Utilidades;

public class NoticiasDetalleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BD_Consultas bd_consultas;
    private  String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_detalle);
        ejecutar();
    }
    private void ejecutar()
    {
        bd_consultas=new BD_Consultas(this);
        bd_consultas.verificaLogin();
        this.id=getIntent().getExtras().getString("id");
        //showDrawer();
        consulta();
    }
    public void consulta()
    {
        StringRequest stringRequest=new StringRequest
                (
                        Request.Method.GET,
                        Constantes.BASE_URL + "noticias/" + this.id,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                Noticias_Model noticias_model=gson.fromJson(response, Noticias_Model.class);
                                showDrawer(noticias_model.getTitulo());
                                Utilidades utilidades=new Utilidades();
                                String media="";

                                if(noticias_model.getTipo_media().equals("1"))
                                {
                                    media="<img src='"+Constantes.PATH+"noticias/"+noticias_model.getMedia()+"' width='400' height='400' />";
                                }else
                                {
                                    media="<iframe width='400' height='215' src='https://www.youtube.com/embed/"+noticias_model.getMedia()+"' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
                                }

                                String contenido="<h1>"+noticias_model.getTitulo()+"</h1><hr /> "+media+" <hr /><p  style='text-align: justify;'>"
                                                    +noticias_model.getDetalle()+
                                                    "</p><hr />- Categoría : "+noticias_model.getCategoria()+"<br />- Fuente : "+
                                                    noticias_model.getFuente()+"<br />- Fecha : "+utilidades.formateaFecha(noticias_model.getFecha())+"<br />";


                                WebView webView =(WebView)findViewById(R.id.navegador);
                                WebSettings webSettings=webView.getSettings();
                                webSettings.setJavaScriptEnabled(true);
                                webView.loadDataWithBaseURL("",contenido,"text/html","UTF-8","");
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



        //drawer
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
