package alumniubo.cl.alumni.alumni.utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import alumniubo.cl.alumni.alumni.MainActivity;
import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.sqlite.BD_Consultas;
import alumniubo.cl.alumni.alumni.vistas.BeneficiosActivity;
import alumniubo.cl.alumni.alumni.vistas.CredencialActivity;
import alumniubo.cl.alumni.alumni.vistas.MiCuentaActivity;
import alumniubo.cl.alumni.alumni.vistas.NoticiasActivity;

public class MenuDrawer {

    private Context context;
    private Activity activity;

    public MenuDrawer(Context context,Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    public void Menu(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.beneficios:
                this.context.startActivity(new Intent(this.context,BeneficiosActivity.class));
            break;
            case R.id.noticias:
                this.context.startActivity(new Intent(this.context, NoticiasActivity.class));
                break;
            case R.id.mi_cuenta:
                this.context.startActivity(new Intent(this.context,MiCuentaActivity.class));
            break;
            case R.id.credencial:
                this.context.startActivity(new Intent(this.context,CredencialActivity.class));
                break;
            case R.id.cerrar_sesion:
                BD_Consultas bd_consultas=new BD_Consultas(context);
                bd_consultas.BorrarBD();
                context.startActivity(new Intent(context,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));

                activity.finish();
            break;

        }

    }
}
