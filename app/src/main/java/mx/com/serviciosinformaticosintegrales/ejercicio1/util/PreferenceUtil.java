package mx.com.serviciosinformaticosintegrales.ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelUser;

public class PreferenceUtil {

    private static final String FILE_NAME = "preferencias";
    private final SharedPreferences sp;
    private String fUltimaSesion;


    public PreferenceUtil(Context context)
    {
        sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void guardarUsuario(ModelUser modelUser)
    {
        sp.edit().putString("usuario", modelUser.strUsuario).apply();
        sp.edit().putString("contraseña", modelUser.strContraseña).apply();
    }

    public ModelUser obtenerUsuario()
    {
        String strUsuario = sp.getString("usuario", null);
        String strContraseña = sp.getString("contraseña", null);
        if (TextUtils.isEmpty(strUsuario) || TextUtils.isEmpty(strContraseña))
        {
            return null;
        }
        return new ModelUser(strUsuario, strContraseña);
    }

    public void guardarUltimaSesion(String fUltimaSesion)
    {
        sp.edit().putString("fUltimaSesion", fUltimaSesion).apply();
    }

    public String obtenerUltimaSesion()
    {
        fUltimaSesion = sp.getString("fUltimaSesion", null);
        if (TextUtils.isEmpty(fUltimaSesion))
        {
            return fUltimaSesion="";
        }
        return fUltimaSesion;
    }

    public void borrarPreferencias()
    {
        sp.edit().remove("usuario").commit();
        sp.edit().remove("contraseña").commit();
        sp.edit().remove("fUltimaSesion").commit();
    }

    public void guardarTiempoUso(int intTiempoUso)
    {
        sp.edit().putInt("tiempoUso", intTiempoUso).apply();
    }

    public String obtenerTiempoUso()
    {
        fUltimaSesion = sp.getString("fUltimaSesion", null);
        if (TextUtils.isEmpty(fUltimaSesion))
        {
            return fUltimaSesion="";
        }
        return fUltimaSesion;
    }
}
