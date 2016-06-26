package mx.com.serviciosinformaticosintegrales.ejercicio1.sql;

import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelItem;
import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelUser;

public class ItemDataSource {

    private final SQLiteDatabase bd;
    private final SQLiteDatabase leerbd;
    private boolean blnBandera;

    public ItemDataSource(Context context)
    {
        MySqliteHelper helper = new MySqliteHelper(context);
        bd = helper.getWritableDatabase();
        leerbd = helper.getReadableDatabase();
    }

    public void guardarUsuario(ModelUser modelUser)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMNA_USUARIO, modelUser.strUsuario);
        contentValues.put(MySqliteHelper.COLUMNA_CONTRASEÑA, modelUser.strContraseña);
        bd.insert(MySqliteHelper.NOMBRE_TABLA, null, contentValues);
    }

    public void saveItem (ModelItem modelItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.Column_Item_Name,modelItem.strItem);
        contentValues.put(MySqliteHelper.Column_Item_Desc, modelItem.strDescripcion);
        contentValues.put(MySqliteHelper.Column_Item_Resource, modelItem.intResourceId);
        bd.insert(MySqliteHelper.Table_Name, null, contentValues);
    }

    public boolean consultarUsuario(ModelUser modelUser)
    {

        Cursor cursor = leerbd.query(MySqliteHelper.NOMBRE_TABLA,null,
                MySqliteHelper.COLUMNA_USUARIO + "=? AND " + MySqliteHelper.COLUMNA_CONTRASEÑA + "=? ",
                new String[]{String.valueOf(modelUser.strUsuario), String.valueOf(modelUser.strContraseña)},null,null,null);
        if (cursor.getCount()>0)
        {
            blnBandera= true;
        }
        return blnBandera;
    }

    public void borrarUsuario(ModelUser modelUser)
    {
        bd.delete(MySqliteHelper.NOMBRE_TABLA,MySqliteHelper.COLUMNA_ID + "=?",
                new String[]{String.valueOf(modelUser.intId)});
    }

    public void deleteItem(ModelItem modelItem)
    {
        bd.delete(MySqliteHelper.Table_Name, MySqliteHelper.Column_Id + "=?",
                new String[]{String.valueOf(modelItem.intId)});
    }

    public List<ModelItem> getAllItems()
    {
        List<ModelItem> modelItemList = new ArrayList<>();
        Cursor cursor = bd.query(MySqliteHelper.Table_Name,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.Column_Id));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.Column_Item_Name));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.Column_Item_Desc));
            int resourceId = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.Column_Item_Resource));
            ModelItem modelItem = new ModelItem();
            modelItem.intId = id;
            modelItem.strItem = itemName;
            modelItem.strDescripcion = description;
            modelItem.intResourceId = resourceId;
            modelItemList.add(modelItem);
        }

        return modelItemList;
    }

}
