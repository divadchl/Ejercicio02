package mx.com.serviciosinformaticosintegrales.ejercicio1.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.content.Context;

public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String BASEDATOS_NOMBRE = "BDEjercicio";
    private final static int BASEDATOS_VERSION = 1;
    public static final String NOMBRE_TABLA = "usarios";
    public static final String COLUMNA_ID = BaseColumns._ID;
    public static final String COLUMNA_USUARIO = "usuario";
    public static final String COLUMNA_CONTRASEÑA = "contraseña";
    private static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" + COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMNA_USUARIO + " TEXT NOT NULL," +
            COLUMNA_CONTRASEÑA + " TEXT NOT NULL)";

    public MySqliteHelper(Context   context)
    {
        super(context, BASEDATOS_NOMBRE, null, BASEDATOS_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

    }
}
