package alumniubo.cl.alumni.alumni;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import alumniubo.cl.alumni.alumni.constantes.Constantes;
import alumniubo.cl.alumni.alumni.modelos.UsuarioEntity;
import alumniubo.cl.alumni.alumni.singleton.MySingleton;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.Utilidades;
import alumniubo.cl.alumni.alumni.validaciones.Validaciones;
import alumniubo.cl.alumni.alumni.vistas.BeneficiosActivity;

public class MainActivity extends AppCompatActivity {
    private BD_Consultas bd_consultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ejecutar(View view)
    {
        bd_consultas=new BD_Consultas(this);
        final EditText rut=(EditText)findViewById(R.id.rut);
        final EditText password=(EditText)findViewById(R.id.password);
        Validaciones validaciones=new Validaciones();
        Integer bandera_rut=0;
        Integer bandera_rut_valido=0;
        Integer bandera_password=0;
        String error="";
        if(validaciones.campoRequerido(rut)==false)
        {
            error+=getResources().getString(R.string.validacion_1);
            bandera_rut=1;
        }else
        {
            bandera_rut=0;
        }
        if(validaciones.validarRut(rut.getText().toString())==false)
        {
            error+=getResources().getString(R.string.validacion_2);
            bandera_rut_valido=1;
        }else
        {
            bandera_rut_valido=0;
        }
        if(validaciones.campoRequerido(password)==false)
        {
            error+=getResources().getString(R.string.validacion_3);
            bandera_password=1;
        }else
        {
            bandera_password=0;
        }
        final Utilidades utilidades=new Utilidades();
        if(bandera_rut==1 || bandera_rut_valido==1 || bandera_password==1)
        {

            error=utilidades.convertToUTF8(error);
            utilidades.levantaPopUp(this, this, error);
            rut.requestFocus();
        }else
         {
             utilidades.cargando(this,getResources().getString(R.string.app_name),getResources().getString(R.string.cargando));

             StringRequest stringRequest=new StringRequest
                     (
                             Request.Method.POST,
                             Constantes.BASE_URL + "login",
                             new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {
                                    //
                                     Gson gson = new Gson();
                                     UsuarioEntity usuarioEntity=gson.fromJson(response,UsuarioEntity.class);

                                    bd_consultas.BorrarBD();

                                     bd_consultas.creaUsuario
                                             (
                                                     usuarioEntity.getId(),
                                                     usuarioEntity.getNombre(),
                                                     usuarioEntity.getPerfil_id(),
                                                     usuarioEntity.getPerfil(),
                                                     usuarioEntity.getRut(),
                                                     usuarioEntity.getCorreo(),
                                                     usuarioEntity.getTelefono(),
                                                     usuarioEntity.getNombre_empresa(),
                                                     usuarioEntity.getCargo_actual(),
                                                     usuarioEntity.getNombre_empleador(),
                                                     usuarioEntity.getCargo_empleador()
                                             );
                                     startActivity(new Intent(getApplicationContext(),BeneficiosActivity.class));
                                     finish();

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
                             Toast.makeText(getApplicationContext(),"Los datos ingresados no son correctos",Toast.LENGTH_LONG).show();
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                             //Log.d( "error volley cesar: ", ""+jsonError+" \nrut="+rut.getText().toString()+"\npassword="+password.getText().toString()+"\nruta="+Constantes.BASE_URL + "login");
                         }
                     }
                     ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map<String,String> params=new HashMap<String,String>();
                     params.put("rut",rut.getText().toString());
                     params.put("password",password.getText().toString());
                     return params;
                 }
                 @Override
                 public Map<String, String> getHeaders() throws AuthFailureError {
                     Map<String, String> headersSys = super.getHeaders();
                     Map<String, String> headers = new HashMap<String, String>();
                     headers.put("token", "dadversion2");
                     headers.putAll(headersSys);
                     return headers;
                 }
             };
             MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
         }
    }
    public void no_puedes_entrar(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alumniubo.cl/login/olvido"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
