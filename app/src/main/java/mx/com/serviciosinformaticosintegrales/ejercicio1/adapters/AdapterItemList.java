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

/**
 * Created by David on 17/06/16.
 */
public class AdapterItemList extends ArrayAdapter<ModelItem> {
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
        txtDescripcionItem.setText(modelItem.strId);
        img.setImageResource(modelItem.intResourceId);
        return convertView;
    }
}
