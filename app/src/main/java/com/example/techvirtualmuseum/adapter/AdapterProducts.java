package com.example.techvirtualmuseum.adapter;

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

import com.example.techvirtualmuseum.R;
import com.example.techvirtualmuseum.ProductDetails;
import com.example.techvirtualmuseum.modal.ProductModal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProducts extends ArrayAdapter <ProductModal> {


    // constructor for our list view adapter.
    public AdapterProducts(@NonNull Context context, List<ProductModal> productModalArrayList) {
        super(context, 0, productModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.product_list, parent, false);
        }

        // obtenemos los datos del array list que tenemos en la clase modelo
        ProductModal productModal = getItem(position);

        // inicializamos el UI de los campos
        TextView nameProduct = listitemView.findViewById(R.id.nameProduct);
        TextView descripcionProduct = listitemView.findViewById(R.id.descripcionProduct);
        ImageView imageProduct = listitemView.findViewById(R.id.imageProduct);

        // colocamos los diferentes textos despues de haber accedido a sus campos
        nameProduct.setText(productModal.getName());
        descripcionProduct.setText(productModal.getDescripcion());

        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(productModal.getImg()).into(imageProduct);


        listitemView.setOnClickListener(v -> {
            //al hacer click guardamos los datos del evento al que se lo hemos hecho
            Intent intent = new Intent(v.getContext(), ProductDetails.class);
            intent.putExtra("name", productModal.getName());
            intent.putExtra("descripcion", productModal.getDescripcion());
            intent.putExtra("videoId", productModal.getVideoId());
            intent.putExtra("img", productModal.getImg());
            intent.putExtra("video", productModal.getVideo());
            v.getContext().startActivity(intent);
        });

        return listitemView;
    }
}
