package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento.FragmentoLista;
import mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento.FragmentoPerfil;


public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private String strUsuario;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        strUsuario= getIntent().getExtras().getString("usuario");
        //String strSaludo = String.format(getString(R.string.saludo),strUsuario);
        findViewById(R.id.activity_detail_btnFragmento1).setOnClickListener(this);
        findViewById(R.id.activity_detail_btnFragmento2).setOnClickListener(this);


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


}
