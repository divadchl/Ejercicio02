package mx.com.serviciosinformaticosintegrales.ejercicio1.service;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import mx.com.serviciosinformaticosintegrales.ejercicio1.util.PreferenceUtil;

public class ServiceTimer extends Service {

    public static final String strTAG = "unam_tag";
    public static final String strACTION_SEND_TIMER ="mx.com.serviciosinformaticosintegrales.SEND_TIMER";
    int intCounter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            intCounter++;
            handler.postDelayed(runnable,1000);
            Intent i = new Intent(strACTION_SEND_TIMER);
            i.putExtra("timer", intCounter);
            sendBroadcast(i);
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(strTAG,"OnstartCommand called");
        PreferenceUtil util = new PreferenceUtil(getApplication());
        intCounter = util.obtenerTiempoUso();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(strTAG,"Oncreate servicio");
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceUtil util = new PreferenceUtil(getApplication());
        util.guardarTiempoUso(intCounter);
        Log.d(strTAG,"OnDestroy Servicio");
        handler.removeCallbacks(runnable);
    }
}
