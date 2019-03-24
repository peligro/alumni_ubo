package alumniubo.cl.alumni.alumni.modelos;

public class UsuarioEntity
{
    private String id;
    private String perfil;
    private String perfil_id;
    private String rut;
    private String nombre;
    private String correo;
    private String telefono;
    private String nombre_empresa;
    private String cargo_actual;
    private String nombre_empleador;
    private String cargo_empleador;

    public UsuarioEntity(String id, String perfil, String perfil_id, String rut, String nombre, String correo, String telefono, String nombre_empresa, String cargo_actual, String nombre_empleador, String cargo_empleador) {
        this.id = id;
        this.perfil = perfil;
        this.perfil_id = perfil_id;
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.nombre_empresa = nombre_empresa;
        this.cargo_actual = cargo_actual;
        this.nombre_empleador = nombre_empleador;
        this.cargo_empleador = cargo_empleador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(String perfil_id) {
        this.perfil_id = perfil_id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getCargo_actual() {
        return cargo_actual;
    }

    public void setCargo_actual(String cargo_actual) {
        this.cargo_actual = cargo_actual;
    }

    public String getNombre_empleador() {
        return nombre_empleador;
    }

    public void setNombre_empleador(String nombre_empleador) {
        this.nombre_empleador = nombre_empleador;
    }

    public String getCargo_empleador() {
        return cargo_empleador;
    }

    public void setCargo_empleador(String cargo_empleador) {
        this.cargo_empleador = cargo_empleador;
    }
}
