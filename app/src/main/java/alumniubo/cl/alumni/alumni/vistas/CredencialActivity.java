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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.utilidades.MenuDrawer;

public class CredencialActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private BD_Consultas bd_consultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credencial);
        ejecutar();
    }
    private void ejecutar()
    {
        bd_consultas=new BD_Consultas(this);
        bd_consultas.verificaLogin();
        showDrawer();
       ImageView credencial_logo=findViewById(R.id.credencial_logo);
       Picasso.with(this).load("http://www.alumniubo.cl/public/frontend/images/logo_alumni_limpio.png").into(credencial_logo);
       ImageView credencial_qr=findViewById(R.id.credencial_qr);
       Picasso.with(this).load("https://chart.googleapis.com/chart?chs=450x450&cht=qr&chl=https://www.cesarcancino.com&choe=UTF-8").into(credencial_qr);
    }
    public void showDrawer()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.titulo_credencial));
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
        TextView credencial_nombre=findViewById(R.id.credencial_nombre);
        TextView credencial_rut=findViewById(R.id.credencial_rut);
        TextView credencial_carrera=findViewById(R.id.credencial_carrera);
        Cursor cursor=bd_consultas.getCursor();
        cursor.moveToFirst();
        //credencial
        credencial_nombre.setText(cursor.getString(2));
        credencial_rut.setText(cursor.getString(5));
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
