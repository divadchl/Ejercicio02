package mx.com.serviciosinformaticosintegrales.ejercicio1.fragmento;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import mx.com.serviciosinformaticosintegrales.ejercicio1.ActivityDetail;
import mx.com.serviciosinformaticosintegrales.ejercicio1.ActivityResumen;
import mx.com.serviciosinformaticosintegrales.ejercicio1.adapters.AdapterItemList;
import mx.com.serviciosinformaticosintegrales.ejercicio1.model.ModelItem;
import mx.com.serviciosinformaticosintegrales.ejercicio1.R;
import mx.com.serviciosinformaticosintegrales.ejercicio1.sql.ItemDataSource;

public class FragmentoLista extends Fragment {
    private ListView listView;
    //private List<ModelItem> array = new ArrayList<>();
    private int counter;
    private boolean isWifi;
    private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_lista,container,false);
        listView = (ListView) view.findViewById(R.id.fragmento_lista_lsvItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter= (AdapterItemList) parent.getAdapter();
                ModelItem modelItem =adapter.getItem(position);
                //ModelItem modelItem2 = array.get(position);
                Toast.makeText(getActivity(),modelItem.strItem,Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getActivity(), ActivityResumen.class);
                intent.putExtra("usuario", modelItem2.strItem);
                intent.putExtra("recurso", modelItem2.intResourceId);
                intent.putExtra("descripcion", modelItem2.strId);
                startActivity(intent);*/
            }
        });

        List<ModelItem> modelItemList = itemDataSource.getAllItems();
        isWifi = !(modelItemList.size()%2==0);
        counter = modelItemList.size();
        listView.setAdapter(new AdapterItemList(getActivity(),modelItemList));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter =(AdapterItemList) parent.getAdapter();
                final ModelItem modelItem =adapter.getItem(position);

                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete")
                        .setMessage(String.format("¿Desea borrar el elemento %s?",modelItem.strItem))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemDataSource.deleteItem(modelItem);
                                listView.setAdapter(new AdapterItemList(getActivity(),
                                        itemDataSource.getAllItems()));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });


        final EditText edtTextoItems = (EditText) view.findViewById(R.id.fragmento_lista_edtItem);
        view.findViewById(R.id.fragmento_lista_btnAgregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datoItem = edtTextoItems.getText().toString();
                if(!TextUtils.isEmpty(datoItem))
                {
                    ModelItem item = new ModelItem();
                    item.strItem = datoItem;
                    item.strDescripcion  = "Descripción " + counter;
                    item.intResourceId=isWifi?R.drawable.ic_action_face_unlock:R.drawable.ic_editor_insert_emoticon;
                    //array.add(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));
                    isWifi=!isWifi;
                    counter++;
                    edtTextoItems.setText("");
                }

            }
        });


        return view;
    }


}
