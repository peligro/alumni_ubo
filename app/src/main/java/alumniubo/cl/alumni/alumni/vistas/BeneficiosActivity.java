package alumniubo.cl.alumni.alumni.vistas;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.adaptadores.Tarjeta_beneficio_1_adaptador;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.Tarjeta_beneficio_1_Model;
import alumniubo.cl.alumni.alumni.singleton.MySingleton;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.MenuDrawer;

public class BeneficiosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BD_Consultas bd_consultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);
        ejecutar();
    }
    private void ejecutar()
    {
        bd_consultas=new BD_Consultas(this);
        bd_consultas.verificaLogin();
        showDrawer();
        showRecyclerView();

    }
    public void showRecyclerView()
    {
        final ArrayList<Tarjeta_beneficio_1_Model> arrayList=new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tarjeta_beneficio_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest
                (
                        Request.Method.GET,
                        Constantes.BASE_URL + "beneficios",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                int count=0;
                                while(count<response.length())
                                {
                                    try{
                                        JSONObject jsonObject=response.getJSONObject(count);
                                        Tarjeta_beneficio_1_Model tarjeta_beneficio_1_model=new Tarjeta_beneficio_1_Model
                                                (
                                                        jsonObject.getString("logo"),
                                                        jsonObject.getString("titulo"),
                                                        jsonObject.getString("sub_titulo"),
                                                        jsonObject.getString("id")
                                                );
                                        arrayList.add(tarjeta_beneficio_1_model);
                                        Tarjeta_beneficio_1_adaptador tarjeta_beneficio_1_adaptador=new Tarjeta_beneficio_1_adaptador(arrayList,BeneficiosActivity.this);
                                        recyclerView.setAdapter(tarjeta_beneficio_1_adaptador);
                                        count++;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
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
                        //Log.d( "error volley cesar: ", ""+jsonError);
                    }
                }
                ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Token", "6q3}HQ1yk8dG{BkyII9U");
                headers.putAll(headersSys);
                return headers;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }
    public void showDrawer()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.titulo_beneficios));
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
