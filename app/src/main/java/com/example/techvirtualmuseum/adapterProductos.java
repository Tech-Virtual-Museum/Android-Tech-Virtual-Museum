package com.example.techvirtualmuseum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterProductos extends ArrayAdapter<productModal> {


    // constructor for our list view adapter.
    public adapterProductos(@NonNull Context context, ArrayList<productModal> productModalArrayList) {
        super(context, 0, productModalArrayList);
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
        productModal productModal = getItem(position);

        // inicializamos el UI de los campos
        TextView nameProduct = listitemView.findViewById(R.id.nameProduct);
        TextView descripcionProduct = listitemView.findViewById(R.id.descripcionProduct);
        ImageView imageProduct = listitemView.findViewById(R.id.imageProduct);

        // colocamos los diferentes textos despues de haber accedido a sus campos
        nameProduct.setText(productModal.getName());
        descripcionProduct.setText(productModal.getDescription());


        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(productModal.getImg()).into(imageProduct);

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //al hacer click guardamos los datos del evento al que se lo hemos hecho
                Intent intent = new Intent(v.getContext(), eventDetails.class);
                intent.putExtra("name", productModal.getName());
                intent.putExtra("descripcion", productModal.getDescription());
                v.getContext().startActivity(intent);
            }
        });

        return listitemView;
    }
}

