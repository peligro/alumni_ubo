package alumniubo.cl.alumni.alumni.modelos;

public class Tarjeta_beneficio_1_detalle {
    private String id,id_empresa, titulo, sub_titulo, descripcion, fecha, estado, logo, imagen, empresa;

    public Tarjeta_beneficio_1_detalle(String id, String id_empresa, String titulo, String sub_titulo, String descripcion, String fecha, String estado, String logo, String imagen, String empresa) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.titulo = titulo;
        this.sub_titulo = sub_titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.logo = logo;
        this.imagen = imagen;
        this.empresa = empresa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSub_titulo() {
        return sub_titulo;
    }

    public void setSub_titulo(String sub_titulo) {
        this.sub_titulo = sub_titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
