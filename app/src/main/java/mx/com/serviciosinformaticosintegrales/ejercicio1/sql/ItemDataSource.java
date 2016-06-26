package mx.com.serviciosinformaticosintegrales.ejercicio1.sql;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelUser;

public class ItemDataSource {

    private final SQLiteDatabase bd;

    public ItemDataSource(Context context)
    {
        MySqliteHelper helper = new MySqliteHelper(context);
        bd = helper.getWritableDatabase();
    }

    public void GuardarItem(ModelUser modelUser)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMNA_USUARIO, modelUser.strUsuario);
        contentValues.put(MySqliteHelper.COLUMNA_CONTRASEÑA, modelUser.strContraseña);
        bd.insert(MySqliteHelper.NOMBRE_TABLA, null, contentValues);
    }

    public void borarItem(ModelUser modelUser)
    {
        bd.delete(MySqliteHelper.NOMBRE_TABLA,MySqliteHelper.COLUMNA_ID + "=?", new String[]{String.valueOf(modelUser.i)})
    }

}
