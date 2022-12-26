package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.firestore.FirebaseFirestore

class crearComentario : AppCompatActivity() {

    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_comentario)

        database = FirebaseFirestore.getInstance()

        //obtenemos el id del producto
        val qrText = intent.getStringExtra("QR_CODE_TEXT")



        //boton para guardar el comentario en la  base de datos
        val saveComment : Button = findViewById(R.id.guardarComentario)
        saveComment.setOnClickListener{

            //obtenemos los campos de texto asi como el texto que el usuario introduce
            val editTextName = findViewById<EditText>(R.id.name)
            val nameComment = editTextName.text.toString()

            val editTextComment = findViewById<EditText>(R.id.comentario)
            val comentarioComment = editTextComment.text.toString()

            val intent : Intent = Intent (this, displayComments::class.java)

            database!!.collection("comments").document(qrText!!).collection("comments").document().set(
                hashMapOf("author" to nameComment, "comment" to comentarioComment))

            startActivity(intent)
        }

        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent : Intent = Intent (this, displayComments::class.java)
            startActivity(intent)
        }
    }
}