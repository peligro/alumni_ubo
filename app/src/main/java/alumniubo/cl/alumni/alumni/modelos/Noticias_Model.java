package alumniubo.cl.alumni.alumni.modelos;

public class Noticias_Model {
    //tipo media 1 imagen 2 youtube
    private String id, categoria, titulo, detalle, fuente, fecha, tipo_media, media;

    public Noticias_Model(String id, String categoria, String titulo, String detalle, String fuente, String fecha, String tipo_media, String media) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.detalle = detalle;
        this.fuente = fuente;
        this.fecha = fecha;
        this.tipo_media = tipo_media;
        this.media = media;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_media() {
        return tipo_media;
    }

    public void setTipo_media(String tipo_media) {
        this.tipo_media = tipo_media;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
