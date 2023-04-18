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
import com.example.techvirtualmuseum.modal.CommentModal;
import com.example.techvirtualmuseum.ProductDetails;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;


public class AdapterComments extends ArrayAdapter<CommentModal> {

    public AdapterComments(@NonNull Context context, List<CommentModal> commentModalArrayList){
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
        CommentModal commentModal = getItem(position);

        // inicializamos el UI de los campos
        TextView commentName = listitemView.findViewById(R.id.commentName);
        TextView commentBody = listitemView.findViewById(R.id.commentBody);

        commentName.setText(commentModal.getAuthor());
        commentBody.setText(commentModal.getComment());

        listitemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetails.class);
            intent.putExtra("author", commentModal.getAuthor());
            intent.putExtra("comment", commentModal.getComment());
            v.getContext().startActivity(intent);
        });
        return listitemView;
    }

}
