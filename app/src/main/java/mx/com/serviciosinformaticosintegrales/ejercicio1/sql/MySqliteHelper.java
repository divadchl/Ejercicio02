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

    public static final String Table_Name = "item_table";
    public static final String Column_Id = BaseColumns._ID;
    public static final String Column_Item_Name = "name";
    public static final String Column_Item_Desc = "description";
    public static final String Column_Item_Resource = "reosurce_id";
    private static final String Create_Table = "CREATE TABLE " + Table_Name +
            "(" + Column_Id + "integer primary key autoincrement, " +
            Column_Item_Name + " text not null, " +
            Column_Item_Desc + " text not null, " +
            Column_Item_Resource + " integer not null";



    public MySqliteHelper(Context   context)
    {
        super(context, BASEDATOS_NOMBRE, null, BASEDATOS_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA);
        bd.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

    }
}
