package mx.com.serviciosinformaticosintegrales.ejercicio1.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ServiceTimer extends Service {

    public static final String strTAG = "unam_tag";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
