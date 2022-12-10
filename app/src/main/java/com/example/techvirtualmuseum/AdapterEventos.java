package com.example.techvirtualmuseum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AdapterEventos extends ArrayAdapter<dataModal> {


    // constructor for our list view adapter.
    public AdapterEventos(@NonNull Context context, ArrayList<dataModal> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.eventos_list, parent, false);
        }

        // obtenemos los datos del array list que tenemos en la clase modelo
        dataModal dataModal = getItem(position);

        // inicializamos el UI de los campos
        TextView nameEvent = listitemView.findViewById(R.id.nameEvent);
        TextView dateEvent = listitemView.findViewById(R.id.dateEvent);
        TextView hourEvent = listitemView.findViewById(R.id.hourEvent);
        TextView priceEvent = listitemView.findViewById(R.id.priceEvent);
        TextView descriptionEvent = listitemView.findViewById(R.id.descripcionEvent);
        ImageView imageEvent = listitemView.findViewById(R.id.imageEvent);

        // colocamos los diferentes textos despues de haber accedido a sus campos
        nameEvent.setText(dataModal.getName());
        dateEvent.setText(dataModal.getDate());
        hourEvent.setText(dataModal.getHour());
        priceEvent.setText(dataModal.getPricing());
        descriptionEvent.setText(dataModal.getDescription());


        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(dataModal.getImgUrl()).into(imageEvent);

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //al hacer click guardamos los datos del evento al que se lo hemos hecho
                Intent intent = new Intent(v.getContext(), eventDetails.class);
                intent.putExtra("name", dataModal.getName());
                intent.putExtra("date", dataModal.getDate());
                intent.putExtra("hour", dataModal.getHour());
                intent.putExtra("pricing", dataModal.getPricing());
                intent.putExtra("description", dataModal.getDescription());
                intent.putExtra("imgUrl", dataModal.getImgUrl());
                v.getContext().startActivity(intent);
            }
        });
        
        return listitemView;
    }
}
