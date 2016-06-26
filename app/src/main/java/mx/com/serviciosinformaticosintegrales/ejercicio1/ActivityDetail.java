package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento.FragmentoLista;
import mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento.FragmentoPerfil;
import mx.com.serviciosinformaticosintegrales.ejercicio1.service.ServiceTimer;
import mx.com.serviciosinformaticosintegrales.ejercicio1.util.PreferenceUtil;


public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private TextView txvTiempo;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            txvTiempo.setText(String.format("Uso de la aplicación: %s seconds",counter));
        }
    };

    private String strUsuario;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        strUsuario= getIntent().getExtras().getString("usuario");
        //String strSaludo = String.format(getString(R.string.saludo),strUsuario);
        findViewById(R.id.activity_detail_btnFragmento1).setOnClickListener(this);
        findViewById(R.id.activity_detail_btnFragmento2).setOnClickListener(this);
        findViewById(R.id.activity_detail_btnCerrarSesion).setOnClickListener(this);
        txvTiempo = (TextView) findViewById(R.id.txvTiempoUSo);
    }

    @Override
    public void onClick(final View clicked) {
        switch (clicked.getId())
        {
            case R.id.activity_detail_btnFragmento1:
                cambiarFragmento1();
                break;
            case R.id.activity_detail_btnFragmento2:
                cambiarFragmento2();
                break;
            case R.id.activity_detail_btnCerrarSesion:
                cerrarSesion();
                finish();
                break;
        }
    }

    private void cambiarFragmento1() {
        FragmentoPerfil f = FragmentoPerfil.newInstance(strUsuario);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();

    }

    private void cambiarFragmento2() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentoLista()).commit();

    }

    public void pasarDatos()
    {
        return;
    }

    public void cerrarSesion()
    {
        PreferenceUtil util = new PreferenceUtil(getApplication());
        util.borrarPreferencias();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.strACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,filter);
        Log.d(ServiceTimer.strTAG,"OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.strTAG,"onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.strTAG,"OnDestroy, terminando servicio");
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
}
