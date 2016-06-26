package mx.com.serviciosinformaticosintegrales.ejercicio1.model;

/**
 * Created by David on 23/06/2016.
 */
public class ModelUser {
    public int intId;
    public String strUsuario;
    public String strContraseña;

    public ModelUser(String strUsuario, String strContraseña)
    {
        this.strUsuario = strUsuario;
        this.strContraseña = strContraseña;
    }
}
