package alumniubo.cl.alumni.alumni.utilidades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import alumniubo.cl.alumni.alumni.R;
import alumniubo.cl.alumni.alumni.constantes.Constantes;

public class Utilidades {
    public static void cargando(Context context, String titulo, String mensaje)
    {
        final ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle(titulo);
        progress.setMessage(mensaje);
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 1000);
    }
    public static void levantaPopUp(Context context, Activity activity, String texto_popup)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(context.getResources().getString(R.string.app_name));
        builder.setMessage(Utilidades.convertFromUTF8(texto_popup));
        builder.setPositiveButton("OK",null);
        builder.create();
        builder.show();
    }
    public static String ucFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
    public static String formateaFecha(String fechaParametro)
    {
        String[] exploded=fechaParametro.split("-");
        String mes;
        switch (exploded[1]){
            case "01":
                mes= Constantes.MESES[0];
                break;
            case "02":
                mes=Constantes.MESES[1];
                break;
            case "03":
                mes=Constantes.MESES[2];
                break;
            case "04":
                mes=Constantes.MESES[3];
                break;
            case "05":
                mes=Constantes.MESES[4];
                break;
            case "06":
                mes=Constantes.MESES[5];
                break;
            case "07":
                mes=Constantes.MESES[6];
                break;
            case "08":
                mes= Constantes.MESES[7];
                break;
            case "09":
                mes=Constantes.MESES[8];
                break;
            case "10":
                mes=Constantes.MESES[9];
                break;
            case "11":
                mes=Constantes.MESES[10];
                break;
            case "12":
                mes=Constantes.MESES[11];
                break;
            default:
                mes="";
                break;
        }
        String fecha=exploded[0]+" de "+ucFirst(mes)+" de "+exploded[2];
        return fecha;
    }
    public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    public  String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    //Metodo usado para obtener la hora actual del sistema
    //@return Retorna un <b>STRING</b> con la hora actual formato "hh:mm:ss"
    public String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }
    //Sumarle dias a una fecha determinada
    //@param fch La fecha para sumarle los dias
    //@param dias Numero de dias a agregar
    //@return La fecha agregando los dias
    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }


    //Restarle dias a una fecha determinada
    //@param fch La fecha
    //@param dias Dias a restar
    //@return La fecha restando los dias
    public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }
    public Date sumarRestarDiasFecha2(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
    public String sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        return format1.format(calendar.getTime()); // Devuelve el objeto Date con los nuevos días añadidos
    }

    //Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public  synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public  java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String convertirStringToTimestamp(int timestamp,String separador) {
        String fecha = new SimpleDateFormat("dd"+separador+"MM"+separador+"yyyy")
                .format(new Date(timestamp * 1000L));

        return fecha;
    }
    public static String convertirTimestampToDate(int timestamp) {
        String fecha = new SimpleDateFormat("HH:mm:ss")
                .format(new Date(timestamp * 1000L));

        return fecha;
    }
    public static String formateaRut(String rut) {
        String firstPart = rut.substring(0, rut.length() - 1);
        firstPart = firstPart.replaceAll("[\\.\\-]", "");
        String tmp = "";
        while (firstPart.length() > 3) {
            tmp = "." + firstPart.substring(firstPart.length() - 3) + tmp;
            firstPart = firstPart.substring(0, firstPart.length() - 3);
        }
        String secondPart = rut.substring(rut.length() - 1, rut.length());
        return firstPart + tmp + "-" + secondPart;
    }
}