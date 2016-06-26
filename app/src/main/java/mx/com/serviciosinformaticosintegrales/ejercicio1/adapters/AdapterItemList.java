package mx.com.serviciosinformaticosintegrales.ejercicio1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import mx.com.serviciosinformaticosintegrales.ejercicio1.R;
import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelItem;
import java.util.List;
import com.squareup.picasso.Picasso;

public class AdapterItemList extends ArrayAdapter<ModelItem> {

    private final String url1="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Biblioteca_Central_UNAM_M%C3%A9xico.jpg/250px-Biblioteca_Central_UNAM_M%C3%A9xico.jpg";
    private final String url2="https://www.unam.mx/sites/default/files/images/menu/library-345273_1280.jpg";

    public AdapterItemList(Context context, List<ModelItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView txtDescripcionItem= (TextView) convertView.findViewById(R.id.row_list_txtDescripcionItem);
        TextView txtTitulo = (TextView) convertView.findViewById(R.id.row_list_txtTituloItem);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_list_image);

        ModelItem modelItem=getItem(position);
        txtTitulo.setText(modelItem.strItem);
        txtDescripcionItem.setText(modelItem.strDescripcion);
        Picasso.with(getContext()).load(modelItem.intResourceId==R.drawable.ic_action_face_unlock?
                url1:url2).into(img);
        //img.setImageResource(modelItem.intResourceId);
        return convertView;
    }
}
