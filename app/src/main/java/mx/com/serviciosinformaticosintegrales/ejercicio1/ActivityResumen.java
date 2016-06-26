package mx.com.serviciosinformaticosintegrales.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityResumen extends AppCompatActivity {
    private TextView txvUsuario;
    private TextView txvDescripcion;
    private ImageView imvImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        txvUsuario = (TextView) findViewById(R.id.txvUsuario);
        txvDescripcion = (TextView) findViewById(R.id.txvDescripcion);
        imvImagen = (ImageView) findViewById(R.id.imvImagen);

        String strUsuario= getIntent().getExtras().getString("usuario");
        String strDescripcion = getIntent().getExtras().getString("descripcion");
        int strImagen = getIntent().getExtras().getInt("recurso");

        txvUsuario.setText(strUsuario);
        txvDescripcion.setText(strDescripcion);
        imvImagen.setImageResource(strImagen);
    }
}
