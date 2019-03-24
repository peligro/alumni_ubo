package alumniubo.cl.alumni.alumni.sqlite;

public  class BD_Constantes {

    private BD_Constantes() {}


    public static final String TABLA = "usuarios";
    public static final String ID = "id";
    public static final String IDE = "ide";
    public static final String NOMBRE = "nombre";
    public static final String PERFIL_ID="perfil_id";
    public static final String PERFIL="perfil";
    public static final String RUT="rut";
    public static final String CORREO="correo";
    public static final String TELEFONO="telefono";
    public static final String NOMBRE_EMPRESA="nombre_empresa";
    public static final String CARGO_ACTUAL="cargo_actual";
    public static final String NOMBRE_EMPLEADOR="nombre_empleador";
    public static final String CARGO_EMPLEADOR="cargo_empleador";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLA + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    IDE + " TEXT," +
                    NOMBRE + " TEXT," +
                    PERFIL_ID + " TEXT," +
                    PERFIL + " TEXT ," +
                    RUT + " TEXT," +
                    CORREO + " TEXT," +
                    TELEFONO + " TEXT," +
                    NOMBRE_EMPRESA + " TEXT," +
                    CARGO_ACTUAL + " TEXT," +
                    NOMBRE_EMPLEADOR + " TEXT," +
                    CARGO_EMPLEADOR + " TEXT" +
                    ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLA;

}
