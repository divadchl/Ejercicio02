package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelUser;
import mx.com.serviciosinformaticosintegrales.ejercicio1.sql.ItemDataSource;
import mx.com.serviciosinformaticosintegrales.ejercicio1.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsuario;
    private EditText edtContarseña;
    private TextView txvFechaUltimaSesion;
    private View prbProgeso;
    private CheckBox chkRecordar;
    PreferenceUtil util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario= (EditText) findViewById(R.id.activity_main_edtUsuario);
        edtContarseña = (EditText) findViewById(R.id.activity_main_edtContraseña);
        chkRecordar = (CheckBox) findViewById(R.id.chkRecuerdaMe);
        prbProgeso = findViewById(R.id.activity_main_prbProgreso);
        txvFechaUltimaSesion = (TextView) findViewById(R.id.txvFechaUltimaSesion);
        findViewById(R.id.activity_main_btnIngresar).setOnClickListener(this);
        findViewById(R.id.activity_main_btnRegistrarse).setOnClickListener(this);
        util = new PreferenceUtil(getApplicationContext());
        ModelUser modelUser = util.obtenerUsuario();
        if(modelUser != null)
        {
            edtUsuario.setText(modelUser.strUsuario);
            edtContarseña.setText(modelUser.strContraseña);
            chkRecordar.setChecked(true);
        }
        String strFechaUltimaSesion = util.obtenerUltimaSesion();
        if (strFechaUltimaSesion == "")
        {
            txvFechaUltimaSesion.setText("No se ha iniciado sesión");
        }
        else
        {
            txvFechaUltimaSesion.setText("Último Inicio de Sesión: " + strFechaUltimaSesion);
        }
    }
    //Se utiliza cuando tienes varios botones
    @Override
    public void onClick(final View clicked) {
        switch (clicked.getId())
        {
            case R.id.activity_main_btnIngresar:
                processData();
                break;
            case R.id.activity_main_btnRegistrarse:
                Intent intent = new Intent(getApplicationContext(),ActivityRegistro.class);
                startActivity(intent);
                break;
        }
    }

    private void processData() {
        final String strUsuario = edtUsuario.getText().toString();
        final String strContraseña = edtContarseña.getText().toString();
        prbProgeso.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prbProgeso.setVisibility(View.GONE);

                ItemDataSource objItemDataSource = new ItemDataSource(getApplication());
                ModelUser objModelUser = new ModelUser(strUsuario, strContraseña);
                //objItemDataSource.consultarUsuario(strUsuario);

                //if (strUsuario.equals("motitas") && strContraseña.equals("123"))
                if (objItemDataSource.consultarUsuario(objModelUser))
                {
                    util = new PreferenceUtil(getApplicationContext());
                    if(chkRecordar.isChecked())
                    {
                        util.guardarUsuario(new ModelUser(strUsuario, strContraseña));
                    }

                    Date fechaActual = new Date();
                    util.guardarUltimaSesion(fechaActual.toString());
                    Toast.makeText(getApplicationContext(), "Iniciando Sesión", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                    intent.putExtra("usuario", strUsuario);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        },1000*1);


    }
}
