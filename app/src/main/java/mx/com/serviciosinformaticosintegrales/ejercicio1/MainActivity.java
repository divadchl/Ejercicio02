package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsuario;
    private EditText edtContarseña;
    private View prbProgeso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario= (EditText) findViewById(R.id.activity_main_edtUsuario);
        edtContarseña = (EditText) findViewById(R.id.activity_main_edtContraseña);
        prbProgeso = findViewById(R.id.activity_main_prbProgreso);
        findViewById(R.id.activity_main_btnIngresar).setOnClickListener(this);
    }
    //Se utiliza cuando tienes varios botones
    @Override
    public void onClick(final View clicked) {
        switch (clicked.getId())
        {
            case R.id.activity_main_btnIngresar:
                processData();
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

                if (strUsuario.equals("motitas") && strContraseña.equals("123"))
                {
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
