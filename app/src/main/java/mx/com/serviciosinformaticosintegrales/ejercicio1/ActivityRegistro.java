package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelUser;
import mx.com.serviciosinformaticosintegrales.ejercicio1.sql.ItemDataSource;
import mx.com.serviciosinformaticosintegrales.ejercicio1.util.PreferenceUtil;

public class ActivityRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText edtUsuario = (EditText) findViewById(R.id.activity_registro_edtUsuario);
        final EditText edtContraseña = (EditText)findViewById(R.id.activity_registro_edtContraseña);
        findViewById(R.id.activity_registro_btnRegistrarUsuario).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strUsuario = edtUsuario.getText().toString();
                String strContraseña = edtContraseña.getText().toString();
                ModelUser objModelUser = new ModelUser(strUsuario, strContraseña);
                ItemDataSource objItemDataSource = new ItemDataSource(getApplication());
                objItemDataSource.guardarUsuario(objModelUser);
                /*PreferenceUtil util = new PreferenceUtil(getApplicationContext());
                util.guardarUsuario(new ModelUser(strUsuario, strContraseña));*/
                finish();
            }
        });



    }
}
