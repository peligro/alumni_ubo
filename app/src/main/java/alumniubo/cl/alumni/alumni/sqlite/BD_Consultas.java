package alumniubo.cl.alumni.alumni.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alumniubo.cl.alumni.alumni.MainActivity;

public class BD_Consultas {
    private Context context;
    private String id;
    private BD_Helper bd_helper;
    private SQLiteDatabase db;
    private SQLiteDatabase db2;

    public BD_Consultas(Context context) {
        this.context = context;
        this.bd_helper = new BD_Helper(this.context);
    }

    public long creaUsuario(String ide, String nombre, String perfil_id, String perfil, String rut, String correo, String telefono, String nombre_empresa, String cargo_actual, String nombre_empleador, String cargo_empleador) {
        bd_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BD_Constantes.IDE, ide);
        contentValues.put(BD_Constantes.NOMBRE, nombre);
        contentValues.put(BD_Constantes.PERFIL_ID, perfil_id);
        contentValues.put(BD_Constantes.PERFIL, perfil);
        contentValues.put(BD_Constantes.RUT, rut);
        contentValues.put(BD_Constantes.CORREO, correo);
        contentValues.put(BD_Constantes.TELEFONO, telefono);
        contentValues.put(BD_Constantes.NOMBRE_EMPRESA, nombre_empresa);
        contentValues.put(BD_Constantes.CARGO_ACTUAL, cargo_actual);
        contentValues.put(BD_Constantes.NOMBRE_EMPLEADOR, nombre_empleador);
        contentValues.put(BD_Constantes.CARGO_EMPLEADOR, cargo_empleador);
        long id = db.insert(BD_Constantes.TABLA, null, contentValues);
        return id;
    }
    public void updateUsuario(String nombre, String correo, String telefono)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BD_Constantes.NOMBRE, nombre);
        contentValues.put(BD_Constantes.CORREO, correo);
        contentValues.put(BD_Constantes.TELEFONO, telefono);
        db.update(BD_Constantes.TABLA, contentValues,null, null);
    }
    public void verificaLogin() {
        Cursor cursor = this.getCursor();
        if (cursor.getCount() == 0) {
            this.context.startActivity(new Intent(this.context, MainActivity.class));
        }
    }

    public Cursor getCursor() {
        db = bd_helper.getReadableDatabase();
        String[] projection = {
                BD_Constantes.ID,
                BD_Constantes.IDE,
                BD_Constantes.NOMBRE,
                BD_Constantes.PERFIL_ID,
                BD_Constantes.PERFIL,
                BD_Constantes.RUT,
                BD_Constantes.CORREO,
                BD_Constantes.TELEFONO,
                BD_Constantes.NOMBRE_EMPRESA,
                BD_Constantes.CARGO_ACTUAL,
                BD_Constantes.NOMBRE_EMPLEADOR,
                BD_Constantes.CARGO_EMPLEADOR
        };
        Cursor cursor = db.query(
                BD_Constantes.TABLA,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        return cursor;
    }

    public void BorrarBD() {
        db = bd_helper.getReadableDatabase();
        String selection = BD_Constantes.ID + " LIKE ?";
        String[] selectionArgs = {this.id};
        db.delete(BD_Constantes.TABLA, null, null);
    }
}