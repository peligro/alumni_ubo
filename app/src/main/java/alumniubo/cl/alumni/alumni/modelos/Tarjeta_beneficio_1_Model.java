package alumniubo.cl.alumni.alumni.modelos;

public class Tarjeta_beneficio_1_Model {
    private String tarjeta_beneficio_1_imagen, tarjeta_beneficio_1_titulo,tarjeta_beneficio_1_sub_titulo,id;

    public Tarjeta_beneficio_1_Model(String tarjeta_beneficio_1_imagen, String tarjeta_beneficio_1_titulo, String tarjeta_beneficio_1_sub_titulo, String id) {
        this.tarjeta_beneficio_1_imagen = tarjeta_beneficio_1_imagen;
        this.tarjeta_beneficio_1_titulo = tarjeta_beneficio_1_titulo;
        this.tarjeta_beneficio_1_sub_titulo = tarjeta_beneficio_1_sub_titulo;
        this.id = id;
    }

    public String getTarjeta_beneficio_1_imagen() {
        return tarjeta_beneficio_1_imagen;
    }

    public void setTarjeta_beneficio_1_imagen(String tarjeta_beneficio_1_imagen) {
        this.tarjeta_beneficio_1_imagen = tarjeta_beneficio_1_imagen;
    }

    public String getTarjeta_beneficio_1_titulo() {
        return tarjeta_beneficio_1_titulo;
    }

    public void setTarjeta_beneficio_1_titulo(String tarjeta_beneficio_1_titulo) {
        this.tarjeta_beneficio_1_titulo = tarjeta_beneficio_1_titulo;
    }

    public String getTarjeta_beneficio_1_sub_titulo() {
        return tarjeta_beneficio_1_sub_titulo;
    }

    public void setTarjeta_beneficio_1_sub_titulo(String tarjeta_beneficio_1_sub_titulo) {
        this.tarjeta_beneficio_1_sub_titulo = tarjeta_beneficio_1_sub_titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
