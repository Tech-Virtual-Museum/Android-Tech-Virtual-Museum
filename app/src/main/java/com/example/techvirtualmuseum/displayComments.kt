package com.example.techvirtualmuseum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.example.techvirtualmuseum.adapter.AdapterComments
import com.example.techvirtualmuseum.adapter.AdapterEventos
import com.example.techvirtualmuseum.modal.commentModal
import com.example.techvirtualmuseum.modal.dataModal
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class displayComments : AppCompatActivity() {

    var comentariosLista: ListView? = null
    var commentModalArrayList: ArrayList<commentModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comments)

        comentariosLista = findViewById<View>(R.id.listItems) as ListView?
        commentModalArrayList = ArrayList()
        database = FirebaseFirestore.getInstance()

        // llamamos al metodo que nos cargaran los datos en la vista
        loadDatainListview()

        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent : Intent = Intent (this, productDetails::class.java)
            startActivity(intent)
        }
    }

    private fun loadDatainListview() {
        database!!.collection("pruebaComentarios").get()
            .addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // despues de obtener la lista, la pasamos para nuestra clase objeto
                        val commentModal: commentModal? = d.toObject(commentModal::class.java)

                        // despues de obtener los datos de firebase, la guardamos en un arrayList
                        commentModalArrayList!!.add(commentModal!!)
                    }
                    // pasamos el arrayList a la clase adapter que tenemos
                    val adapter =
                        AdapterComments(
                            this@displayComments,
                            commentModalArrayList
                        )
                    comentariosLista!!.setAdapter(adapter)
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@displayComments,
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }).addOnFailureListener(OnFailureListener {
                Toast.makeText(this@displayComments, "Fail to load data..", Toast.LENGTH_SHORT).show()
            })
    }
}