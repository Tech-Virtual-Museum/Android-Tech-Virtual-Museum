package com.example.techvirtualmuseum.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.techvirtualmuseum.R;
import com.example.techvirtualmuseum.modal.commentModal;
import com.example.techvirtualmuseum.productDetails;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;


public class AdapterComments extends ArrayAdapter<commentModal> {

    public AdapterComments(@NonNull Context context, ArrayList<commentModal> commentModalArrayList){
        super (context, 0, commentModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent){
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.comment_list, parent, false);
        }

        // obtenemos los datos del array list que tenemos en la clase modelo
        commentModal commentModal = getItem(position);

        // inicializamos el UI de los campos
        TextView commentName = listitemView.findViewById(R.id.commentName);
        TextView commentBody = listitemView.findViewById(R.id.commentBody);

        commentName.setText(commentModal.getAuthor());
        commentBody.setText(commentModal.getComment());

        listitemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), productDetails.class);
                    intent.putExtra("author", commentModal.getAuthor());
                    intent.putExtra("comment", commentModal.getComment());
                    v.getContext().startActivity(intent);
                }
        });
        return listitemView;
    }

}
