package alumniubo.cl.alumni.alumni.vistas;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import alumniubo.cl.alumni.alumni.MainActivity;
import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.singleton.MySingleton;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.MenuDrawer;
import alumniubo.cl.alumni.alumni.utilidades.Utilidades;
import alumniubo.cl.alumni.alumni.validaciones.Validaciones;

public class MiCuentaActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private BD_Consultas bd_consultas;
    private EditText txt_nombre;
    private EditText txt_correo;
    private EditText txt_telefono;
    private EditText txt_pass;
    private EditText nombre_empresa;
    private EditText cargo_actual;
    private EditText nombre_empleador;
    private EditText cargo_empleador;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        ejecutar();
    }
    private void ejecutar()
    {
        bd_consultas=new BD_Consultas(this);
        bd_consultas.verificaLogin();
        txt_nombre=findViewById(R.id.txt_nombre);
        txt_nombre.setEnabled(false);
        txt_correo=findViewById(R.id.txt_correo);
        txt_telefono=findViewById(R.id.txt_telefono);
        txt_pass=findViewById(R.id.txt_pass);
        nombre_empresa=findViewById(R.id.nombre_empresa);
        cargo_actual=findViewById(R.id.cargo_actual);
        nombre_empleador=findViewById(R.id.nombre_empleador);
        cargo_empleador=findViewById(R.id.cargo_empleador);
        showDrawer();


    }
    public void validar(View view)
    {
        Integer bandera_nombre=0;
        Integer bandera_correo=0;
        Integer bandera_correo_valido=0;
        Integer bandera_telefono=0;
        Integer bandera_password=0;
        Integer modifica_password=0;
        String error="";

        Validaciones validaciones=new Validaciones();
        if(validaciones.campoRequerido(this.txt_nombre)==false)
        {
            error+="El campo Nombre está vacío\n";
            bandera_nombre=1;
        }else
        {
            bandera_nombre=0;
        }
        if(validaciones.campoRequerido(this.txt_correo)==false)
        {
            error+="El campo E-Mail está vacío\n";
            bandera_correo=1;
        }else
        {
            bandera_correo=0;
        }
        if(validaciones.validaEmail(this.txt_correo.getText().toString())==false)
        {
            error+="El E-Mail ingresado no es válido\n";
            bandera_correo=1;
        }else
        {
            bandera_correo=0;
        }
        if(validaciones.campoRequerido(this.txt_telefono)==false)
        {
            error+="El campo Teléfono está vacío\n";
            bandera_telefono=1;
        }else
        {
            bandera_telefono=0;
        }
        if(this.txt_pass.getText().toString().equals(""))
        {
            bandera_password=0;
            modifica_password=0;
        }else
            {
                if(validaciones.campoRequerido(this.txt_pass)==false)
                {
                    error+="El campo Contraseña está vacío\n";
                    bandera_password=1;
                }else
                {
                    bandera_password=0;
                }

                modifica_password=1;
            }
        final String str = Integer.toString(modifica_password);
        final String password;
        if(str.equals("0"))
        {
            password="vacio";
        }else
            {
            password=txt_pass.getText().toString();
            }
        final Utilidades utilidades=new Utilidades();
        if(bandera_nombre==1 || bandera_correo==1 || bandera_correo_valido==1 || bandera_telefono==1 || bandera_password==1)
        {

            error=utilidades.convertToUTF8(error);
            utilidades.levantaPopUp(this, this, error);
            txt_nombre.requestFocus();
        }else
        {
            utilidades.cargando(this,getResources().getString(R.string.app_name),getResources().getString(R.string.cargando));
            StringRequest stringRequest=new StringRequest
                    (
                            Request.Method.PUT,
                            Constantes.BASE_URL + "user",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    bd_consultas.updateUsuario(txt_nombre.getText().toString(), txt_correo.getText().toString(), txt_telefono.getText().toString());
                                    startActivity(new Intent(getApplicationContext(), MiCuentaActivity.class));
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            NetworkResponse networkResponse = error.networkResponse;
                            String jsonError="nada";
                            if (networkResponse != null && networkResponse.data != null) {
                                jsonError = new String(networkResponse.data);
                                // Print Error!
                            }
                            //Toast.makeText(getApplicationContext(),"Los datos ingresados no son correctos",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            //Log.d( "error volley cesar: ", ""+jsonError );
                        }
                    }
                    ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params=new HashMap<String,String>();
                    params.put("id",id);
                    params.put("nombre",txt_nombre.getText().toString());
                    params.put("correo",txt_correo.getText().toString());
                    params.put("telefono",txt_telefono.getText().toString());
                    params.put("password_modificar",String.valueOf(str));
                    params.put("password",password);
                    params.put("nombre_empresa",nombre_empresa.getText().toString());
                    params.put("cargo_actual",cargo_actual.getText().toString());
                    params.put("nombre_empleador",nombre_empleador.getText().toString());
                    params.put("cargo_empleador",cargo_empleador.getText().toString());
                    return params;
                }
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

    }
    public void showDrawer()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.titulo_micuenta));
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
        //formulario

        txt_nombre.setText(cursor.getString(2));
        txt_correo.setText(cursor.getString(6));
        txt_telefono.setText(cursor.getString(7));
        nombre_empresa.setText(cursor.getString(8));
        cargo_actual.setText(cursor.getString(9));
        nombre_empleador.setText(cursor.getString(10));
        cargo_empleador.setText(cursor.getString(11));
        id=cursor.getString(1);
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
