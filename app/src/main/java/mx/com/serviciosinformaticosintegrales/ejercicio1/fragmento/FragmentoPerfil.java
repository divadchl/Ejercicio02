package mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mx.com.serviciosinformaticosintegrales.ejercicio1.R;

/**
 * Created by David on 17/06/16.
 */
public class FragmentoPerfil extends Fragment {
    private ImageView imvPerfil;
    private boolean cambio=true;

    public static FragmentoPerfil newInstance(String nombre)
    {
        FragmentoPerfil f = new FragmentoPerfil();
        Bundle b = new Bundle();
        b.putString("usuario",nombre);
        f.setArguments(b);
        return f;
    }

    public void changeImage()
    {
        imvPerfil.setImageResource(cambio?R.drawable.ic_action_face_unlock: R.drawable.ic_editor_insert_emoticon);
        cambio=!cambio;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String letra, letraComp;


        View view = inflater.inflate(R.layout.fragmento_perfil,container,false);
        imvPerfil = (ImageView) view.findViewById(R.id.fragmento_perfil_imgProfile);
        TextView txvUsuario = (TextView) view.findViewById(R.id.fragmento_perfil_txvUsuarioFragmento);
        Bundle bundle=getArguments();
        String strUsuario ;
        if(bundle!=null)
            strUsuario=bundle.getString("usuario");
        else
            strUsuario= "XML inflate";


        letra = strUsuario.substring(0,1).toUpperCase();
        letraComp="N";

        if(letra.compareTo(letraComp)<0)
        {
            cambio= true;
            changeImage();
        }
        else
        {
            cambio= false;
            changeImage();
        }

        txvUsuario.setText(strUsuario);
        return view;
    }
}
